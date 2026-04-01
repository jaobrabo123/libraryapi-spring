package com.azevedo.libraryapi.repository;

import com.azevedo.libraryapi.model.Autor;
import com.azevedo.libraryapi.model.GeneroLivro;
import com.azevedo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        Autor autorSalvo = autorRepository.save(autor);

        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        UUID id = UUID.fromString("74c28c90-3b47-460d-90fb-8ba18f0a653a");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            autorRepository.save(autorEncontrado);
        }

    }

    @Test
    public void listarTest(){
        List<Autor> list = autorRepository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        long count = autorRepository.count();
        System.out.println("Contagem de autores: "+count);
    }

    @Test
    public void deletePorIdTest(){
        UUID id = UUID.fromString("b6884b14-eadf-47a4-9fca-74bf78f641f1");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        UUID id = UUID.fromString("bdeda670-9921-4996-9346-c04f90d359aa");
        var maria = autorRepository.findById(id).get();
        autorRepository.delete(maria);
    }

    @Test
    public void salvarAutorComlivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("999-23463");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O roubo à casa assombrada");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("8273-23463");
        livro2.setPreco(BigDecimal.valueOf(230));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("O roubo à casa normal");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

//        livroRepository.saveAll(autor.getLivros());

    }

    @Test
//    @Transactional
    public void listarLivrosAutorTest(){
        UUID id = UUID.fromString("49b3f91d-a4da-4293-a0b8-274a4e3a4ef9");
        var autor = autorRepository.findById(id).get();

        // Buscar os livros do autor
        List<Livro> livroList = livroRepository.findByAutor(autor);
        autor.setLivros(livroList);

        autor.getLivros().forEach(System.out::println);
    }

}
