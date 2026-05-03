/**
 * Pacote JAXB para as mensagens SOAP usadas pelo endpoint.
 *
 * Em Spring-WS, estas classes sao convertidas para XML e a partir de XML
 * usando o namespace definido aqui. Isto segue a ideia contract-first
 * mostrada nas aulas de SOAP, WSDL e XSD.
 */
@jakarta.xml.bind.annotation.XmlSchema(
    namespace = "http://example.com/oficina",
    elementFormDefault = jakarta.xml.bind.annotation.XmlNsForm.QUALIFIED
)
package com.example.oficina.ws;
