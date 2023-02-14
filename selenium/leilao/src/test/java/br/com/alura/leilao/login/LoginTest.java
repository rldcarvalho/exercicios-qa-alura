package br.com.alura.leilao.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    @Test
    void deveriaEfetuarLoginComDadosValidos(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertNotEquals("http://localhost:8080/login", browser.getCurrentUrl());
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

        browser.quit();
    }

    @Test
    void naoDeveriaLogarComDadosInvalidos(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123123");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertEquals("http://localhost:8080/login?error", browser.getCurrentUrl());
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));

        browser.quit();
    }

}
