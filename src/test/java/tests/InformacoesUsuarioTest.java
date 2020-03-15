package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
    @Test
    public void testAdicionarUmInformacaoDoUsuario(){
        // Informar onde está o chromediver na maquina local.
        System.setProperty("webdriver.chrome.driver", "/home/kleber/Documents/drivers/chromedriver");

        // Declara e instancia uma variavel para abertura do navegador
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Navegando até a pagina do Taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

        // Clicar no link com texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        // Digitar "julio0001" no campo "Login" dentro do id signinbox.
        WebElement formSigninbox = navegador.findElement(By.id("signinbox"));
        formSigninbox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar "123456" no campo "Senha" dentro do id signinbox.
        formSigninbox.findElement(By.name("password")).sendKeys("123456");

        //Clicar no link com texto "SIGN IN"
        formSigninbox.findElement(By.linkText("SIGN IN")).click();

        // Verificar se existe "Hi, Julio" no elemento a com classe me
        WebElement me = navegador.findElement(By.className("me"));
        String textoLogado = me.getText();
        assertEquals("Hi, Julio", textoLogado);
    }
}
