#language: pt
Funcionalidade: Demo
  Eu como investidor, desejo fazer uma simulação de investimento
  Para que eu saiba o valor ideal e quanto vou poupar no prazo escolhido

  Contexto:
    Dado que estou na tela do simulador de investimento

  @SIMULAÇÃO
  Esquema do Cenario: Simular investimento com menos de 20 reais
    Quando preencho o formulário com dados de aplicar <valorAplicar>, investir <valorInvestir>, tempo <tempo> e periodo <selectPeriodo> para o <nomeCenario>
    Entao uma mensagem aparecerá avisando valor minimo de 20 reais
    Exemplos:
      | nomeCenario       | valorAplicar | valorInvestir | tempo | selectPeriodo |
      | INVESTIR_19_REAIS | 1900         | 2000          | 12    | Meses         |