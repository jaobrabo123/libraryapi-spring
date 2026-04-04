package com.azevedo.libraryapi.controller.mappers;

import com.azevedo.libraryapi.controller.dto.CadastroLivroDTO;
import com.azevedo.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.azevedo.libraryapi.model.Livro;
import com.azevedo.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.autorId()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);

}
