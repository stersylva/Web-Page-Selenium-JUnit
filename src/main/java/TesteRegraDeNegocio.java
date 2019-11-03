import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.chrome.ChromeDriver;

public class TesteRegraDeNegocio {

    private WebDriver driver;
    private DSL dsl;
    @Before
    public void inicializa(){
        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");
        dsl = new DSL(driver);
    }

    @Test
    public void RealizarCadastroComSucesso() {

        dsl.escreve("elementosForm:nome", "Maria");
        dsl.escreve("elementosForm:sobrenome", "Silva");
        dsl.clicarRadio("elementosForm:sexo:1");
        dsl.clicarRadio("elementosForm:comidaFavorita:2");

        new Select(driver.findElement(By.id("elementosForm:escolaridade")))
                .selectByVisibleText("Mestrado");
        new Select(driver.findElement(By.id("elementosForm:esportes")))
                .selectByVisibleText("Natacao");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Maria"));


        Assert.assertEquals("Sobrenome: Silva", driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
        Assert.assertEquals("Escolaridade: mestrado", driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Esportes: Natacao", driver.findElement(By.id("descEsportes")).getText());

        driver.quit();
    }


    @Test
    public void ValidarNomeObrigatorio() {

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
        driver.quit();

    }

    @Test
    public void ValidarSobrenomeObrigatorio() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        driver.quit();

    }

    @Test
    public void ValidarSexo() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void ValidarComidaVegariana() {


        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        driver.quit();
    }

    @Test
    public void ValidarEsportista() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Maria");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        driver.quit();

    }
}
