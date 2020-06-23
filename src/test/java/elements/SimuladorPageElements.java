package elements;

import utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SimuladorPageElements extends SeleniumUtils {

  public static WebDriver driver;

  @FindBy(id="valorAplicar")
  protected WebElement VALOR_APLICAR;

  @FindBy(id="valorAplicar-error")
  protected WebElement VALOR_APLICAR_ERROR;

  @FindBy(id="valorInvestir-error")
  protected WebElement VALOR_INVESTIR_ERROR;

  @FindBy(id="valorInvestir")
  protected WebElement VALOR_INVESTIR;

  @FindBy(id="tempo")
  protected WebElement TEMPO;

  @FindBy(id="periodo")
  protected WebElement PERIODO;

  @FindBy(xpath = "//*[@id=\"formInvestimento\"]/div[5]/ul/li[2]/button")
  protected WebElement BOTAO_SIMULAR;

  @FindBy(xpath = "/html/body/div[3]/div/div/div[1]/div/div[2]/a")
  protected WebElement BOTAO_REFAZER_SIMULACAO;

  @FindBy(xpath = "//*[@class='modal bootstrap-dialog type-primary size-normal fade in']")
  public WebElement MODAL_BACKDROP;
}
