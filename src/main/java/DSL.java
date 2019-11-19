import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DSL {

    private WebDriver driver;
    private Iterable<? extends WebElement> allSelectedOptions;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    /****************** TextField e TextArea *********************/

    public void escrever(By by, String texto) {
        driver.findElement(by).sendKeys(texto);
    }

    public void escrever(String id_campo, String texto) {
        escrever(By.id(id_campo), texto);

    }

    public String obterValorCampo(String id_campo) {
        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }

    /*********************** Radio e Check *********************/

    public void clicarRadio(String id) {
        driver.findElement(By.id(id)).click();
    }

    public boolean isRadioMarcado(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }

    public void clicarCheck(String id) {
        driver.findElement(By.id(id)).click();
    }

    public boolean isCheckMarcado(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }

    /********************************** Select ******************************/


    public void select(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public void desmarcarSelect(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
    }

    public String deveVerificarValorSelect(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public List<String> obterValoresSelect(String id) {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<String> valores = new ArrayList<String>();
        for (WebElement opcao: allSelectedOptions) {
            valores.add(opcao.getText());
        }
        return valores;
    }

    public int obterQuantidadeOpcoesSelect(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }

    public boolean verificarOpcaoSelect(String id, String opcao) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        for(WebElement option : options) {
            if(option.getText().equals(opcao)){
                return true;
            }
        }
        return false;
    }

    /*********************** Botao *****************************/

    public void clicarBotao(String id) {
        driver.findElement(By.id(id)).click();
    }
    public String obterValueElemento(String id) {
        return driver.findElement(By.id(id)).getAttribute("value");
    }

    /********************************** Texto *******************/
    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }
    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    /********************************* Alerts *************************/

    public String alertObterTexto() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }
    public String alertaObterTextoEAceitar() {
        Alert alert = driver.switchTo().alert();
        String valor = alert.getText();
        alert.accept();
        return valor;
    }
    public String alertObterTextoENegar() {
        Alert alert = driver.switchTo().alert();
        String valor = alert.getText();
        alert.dismiss();
        return valor;
    }
    public void alertEscrever(String valor) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(valor);
        alert.accept();
    }

    /*********************************** Frames e Janelas ******************/
    public void entrarFrame(String id) {
        driver.switchTo().frame(id);
    }
    public void sairFrame() {
        driver.switchTo().defaultContent();
    }
    public void trocarJanela(String id) {
        driver.switchTo().window(id);
    }

}
