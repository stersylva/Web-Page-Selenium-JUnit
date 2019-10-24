import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

    @Ignore
    public void pesquisarItem (){

        // abrindo o navegador

        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();

       navegador.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");
   }


}
