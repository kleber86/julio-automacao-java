package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

public class InformacoesUsuarioPageObjectTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    //@Test
    public void testAdicionarUmInformcaoAdicionalDoUsuario(){
        new LoginPage(navegador)
                .clicarSignIn()
                .digitarLogin("julio0001")
                .digitarSenha("123456")
                .clicarSignIn();
    }
    /* Abordagem Funcional */
    @Test
    public void testAdicionarUmInformcaoAdicionalDoUsuarioFuncional() {
        new LoginPage(navegador)
                .clicarSignIn()
                .fazerLogin("julio0001", "123456")
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaioAddMoreDataAboutYou();
    }
    //@After
    public void tearDown(){
        navegador.quit();
    }
}
