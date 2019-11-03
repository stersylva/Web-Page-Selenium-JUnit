import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

    private WebDriver driver;

    @Test
    public void alertSimples() {
        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");

        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Alert Simples", texto);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(alert.getText());
    }
    @Test
    public void alertCorfirm() {
        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");

        driver.findElement(By.id("confirm")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alerta.getText());
        alerta.accept();
        Assert.assertEquals("Confirmado", alerta.getText());
        alerta.accept();

        driver.findElement(By.id("confirm")).click();
        alerta = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alerta.getText());
        alerta.dismiss();
        Assert.assertEquals("Negado", alerta.getText());
        alerta.accept();

        driver.quit();
    }
    @Test
    public void alertPrompt() {
        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");

        driver.findElement(By.id("prompt")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("12");
        alerta.accept();
        Assert.assertEquals("Era 12?", alerta.getText());
        alerta.accept();
        Assert.assertEquals(":D", alerta.getText());
        alerta.accept();

        driver.quit();
    }

}
