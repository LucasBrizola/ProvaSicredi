# ProvaSicredi

Testes Automatizados para o simulador de investimentos do Sicredi encontrado no site: https://www.sicredi.com.br/html/ferramenta/simulador-investimento-poupanca/

-Tecnologias utilizadas: Java jdk 11, Chromedriver ver 83, Selenium 3.141.59, cucumber-java 6.0.0, log4j 1.2.17, junit 4.13, cucumber plugin para intellij.

#Rodando os testes via junit
-rodar a classe SimuladorTest
Será gerado um report dentro da pasta report com a data e hora do teste e screenshots de erros. Também será gerado um log na pasta logs com o cenário testado e o que ocorreu.

#Rodando os testes separadamente (requer plugin intellij)
-Entrar em src/test/java/features, escolher o cenario, clicar com botão direito e esolhar rodar cenário.
Será gerado um report dentro da pasta report com a data e hora do teste e screenshots de erros. Também será gerado um log na pasta logs com o cenário testado e o que ocorreu.