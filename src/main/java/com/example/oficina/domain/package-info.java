/**
 * Pacote JAXB para o modelo de dominio.
 *
 * A entidade tambem e exposta nas respostas SOAP, por isso o namespace XML
 * tem de ficar alinhado com o contrato XSD usado pelo endpoint.
 */
@jakarta.xml.bind.annotation.XmlSchema(
    namespace = "http://example.com/oficina",
    elementFormDefault = jakarta.xml.bind.annotation.XmlNsForm.QUALIFIED
)
package com.example.oficina.domain;
