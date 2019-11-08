import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {
    @Test
    public void frames() {
        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");

        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();

        driver.switchTo().defaultContent(); // traz o foco pra pagina principal
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
    }

    @Test
    public void Janelas() {
        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");

        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo");
        driver.close();
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("e agora?");

    }
    @Test
    public void JanelaSemTitulo() {
        System.setProperty("webdriver.chrome.driver", "/home/stefania/Projetos/web/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///home/stefania/Projetos/Desafio_paginas_da_web/componentes.html");

        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora? ");
        driver.quit();

    }
}
