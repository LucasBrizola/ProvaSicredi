package setup;

import utils.Log4jUtils;
import utils.ReportUtils;
import utils.SeleniumUtils;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestRule {
  protected static WebDriver driver;

  @Before
  public void beforeCenario(Scenario s) {

    ReportUtils.criarReportFeature(s);
    Log4jUtils.criaLog(s);

    ChromeOptions options = new ChromeOptions();
    String headless = SeleniumUtils.loadFromPropertiesFile("sicredi.properties",
        "CHROME_HEADLESS");

    String pathProjeto = System.getProperty("user.dir");
    String sistemaOperacional =  System.getProperty("os.name");

      if (sistemaOperacional.toUpperCase().contains("WINDOWS")) {
        System.setProperty("webdriver.chrome.driver", pathProjeto + "/drivers/chromedriver.exe");
        System.setProperty("os.download.path", System.getProperty("user.home").replaceAll("\\\\", "/") + "/Downloads/");
      } else {
        System.setProperty("webdriver.chrome.driver", pathProjeto + "/drivers/chromedriver");
        System.setProperty("os.download.path", System.getProperty("user.home") + "/Downloads/");
      }

      if (headless.equals("TRUE")) options.addArguments("headless");

      options.addArguments("--no-sandbox");
      options.addArguments("--disable-dev-shm-usage");
      options.addArguments("window-size=1920x1080");
      options.addArguments("--incognito");

      driver = new ChromeDriver(options);

    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();

    String nomeCenario = s.getName();
    ReportUtils.logMensagem(Status.INFO, "Executando Cenario de Teste: " + nomeCenario);

  }

  public static WebDriver getDriver() {
    return driver;
  }

  @After
  public void afterCenario(Scenario cenario) {
    ReportUtils.logMensagem(Status.INFO, "Finalizando instância do chromeDriver",
        SeleniumUtils.getScreenshotReport());
    driver.quit();
    ReportUtils.atualizaReport(cenario);
  }


}
