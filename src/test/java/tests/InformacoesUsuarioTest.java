package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")
public class InformacoesUsuarioTest {

    private WebDriver navegador;
    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        // Informar onde está o chromediver na maquina local.
        System.setProperty("webdriver.chrome.driver", "/home/kleber/Documents/drivers/chromedriver");

        // Declara e instancia uma variavel para abertura do navegador
        navegador = new ChromeDriver();
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

        // Clicar no link que possui a classe "me"
        navegador.findElement(By.className("me")).click();

        // Clicar no link que possui o texto "More data about you"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmInformacaoDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada){

        // Clicar no botão com texto "+ Add more data"
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar o pop onde está o formulario com id "addmoredate"
       WebElement formAddmoredata = navegador.findElement(By.id("addmoredata"));
       WebElement campoType = formAddmoredata.findElement(By.name("type"));
       new Select(campoType).selectByVisibleText(tipo);

        // No campo de name contact digitar o valor "+551199999999"
        formAddmoredata.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link com texto "SAVE"
        formAddmoredata.findElement(By.linkText("SAVE")).click();

        // Na menssagem de id "toast-container" validar o texto "You contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String msg = mensagemPop.getText();
        assertEquals(mensagemEsperada, msg);
    }

    //@Test
    public void removerUmContatoDeUmUsuario(){
        // Clicar no elemento pelo xpath //span[text()="+5511444443333"]/following-sibling::a
            navegador.findElement(By.xpath("//span[text()=\"+5511444443333\"]/following-sibling::a")).click();
        // Confirmar a janela javascript
            navegador.switchTo().alert().accept();

        // Validar que a mensagem apresentada foi "Rest in peace, dear phone!"
        WebElement msgPop = navegador.findElement(By.id("toast-container"));
        String msg = msgPop.getText();
        assertEquals("Rest in peace, dear phone!", msg);

        String screenshotArquivo = "/home/kleber/Documents/julio-automacao-java/test-report" + Generator.dataHoraParaArquivo() + test.getMethodName()+ ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        // Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(msgPop));

        // Clicar no link com texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown(){
        //Fecha o navegador
        navegador.quit();
    }
}
