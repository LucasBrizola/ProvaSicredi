package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.Scenario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtils extends SeleniumUtils {

  public static ExtentHtmlReporter htmlReporter;
  public static ExtentReports extentReports;
  public static ExtentTest child;
  public static String diretorioReport;

  public static void criarReportFeature(Scenario cenario) {
    if (extentReports == null) {
      extentReports = new ExtentReports();

      String dir = System.getProperty("user.dir");
      String filename = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
      filename = filename.replace(".", "_");
      String nomeFeature = cenario.getId().split(";")[0];
      nomeFeature.replace(" ", "_").replace("\"", "");
      SeleniumUtils.criarDiretorio(dir + "\\report");
      setDiretorioReport("./report/" + nomeFeature + "-" + filename);
      SeleniumUtils.criarDiretorio(diretorioReport);

      htmlReporter = new ExtentHtmlReporter(diretorioReport + "\\report.html");
      extentReports.attachReporter(htmlReporter);
    }
    child = extentReports.createTest(cenario.getName(),
        cenario.getId().split(";")[1].replace("-", " ").toUpperCase());
  }

  public static void setDiretorioReport(String diretorio) {
    diretorioReport = diretorio;
  }

  public static void logMensagem(Status status, String mensagem, String imagem) {
    try {
      System.out.println(mensagem);
      child.log(status, mensagem, MediaEntityBuilder.createScreenCaptureFromPath(imagem).build());
      extentReports.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void atualizaReport(Scenario cenario) {
    if (cenario.isFailed()) {
      child.log(Status.ERROR, "Erro encontrado durante a execução.");
    } else {
      child.log(Status.PASS, "Cenário executado com sucesso.");
    }
    extentReports.flush();
  }

  public static String getDiretorioReport() {
    return diretorioReport;
  }

  public static void logMensagem(Status status, String mensagem) {
    System.out.println(mensagem);
    child.log(status, mensagem);
    extentReports.flush();
  }
}
