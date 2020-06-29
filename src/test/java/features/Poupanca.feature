#language: pt
Funcionalidade: Simular Investimento
  Eu como investidor, desejo fazer uma simulação de investimento
  Para que eu saiba o valor ideal e quanto vou poupar no prazo escolhido

  @GERA_SIMULAÇÃO
  Esquema do Cenario: Gerar uma simulação com dados válidos
    Dado que estou na tela do simulador de investimento
    Quando preencho o formulário com dados de aplicar "<valorAplicar>", investir "<valorInvestir>", tempo "<tempo>" e periodo "<selectPeriodo>" para o "<nomeCenario>"
    Entao a simulação é gerada com sucesso
    Exemplos:
      | nomeCenario      | valorAplicar | valorInvestir | tempo | selectPeriodo |
      | SIMULACAO_GERADA | 2000         | 2000          | 12    | Meses         |

  @VALOR_INFERIOR
  Esquema do Cenario: Simular investimento com menos de 20 reais nos campos
    Dado que estou na tela do simulador de investimento
    Quando preencho o formulário com dados de aplicar "<valorAplicar>", investir "<valorInvestir>", tempo "<tempo>" e periodo "<selectPeriodo>" para o "<nomeCenario>"
    Entao uma mensagem aparecerá avisando valor minimo de 20 reais
    Exemplos:
      | nomeCenario      | valorAplicar | valorInvestir | tempo | selectPeriodo |
      | APLICAR_19_REAIS | 1900         | 2000          | 12    | Meses         |
      | POUPAR_19_REAIS  | 2000         | 1900          | 12    | Meses         |

  @API
  Esquema do Cenario: Testar API
    Quando eu fizer uma requisição get para o endpoint da API para o cenário "<nomeCenario>"
    Entao devo verificar que os dados retornados estão corretos
    Exemplos:
      | nomeCenario |
      | getApi      |