package steps;

import actions.SimuladorAction;
import com.aventstack.extentreports.Status;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Dado;
import pages.SimuladorPage;
import utils.ReportUtils;
import utils.SeleniumUtils;

import static org.junit.Assert.assertTrue;

public class SimuladorSteps {
    private final SimuladorPage simuladorPage = new SimuladorPage();
    private final SimuladorAction simuladorAction = new SimuladorAction();

    @Dado("que estou na tela do simulador de investimento")
    public void acessaTelaSimuladorSicredi() {
        simuladorPage.acessaSimulador();
    }

    @Quando("^preencho o formulário com dados de aplicar \"([^\"]*)\", investir \"([^\"]*)\", tempo \"([^\"]*)\" e periodo \"([^\"]*)\" para o \"([^\"]*)\"$")
    public void informaDadosLogin(Integer aplicar, Integer investir, Integer tempo, String periodo, String nomeCenario) {
        ReportUtils.logMensagem(Status.INFO, "Inserindo dados na simulação.");
        //utiliza nome do cenário para criar pasta/diretorio com o mesmo nome
        SeleniumUtils.criaDiretorioPrint(nomeCenario);
        simuladorAction.preencheDados(aplicar, investir, tempo, periodo);
    }

    @Então("a simulação é gerada com sucesso")
    public void validaSimulacaoGerada() {
        ReportUtils.logMensagem(Status.INFO, "Validando se foi realizada a simulação.");
        assertTrue(simuladorAction.validaGeracaoSimulacao());
    }

    @Então("uma mensagem aparecerá avisando valor minimo de 20 reais")
    public void verificaAlertaMenosDe20Reais() {
        ReportUtils.logMensagem(Status.INFO, "Validando se alerta de menos de 20 reais apareceu.");
        assertTrue(simuladorAction.verificaAlertaMenosDe20Reais());
    }

    @Quando("^eu fizer uma requisição get para o endpoint da API para o cenário \"([^\"]*)\"$")
    public void euFizerUmaRequisiçãoGetParaOEndpointDaAPIParaOCenário(String nomeCenario) {
        ReportUtils.logMensagem(Status.INFO, "Realizando requisição para a api de simulação.");
        SeleniumUtils.criaDiretorioPrint(nomeCenario);
        simuladorAction.geraRequisicao("http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador");
    }

    @Entao("devo verificar que os dados retornados estão corretos")
    public void verificaDadosSimulacao() {
        ReportUtils.logMensagem(Status.INFO, "Validando Response da API.");
        assertTrue(simuladorAction.validaResponseApi());
    }
}
