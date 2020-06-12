package elements;

import utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SimuladorPageElements extends SeleniumUtils {

  public static WebDriver driver;

  @FindBy(id="valorAplicar")
  protected WebElement VALOR_APLICAR;

  @FindBy(id="valorInvestir")
  protected WebElement VALOR_INVESTIR;

  @FindBy(id="tempo")
  protected WebElement TEMPO;

  @FindBy(id="periodo")
  protected WebElement PERIODO;


}
