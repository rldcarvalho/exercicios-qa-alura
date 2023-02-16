package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;

    @AfterEach
    void afterEach(){
        paginaDeLeiloes.fechar();
    }

    @Test
    void deveriaCadastrarLeilao(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        CadastroLeilaoPage paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
    }

}
