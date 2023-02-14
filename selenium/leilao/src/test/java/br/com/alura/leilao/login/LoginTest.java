package br.com.alura.leilao.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    static void beforeAll(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
    }

    @BeforeEach
    void beforeEach(){
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    void afterEach(){
        this.browser.quit();
    }

    @Test
    void deveriaEfetuarLoginComDadosValidos(){

        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertNotEquals(URL_LOGIN, browser.getCurrentUrl());
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

    }

    @Test
    void naoDeveriaLogarComDadosInvalidos(){

        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123123");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertEquals("http://localhost:8080/login?error", browser.getCurrentUrl());
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));

    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        this.browser.navigate().to("http://localhost:8080/leiloes/2");

        Assertions.assertEquals(URL_LOGIN, browser.getCurrentUrl());
        Assertions.assertFalse(browser.getPageSource().contains("Dados Leilão"));

    }

}
