package steps;

import com.aventstack.extentreports.Status;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.SimuladorPage;
import io.cucumber.java.pt.Dado;
import utils.ReportUtils;
import utils.SeleniumUtils;

public class SimuladorSteps {
  private SimuladorPage simuladorPage = new SimuladorPage();

  @Dado("que estou na tela do simulador de investimento")
  public void acessaTelaSimuladorSicredi() {
    simuladorPage.acessaSimulador();
  }

  @Quando("^preencho o formulário com dados de aplicar \"([^\"]*)\", investir \"([^\"]*)\", tempo \"([^\"]*)\" e periodo \"([^\"]*)\" para o \"([^\"]*)\"$")
  public void informaDadosLogin(Integer aplicar, Integer investir, Integer tempo, String periodo, String nomeCenario){
    ReportUtils.logMensagem(Status.INFO, "Inserindo dados na simulação.");
    //utiliza nome do cenário para criar pasta/diretorio com o mesmo nome
    SeleniumUtils.criaDiretorioPrint(nomeCenario);
    simuladorPage.preencheDados(aplicar, investir, tempo, periodo);
  }

  @Então("uma mensagem aparecerá avisando valor minimo de 20 reais")
  public void verificaAlertaMenosDe20Reais() {
    System.out.println("cheguei aqui");
  }
}
