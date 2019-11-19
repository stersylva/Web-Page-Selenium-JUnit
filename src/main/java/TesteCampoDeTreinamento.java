import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class TesteCampoDeTreinamento {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
//        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
//        driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "/home/stefania/Projetos/web/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");
        dsl = new DSL(driver);
    }
    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeTextField() {
        dsl.escrever("elementosForm:nome", "teste de escrita");
        Assert.assertEquals("teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testeTexArea() {
        dsl.escrever("elementosForm:sugestoes", "teste\nteste\n");
        Assert.assertEquals("teste\nteste\n", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void testeRadioButon() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void testCheckBox() {
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
    }

    @Test
    public void select() {
//        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
//        Select combo = new Select(element);
//        //combo.selectByIndex(4);
//        //combo.selectByValue("superior");
//        combo.selectByVisibleText("2o grau completo");
        dsl.select("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.deveVerificarValorSelect("elementosForm:escolaridade"));
    }

    @Test
    public void verificaCamposDoSelect() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions(); //getOptions retorna uma lista de todas as opções
        Assert.assertEquals(8, options.size()); //size retona o tamanho

        boolean encontrou = false;
        for(WebElement option: options) {
            if(option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);

    }

    @Test
    public void outraVerificacaoDoSelect() {
        Assert.assertEquals(8, dsl.obterQuantidadeOpcoesSelect("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verificarOpcaoSelect("elementosForm:escolaridade", "Mestrado"));
    }

    @Test
    public void selectMultiplaEscolha() {
        dsl.select("elementosForm:esportes", "Natacao");
        dsl.select("elementosForm:esportes", "Corrida");
        dsl.select("elementosForm:esportes", "O que eh esporte?");

        List<String> opcoesMarcadas = dsl.obterValoresSelect("elementosForm:esportes");
        Assert.assertEquals(3, opcoesMarcadas.size());

        dsl.desmarcarSelect("elementosForm:esportes", "Corrida");
        opcoesMarcadas = dsl.obterValoresSelect("elementosForm:esportes");
        Assert.assertEquals(2, opcoesMarcadas.size());
        Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));

        /******************** antes do PageObjetc

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida"); // para desmarcar a opção corrida usa o deselectVisibleText
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());
         ************************/
    }

    @Test
    public void botao() {
        //para não fazer 2 buscas uma pra clicar e outra pra verificar, coloca dentro de uma WebElenent
        //WebElement botao = driver.findElement(By.id("buttonSimple"));
        dsl.clicarBotao("buttonSimple");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void link() {
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }
    @Test
    public void texto() {
        //System.out.println(driver.findElement(By.tagName("body")).getText()); // todo texto visivel na tela
//        Assert.assertTrue(driver.findElement(By.tagName("body"))
//                .getText().contains("Campo de Treinamento"));

        //melhor forma de pegar um texto na tela
        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3"))
                .getText());
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
                driver.findElement(By.className("facilAchar")).getText());
    }

}
