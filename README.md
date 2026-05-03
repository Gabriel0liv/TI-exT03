# Exame Tipo - Exercicio 3

Base do exercicio 3: SOAP em Spring com JPA e H2.

Este projeto foi organizado para seguir a logica das aulas:

1. primeiro define-se o **contrato XML**;
2. depois publica-se o **Web Service**;
3. depois cria-se a **logica de negocio**;
4. por fim liga-se tudo a uma **base de dados em memoria**.

## 1. O que este exercicio quer mostrar

O objetivo e perceber como um sistema web funciona por camadas:

- um cliente envia um pedido SOAP em XML;
- o Spring recebe esse XML;
- o Spring converte o XML para um objeto Java;
- esse objeto passa pela camada de negocio;
- a camada de negocio fala com a base de dados;
- o resultado volta a ser convertido para XML e devolvido ao cliente.

Na pratica, isto mostra a ligacao entre:

- **SOAP**
- **WSDL**
- **XSD**
- **Spring-WS**
- **JPA**
- **H2**

## 2. A ordem de criacao do projeto

### Passo 1: `assistencias.xsd`

Primeiro cria-se o ficheiro [assistencias.xsd](src/main/resources/assistencias.xsd).

Este ficheiro e o contrato do servico. Ele define:

- quais sao os tipos de dados;
- quais sao os campos de uma assistencia;
- quais sao os pedidos;
- quais sao as respostas;
- quais sao os estados validos.

Isto e importante porque, nas aulas, a ideia de SOAP foi sempre a de um servico com contrato bem definido.

### Passo 2: `package-info.java`

Depois ajustam-se os [package-info.java](src/main/java/com/example/oficina/domain/package-info.java) e [package-info.java](src/main/java/com/example/oficina/ws/package-info.java).

Eles existem para alinhar o namespace XML dos pacotes com o contrato XSD.

Isto vem a seguir ao XSD porque o namespace tem de ser o mesmo entre o contrato e as classes JAXB.

### Passo 3: `Assistencia.java` e `EstadoAssistencia.java`

Depois cria-se [Assistencia.java](src/main/java/com/example/oficina/domain/Assistencia.java) e [EstadoAssistencia.java](src/main/java/com/example/oficina/domain/EstadoAssistencia.java).

Estas classes representam os dados reais que o serviço vai transportar.

Primeiro modela-se a entidade porque a camada SOAP e a camada de negocio vao usar esta estrutura.

`Assistencia.java` é a estrutura principal dos dados.
`EstadoAssistencia.java` existe porque o estado da assistência precisa de valores bem definidos.

### Passo 4: `WebServiceConfig.java`

Depois cria-se [WebServiceConfig.java](src/main/java/com/example/oficina/config/WebServiceConfig.java).

Esta classe:

- ativa o suporte a SOAP no Spring;
- regista o `MessageDispatcherServlet`;
- publica o WSDL em `/ws/oficina.wsdl`;
- liga o XSD ao WSDL.

Aqui o Spring passa a saber onde esta o servico e qual e o contrato que ele deve expor.

Este passo vem depois do XSD e dos tipos porque a configuracao precisa de apontar para esse contrato.

### Passo 5: `AssistenciaRepository.java`

Depois cria-se [AssistenciaRepository.java](src/main/java/com/example/oficina/repository/AssistenciaRepository.java).

Esta classe e a primeira a tocar nos dados.

Ela nao contem SQL manual. Em vez disso, o Spring Data gera a parte CRUD automaticamente.

Ela e a ponte direta entre o Java e a base de dados.

No projeto de exame ela fica em memoria, mas no modelo das aulas esta camada vem antes do service porque o service depende dela.

### Passo 6: `AssistenciaService.java` e `AssistenciaServiceImpl.java`

Depois cria-se [AssistenciaService.java](src/main/java/com/example/oficina/service/AssistenciaService.java) e [AssistenciaServiceImpl.java](src/main/java/com/example/oficina/service/AssistenciaServiceImpl.java).

Aqui ficam as regras do exercicio:

- criar assistencias;
- procurar assistencias;
- eliminar assistencias;
- aplicar o estado inicial `RECEBIDO`.

Esta camada vem depois do repository porque o service precisa de uma forma de ler e escrever dados.

### Passo 7: beans do SOAP em `ws`

Depois cria-se a familia de classes em `ws`:

- [AdicionarAssistenciaRequest.java](src/main/java/com/example/oficina/ws/AdicionarAssistenciaRequest.java)
- [AdicionarAssistenciaResponse.java](src/main/java/com/example/oficina/ws/AdicionarAssistenciaResponse.java)
- [DetalheAssistenciaRequest.java](src/main/java/com/example/oficina/ws/DetalheAssistenciaRequest.java)
- [DetalheAssistenciaResponse.java](src/main/java/com/example/oficina/ws/DetalheAssistenciaResponse.java)
- [EliminarAssistenciaRequest.java](src/main/java/com/example/oficina/ws/EliminarAssistenciaRequest.java)
- [EliminarAssistenciaResponse.java](src/main/java/com/example/oficina/ws/EliminarAssistenciaResponse.java)

Estas classes representam o XML de entrada e de saida.

Elas sao criadas depois porque dependem da entidade, do contrato e da camada de negocio.

### Passo 8: `AssistenciaEndpoint.java`

Depois cria-se [AssistenciaEndpoint.java](src/main/java/com/example/oficina/ws/AssistenciaEndpoint.java).

Esta e a classe que recebe os pedidos SOAP.

Ela nao deve falar diretamente com a BD. Em vez disso:

- recebe o XML;
- converte-o para um bean request;
- chama o service;
- devolve um bean response.

Este passo vem depois dos beans porque o endpoint usa esses beans como entrada e saida.

### Passo 9: `application.properties`

Depois configura-se [application.properties](src/main/resources/application.properties).

Aqui ficam os detalhes de arranque:

- porta do servidor;
- configuracao da H2;
- comportamento da JPA.

Esta configuracao vem no fim porque depende de tudo o resto e define o ambiente onde o projeto corre.

### Passo 10: `OficinaSoapApplication.java`

Por fim cria-se [OficinaSoapApplication.java](src/main/java/com/example/oficina/OficinaSoapApplication.java).

Esta e a classe de arranque do Spring Boot.

Ela vem no fim porque so faz sentido depois de existirem:

- o contrato;
- a entidade;
- o repository;
- o service;
- o endpoint;
- a configuracao.

## 3. Conceitos fundamentais

### O que e um servico

Um servico e uma funcionalidade que o sistema disponibiliza a outros sistemas.

Neste exercicio, o servico oferece tres operacoes:

- adicionar assistencia;
- ver o detalhe de uma assistencia;
- eliminar assistencia.

### O que e um Web Service

Um Web Service e um servico acessivel pela rede.

Aqui o cliente nao chama diretamente uma classe Java.
O cliente chama o servico atraves de mensagens XML.

### O que e WS

`WS` e apenas a abreviatura de **Web Service**.

### O que e SOAP

SOAP e um protocolo de comunicacao baseado em XML.

Serve para enviar pedidos e respostas entre cliente e servidor.

No exame, o SOAPUI e a ferramenta mais pratica para testar este tipo de servico.

### O que e WSDL

WSDL significa *Web Services Description Language*.

O WSDL e o documento que descreve o servico:

- qual e o endereco;
- que operacoes existem;
- que mensagens sao enviadas;
- que mensagens sao recebidas.

### O que e XSD

XSD significa *XML Schema Definition*.

O XSD define a estrutura valida das mensagens XML:

- nomes dos elementos;
- tipos dos campos;
- valores permitidos;
- sequencia dos dados.

No modelo contract-first, o XSD vem primeiro.

### O que e contract-first

Contract-first significa:

- primeiro defines o contrato;
- depois implementas o servico.

Nas aulas de Spring-WS, este e o estilo mais importante.

### O que e JPA

JPA significa *Java Persistence API*.

Serve para mapear classes Java para tabelas da base de dados.

Em vez de escrever SQL manual para tudo, usas anotacoes e repositories.

### O que e H2

H2 e uma base de dados pequena e leve.

Neste projeto esta em modo memoria, o que significa:

- a base e criada quando a aplicacao arranca;
- os dados existem enquanto a aplicacao esta aberta;
- quando fechas a aplicacao, os dados desaparecem.

### Onde fica a BD

A base de dados fica em memoria dentro da propria aplicacao Spring.

Configuracao usada:

- nome da base: `oficina`
- JDBC URL: `jdbc:h2:mem:oficina`

Tambem podes abrir a consola da H2:

```text
http://localhost:8080/h2-console
```

### O que e um bean

Um bean, em Spring, e um objeto que o Spring cria e gere.

Pode ser:

- um `@Service`;
- um `@Repository`;
- um `@Endpoint`;
- um bean JAXB de pedido ou resposta;
- uma entidade JPA.

Na pratica, um bean e algo que o Spring conhece, instancia e injeta onde for necessario.

## 4. Como cada camada interage

### Fluxo completo

1. O utilizador envia um pedido no SOAPUI.
2. O pedido chega ao `MessageDispatcherServlet`.
3. O Spring olha para o `localPart` e para o `namespace`.
4. O pedido e encaminhado para o metodo certo no `AssistenciaEndpoint`.
5. O endpoint transforma o XML num bean Java.
6. O endpoint chama o `AssistenciaService`.
7. O service aplica as regras de negocio.
8. O service usa o `AssistenciaRepository`.
9. O repository grava ou le na H2.
10. O resultado volta para o endpoint.
11. O Spring converte o resultado em XML.
12. O SOAPUI mostra a resposta.

### Relacao entre as camadas

- **Endpoint**: fala com SOAP.
- **Service**: decide o que fazer.
- **Repository**: fala com a BD.
- **Entity**: representa os dados da BD.
- **Request/Response beans**: representam o XML do SOAP.

## 5. Porque existem tantas anotacoes

### `@Endpoint`

Marca a classe como ponto de entrada SOAP.

### `@PayloadRoot`

Diz ao Spring qual elemento XML chama qual metodo.

### `@ResponsePayload`

Diz que o retorno do metodo deve ser convertido para XML.

### `@Service`

Marca a camada de negocio.

### `@Repository`

Marca a camada de acesso a dados.

### `@Entity`

Marca uma classe para ser guardada na base de dados.

### `@XmlRootElement`

Permite que a classe seja usada como XML no SOAP.

### `@XmlSchema`

Define o namespace XML do pacote.

## 6. Ficheiros principais

- `OficinaSoapApplication.java`: arranque da aplicacao Spring Boot.
- `WebServiceConfig.java`: configuracao do SOAP e do WSDL.
- `Assistencia.java`: entidade e modelo XML.
- `EstadoAssistencia.java`: valores permitidos para o estado.
- `AssistenciaRepository.java`: acesso a dados.
- `AssistenciaService.java`: contrato da logica de negocio.
- `AssistenciaServiceImpl.java`: implementacao da logica de negocio.
- `AssistenciaEndpoint.java`: entrada SOAP.
- `assistencias.xsd`: contrato XML.
- `application.properties`: configuracao da porta, JPA e H2.

## 7. WSDL

Depois de arrancar a aplicacao:

```text
http://localhost:8080/ws/oficina.wsdl
```

Esse endereco mostra o contrato gerado pelo Spring a partir do XSD.

## 8. Operacoes disponiveis

### `adicionarAssistencia`

- cria uma assistencia nova;
- define o estado inicial como `RECEBIDO`;
- devolve o objeto criado.

### `detalheAssistencia`

- recebe um `idAssistencia`;
- procura o registo na base;
- devolve os dados ou indica que nao encontrou.

### `eliminarAssistencia`

- recebe um `idAssistencia`;
- tenta remover o registo;
- devolve `SUCESSO` ou `INSUCESSO`.


