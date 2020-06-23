package pages;

import elements.SimuladorPageElements;
import setup.TestRule;
import utils.ReportUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumUtils;

public class SimuladorPage extends SimuladorPageElements {

    public SimuladorPage() {
        driver = TestRule.getDriver();
        PageFactory.initElements(TestRule.getDriver(), this);
    }

    public void acessaSimulador() {
        ReportUtils.logMensagem(Status.INFO, "Acessando Simulador.");
        String urlSistema;
        urlSistema = loadFromPropertiesFile("sicredi.properties",
                "SIMULADOR_PAGE");

        driver.manage().deleteAllCookies();
        driver.navigate().to(urlSistema);
    }

    public void escolheCampoPerfil() {

    }

    public void insereCampoAplicar(Integer aplicar) {
        ReportUtils.logMensagem(Status.INFO, "Preenchendo Campo Aplicar.");
        try {
            esperaElementoVisivel(VALOR_APLICAR, 40);
            setInputText(aplicar.toString(), VALOR_APLICAR);
        } catch (Exception e) {
            ReportUtils.logMensagem(Status.FAIL, "Erro no Campo Aplicar.",
                    SeleniumUtils.getScreenshotReport());
            e.printStackTrace();
        }
    }

    public void insereCampoInvestir(Integer investir) {
        ReportUtils.logMensagem(Status.INFO, "Preenchendo Campo Investir.");
        try {
            esperaElementoVisivel(VALOR_INVESTIR, 40);
            setInputText(investir.toString(), VALOR_INVESTIR);
        } catch (Exception e) {
            ReportUtils.logMensagem(Status.FAIL, "Erro no Campo Investir.",
                    SeleniumUtils.getScreenshotReport());
            e.printStackTrace();
        }
    }

    public void insereCampoTempo(Integer tempo) {
        ReportUtils.logMensagem(Status.INFO, "Preenchendo Campo Tempo.");
        try {
            realizaScroll(TEMPO);
            esperaElementoVisivel(TEMPO, 40);
            setInputText(tempo.toString(), TEMPO);
        } catch (Exception e) {
            ReportUtils.logMensagem(Status.FAIL, "Erro no Campo Tempo.",
                    SeleniumUtils.getScreenshotReport());
            e.printStackTrace();
        }
    }

    public void escolheCampoPeriodo(String periodo) {
        ReportUtils.logMensagem(Status.INFO, "Escolhendo Campo Periodo.");
        try {
            selecionaOpcaoPorTexto(periodo, PERIODO);
        } catch (Exception e) {
            ReportUtils.logMensagem(Status.FAIL, "Erro no Campo Periodo.",
                    SeleniumUtils.getScreenshotReport());
            e.printStackTrace();
        }

    }

    public void gerarSimulacao() {
        ReportUtils.logMensagem(Status.INFO, "Gerando Simulação.");
        try {
            clicaCampo(BOTAO_SIMULAR);
        } catch (Exception e) {
            ReportUtils.logMensagem(Status.FAIL, "Erro ao Gerar Simulação.",
                    SeleniumUtils.getScreenshotReport());
            e.printStackTrace();
        }

    }

    public boolean validaGeracaoSimulacao() {
        return verificaExistenciaDeElementoNaTela(BOTAO_REFAZER_SIMULACAO);
    }

    public boolean verificaAlertaMenosDe20Reais(int aplicar, int investir) {
        if (aplicar < 2000) {
            return verificaExistenciaDeElementoNaTela(VALOR_APLICAR_ERROR);
        }
        if (investir < 2000) {
            return verificaExistenciaDeElementoNaTela(VALOR_INVESTIR_ERROR);
        }
        else return false;
    }

}
