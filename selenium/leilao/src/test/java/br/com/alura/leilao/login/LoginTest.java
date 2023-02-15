package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    void beforeEach(){
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    void afterEach(){

        paginaDeLogin.fechar();
    }

    @Test
    void deveriaEfetuarLoginComDadosValidos(){

        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();

        Assertions.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveriaLogarComDadosInvalidos(){

        paginaDeLogin.preencheFormularioDeLogin("invalido", "123123");
        paginaDeLogin.efetuaLogin();

        Assertions.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
        Assertions.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
        Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        paginaDeLogin.navegaParaPaginaDeLances();

        Assertions.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertFalse(paginaDeLogin.contemTexto("Dados Leilão"));
    }

}
