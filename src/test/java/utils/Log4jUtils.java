package utils;

import io.cucumber.java.Scenario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.*;

public class Log4jUtils extends SeleniumUtils {
	
	public static Logger logger;

	@SuppressWarnings("deprecation")
	public static void criaLog(Scenario cenario) {
		String dir = System.getProperty("user.dir");
		PropertyConfigurator.configure(dir + "\\src\\test\\resources\\log4j.properties");
		String filename = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		filename = filename.replace(".", "_");
		String nomeCenario = cenario.getName();
		nomeCenario = nomeCenario.replace(" ", "_");
		SeleniumUtils.criarDiretorio(dir + "\\logs");
		logger = LogManager.getLogger(Log4jUtils.class.getName());
		try {
			Logger.shutdown();
			SimpleLayout layout = new SimpleLayout();
			FileAppender appender = new FileAppender(layout, dir + "\\logs\\" + nomeCenario + "_" + filename + ".log",
					false);
			logger.addAppender(appender);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Logger getLog() {
		return logger;
	}
	
	public static void logMensagem(String tipo_mensagem, String mensagem) {
		if ("DEBUG".equals(tipo_mensagem)) {
			logger.debug(mensagem);
		} else if ("INFO".equals(tipo_mensagem)) {
			logger.info(mensagem);
		} else if ("WARN".equals(tipo_mensagem)) {
			logger.warn(mensagem);
		} else if ("ERROR".equals(tipo_mensagem)) {
			logger.error(mensagem);
		} else if ("FATAL".equals(tipo_mensagem)) {
			logger.fatal(mensagem);
		} else {
			logger.warn("Tipo de log: " + tipo_mensagem + " não existe.");
			logger.warn("Mensagem a ser enviada: " + mensagem);
		}
	}
}