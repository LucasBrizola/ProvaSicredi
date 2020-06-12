package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import setup.TestRule;
import com.aventstack.extentreports.Status;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtils {

  public static WebDriver driver;

  public SeleniumUtils() {
    driver = TestRule.getDriver();
  }

  protected static Boolean verificaExistenciaDeElementoNaTela(WebElement element) {
    boolean elementoOK = false;
    try {
      if (element.isDisplayed()) {
        elementoOK = true;
      }
    } catch (Exception ignored) {
    }

    return elementoOK;
  }

  public static String loadFromPropertiesFile(String propertieFileName, String propertLoad) {
    Properties prop = new Properties();
    InputStream input = null;
    String path;
    if (usingJarFile()) {
      path = "";
    } else {
      path = "src/test/resources/";
    }
    String property = "";

    try {
      input = new FileInputStream(path + propertieFileName);
      // load a properties file
      prop.load(input);

      // get the property value and print it out
      property = prop.getProperty(propertLoad);

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return property;
  }

  private static Boolean usingJarFile() {
    return new SeleniumUtils().getRunningJarName() != null;
  }

  private String getRunningJarName() {
    String className = this.getClass().getName().replace('.', '/');
    String classJar =
        this.getClass().getResource("/" + className + ".class").toString();
    if (classJar.startsWith("jar:")) {
      String[] vals = classJar.split("/");
      for (String val : vals) {
        if (val.contains("!")) {
          return val.substring(0, val.length() - 1);
        }
      }
    }
    return null;
  }

  public static void criaDiretorioPrint(String nomeTest) {
    String patch = loadFromPropertiesFile("sicredi.properties", "CAMINHO_RAIZ_PASTA");
    String caminho = System.getProperty("user.dir") + "\\" + patch + nomeTest + "\\";
    if (!diretorioExiste(caminho)) {
      new File(caminho).mkdir();
      System.out.println("Criado diretorio dos prints do Teste - " + nomeTest);
    } else {
      if (diretorioVazio(caminho)) {
        System.out.println("Diretório onde os Prints serão salvos encontra-se vazio.");
      } else {
        esvaziaDiretorio(caminho);
      }
    }
    gravaNomeCenario(nomeTest);
  }

  private static boolean diretorioExiste(String caminho) {

    File diretorio;

    diretorio = new File(caminho);
    return diretorio.exists();
  }

  private static void esvaziaDiretorio(String caminho) {
    System.out.println("Deletando evidências existentes");
    File folder = new File(caminho);
    if (folder.isDirectory()) {
      File[] sun = folder.listFiles();
      assert sun != null;
      for (File toDelete : sun) {
        if (toDelete.delete()) {
          System.out.println(String.format("Diretório %s não pode ser deletado.", toDelete.getName()));
        }
      }
    }
  }

  private static boolean diretorioVazio(String diretorio) {
    boolean dirVazio = false;
    File dir = new File(diretorio);
    String[] children = dir.list();
    if (children == null) {
      // Diretório não existe ou não é um diretório
      dirVazio = true;
    }

    return dirVazio;
  }

  private static void gravaNomeCenario(String cenario) {
    try {
      String File = (loadFromPropertiesFile("sicredi.properties", "CAMINHO_ARQUIVO_NOME_CENARIO"));
      escreverArquivoNovo(File, cenario);
    } catch (IOException e) {
      System.out.println("Erro ao gravar nome do cenário!");
      e.printStackTrace();
    }
  }

  private static void escreverArquivoNovo(String patch, String i) throws IOException {
    String file = System.getProperty("user.dir") + "\\" + patch;
    System.out.println("Caminho do arquivo com o nome do cenário - " + file);
    FileWriter writer = new FileWriter(new File(file));
    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    bufferedWriter.write(i);
    bufferedWriter.write(";");
    bufferedWriter.write("\n");
    bufferedWriter.flush();
    bufferedWriter.close();
    writer.close();
  }


  public static String getScreenshotReport() {
    String dir;
    String imagem_dir = "";
    driver.getCurrentUrl();
    String nomePrint = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    nomePrint = nomePrint.replace(".", "_");
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      Thread.sleep(1000);
      criarDiretorio(ReportUtils.getDiretorioReport() + "/screenshots");
      dir = ReportUtils.getDiretorioReport() + "/screenshots/" + nomePrint + ".png";
      imagem_dir = "./screenshots/" + nomePrint + ".png";
      copyFileUsingStream(scrFile, new File(dir));
    } catch (Exception e) {
      ReportUtils.logMensagem(Status.FAIL, "Erro ao salvar o Screenshot - " + e);
      Log4jUtils.logMensagem("ERROR", "Erro ao salvar o Screenshot - " + e);
    }
    return imagem_dir;
  }

  private static void copyFileUsingStream(File source, File dest) throws IOException {
    try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
      byte[] buffer = new byte[1024];
      int length;
      while ((length = is.read(buffer)) > 0) {
        os.write(buffer, 0, length);
      }
    }
  }

  static void criarDiretorio(String diretorioASerCriado) {
    try {
      File diretorio = new File(diretorioASerCriado);
      if (!diretorio.exists()) {
        diretorio.mkdirs();
      }
    } catch (Exception e) {
      ReportUtils.logMensagem(Status.FAIL, "" + e.getMessage());
      Log4jUtils.logMensagem("ERROR", "" + e.getMessage());
    }
  }

}
