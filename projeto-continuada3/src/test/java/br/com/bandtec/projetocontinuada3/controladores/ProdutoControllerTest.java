package br.com.bandtec.projetocontinuada3.controladores;

import br.com.bandtec.projetocontinuada3.dominios.Produto;
import br.com.bandtec.projetocontinuada3.repositorios.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    ProdutoController controller;

    @MockBean
    ProdutoRepository repository;

    @Test
    @DisplayName("A lista deve retornar com status 200")
    void verificar() {

        List<Produto> produto = Arrays.asList(Mockito.mock(Produto.class));

        Mockito.when(repository.findAll()).thenReturn(produto);

        ResponseEntity resposta = controller.verificar();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(produto,resposta.getBody());;

    }
    @Test
    void verificar2(){

        List<Produto> produtos = new ArrayList<>();

        Mockito.when(repository.findAll()).thenReturn(produtos);

        ResponseEntity resposta = controller.verificar();

        assertEquals(204,resposta.getStatusCodeValue());
        assertEquals(null,resposta.getBody());
    }

    @Test
    @DisplayName("produtos")
    void enfileirarReq() {
        Produto produto = new Produto();

        ResponseEntity resposta = controller.enfileirarReq(produto);
        assertEquals(202,resposta.getStatusCodeValue());
    }

    @Test
    void conferir() {
    }

    @Test
    void desfazerReq() {
    }

    @Test
    void consulta() {
    }
}