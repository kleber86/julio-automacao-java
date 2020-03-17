package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

public class InformacoesUsuarioPageObjectTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createBrowserStack();
    }

    /* Abordagem Funcional */
    @Test
    public void testAdicionarUmInformcaoAdicionalDoUsuarioFuncional() {
        String textoToast = new LoginPage(navegador)
                .clicarSignIn()
                .fazerLogin("julio0001", "123456")
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaioAddMoreDataAboutYou()
                .adicionarContato("Phone", "+558190000000")
                .capturaTextoToast();

        Assert.assertEquals("Your contact has been added!",textoToast);

    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
