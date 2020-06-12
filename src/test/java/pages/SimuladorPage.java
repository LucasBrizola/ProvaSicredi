package pages;

import elements.SimuladorPageElements;
import setup.TestRule;
import utils.ReportUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.PageFactory;

public class SimuladorPage extends SimuladorPageElements {

  public SimuladorPage() {
    driver = TestRule.getDriver();
    PageFactory.initElements(TestRule.getDriver(), this);
  }

  public void acessaSimulador() {
    ReportUtils.logMensagem(Status.INFO, "Acessando Simulador");
    String urlSistema;
    urlSistema = loadFromPropertiesFile("sicredi.properties",
        "SIMULADOR_PAGE");

    driver.manage().deleteAllCookies();
    driver.navigate().to(urlSistema);
  }

  public void preencheDados(Integer aplicar, Integer investir, Integer tempo, String periodo){

  }
}
