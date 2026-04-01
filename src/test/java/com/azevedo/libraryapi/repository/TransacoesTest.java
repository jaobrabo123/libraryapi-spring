package com.azevedo.libraryapi.repository;

import com.azevedo.libraryapi.service.TrasacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TrasacaoService trasacaoService;

    @Test
    void transacaoSimples(){
        trasacaoService.executar();
    }

    @Test
    void transacaoEstadoManaged(){
        trasacaoService.atualizacaoSemAtualizar();
    }

}
