package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecretaPage {
    private WebDriver navegador;

    public SecretaPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public MePage clicarMe(){
        navegador.findElement(By.className("me")).click();

        return new MePage(navegador);
    }
}
