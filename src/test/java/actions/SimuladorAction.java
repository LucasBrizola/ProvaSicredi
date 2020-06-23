package actions;

import com.aventstack.extentreports.Status;
import pages.SimuladorPage;
import utils.ReportUtils;

public class SimuladorAction {
    int aplicar;
    int investir;

    private final SimuladorPage simuladorPage = new SimuladorPage();

    public void preencheDados(Integer aplicar, Integer investir, Integer tempo, String periodo) {
        ReportUtils.logMensagem(Status.INFO, "Adicionando dados no simulador.");
        this.aplicar = aplicar;
        this.investir = investir;

        simuladorPage.insereCampoAplicar(aplicar);

        simuladorPage.insereCampoInvestir(investir);

        simuladorPage.insereCampoTempo(tempo);

        //simuladorPage.escolheCampoPeriodo(periodo);

        simuladorPage.gerarSimulacao();
    }

    public boolean validaGeracaoSimulacao() {
        return simuladorPage.validaGeracaoSimulacao();
    }

    public boolean verificaAlertaMenosDe20Reais() {
        return simuladorPage.verificaAlertaMenosDe20Reais(aplicar, investir);
    }

}
