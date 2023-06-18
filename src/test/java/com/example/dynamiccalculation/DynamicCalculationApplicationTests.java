package com.example.dynamiccalculation;

import com.example.dynamiccalculation.data.FormulaRepositoryImpl;
import io.spring.guides.gs_producing_web_service.Formula;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class DynamicCalculationApplicationTests {
	@Autowired
	private WebTestClient webClient;
	private String sendCorrectRequest(String request) {
		return webClient.post().uri("/ws")
				.contentType(MediaType.TEXT_XML)
				.accept(MediaType.TEXT_XML)
				.body(BodyInserters.fromObject(request))
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.returnResult().getResponseBody();
	}
	private String sendIncorrectRequest(String request) {
		return webClient.post().uri("/ws")
				.contentType(MediaType.TEXT_XML)
				.accept(MediaType.TEXT_XML)
				.body(BodyInserters.fromObject(request))
				.exchange()
				.expectStatus().is5xxServerError()
				.expectBody(String.class)
				.returnResult().getResponseBody();
	}
	@Test
	public void TestingCreatingFormulaAndCheckCorrectResponseOnFirstExample() {
		assertThat(sendCorrectRequest(creatingFormulaRequest(0.001))).isEqualTo(firstCreatingFormulaExpectedXml());
		// ноль целых одна тысячная
	}

	@Test
	public void TestingCreatingFormulaAndCheckCorrectResponseOnSecondExample() {
		assertThat(sendCorrectRequest(creatingFormulaRequest(123456789.98765))).isEqualTo(secondCreatingFormulaExpectedXml());
		// сто двадцать три миллиона четыреста пятьдесят шесть тысяч семьсот восемьдесят девять целых
		// девяносто восемь тысяч семьсот шестьдесят пять стотысячных
	}

	@Test
	public void TestingCreatingFormulaAndCheckCorrectResponseOnThirdExample() {
		assertThat(sendCorrectRequest(creatingFormulaRequest(12.112))).isEqualTo(thirdCreatingFormulaExpectedXml());
		// двенадцать целые сто двенадцать тысячных
	}

	@Test
	public void TestingCreatingFormulaAndCheckCorrectResponseOnFourthExample() {
		assertThat(sendCorrectRequest(creatingFormulaRequest(1.1))).isEqualTo(fourthCreatingFormulaExpectedXml());
		// одна целая одна десятая
	}

	@Test
	public void TestingCreatingFormulaAndCheckCorrectResponseOnFourthExampleWithIncorrectNumber() {
		assertThat(sendIncorrectRequest(creatingFormulaRequest(-100.4))).isEqualTo(fifthCreatingFormulaExpectedXml());
	}

	@Test
	public void TestingGettingFormulaAndCheckCorrectResponseOnFourthExample() {
		assertThat(sendCorrectRequest(gettingFormulaRequest(2))).isEqualTo(firstGettingFormulaExpectedXml());
		// двадцать двe целые двенадцать сотых
	}

	@Test
	public void TestingGettingFormulaAndCheckCorrectResponseOnFourthExampleWithErrorId() {
		assertThat(sendIncorrectRequest(gettingFormulaRequest(-12))).isEqualTo(secondGettingFormulaExpectedXml());
	}
	private String creatingFormulaRequest(Double number) {
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
				"                  xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\">\n" +
				"    <soapenv:Header/>\n" +
				"    <soapenv:Body>\n" +
				"        <gs:createFormulaRequest>\n" +
				"            <gs:formula>" + number + "</gs:formula>\n" +
				"        </gs:createFormulaRequest>\n" +
				"    </soapenv:Body>\n" +
				"</soapenv:Envelope>";
	}
	private String gettingFormulaRequest(Integer id) {
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
				"                  xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\">\n" +
				"    <soapenv:Header/>\n" +
				"    <soapenv:Body>\n" +
				"        <gs:getFormulaRequest>\n" +
				"            <gs:id>" + id + "</gs:id>\n" +
				"        </gs:getFormulaRequest>\n" +
				"    </soapenv:Body>\n" +
				"</soapenv:Envelope>";
	}
	private String firstCreatingFormulaExpectedXml() {
		return "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns2:formulaResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\"><ns2:id>45</ns2:id><ns2:number>0.001</ns2:number><ns2:formula>ноль целых одна тысячная</ns2:formula></ns2:formulaResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
	}
	private String secondCreatingFormulaExpectedXml() {
		return "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns2:formulaResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\"><ns2:id>29</ns2:id><ns2:number>1.2345678998765E8</ns2:number><ns2:formula>сто двадцать три миллиона четыреста пятьдесят шесть тысяч семьсот восемьдесят девять целых девяносто восемь тысяч семьсот шестьдесят пять стотысячных</ns2:formula></ns2:formulaResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
	}
	private String thirdCreatingFormulaExpectedXml() {
		return "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns2:formulaResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\"><ns2:id>28</ns2:id><ns2:number>12.112</ns2:number><ns2:formula>двенадцать целые сто двенадцать тысячных</ns2:formula></ns2:formulaResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
	}
	private String fourthCreatingFormulaExpectedXml() {
		return "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns2:formulaResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\"><ns2:id>27</ns2:id><ns2:number>1.1</ns2:number><ns2:formula>одна целая одна десятая</ns2:formula></ns2:formulaResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
	}
	private String fifthCreatingFormulaExpectedXml() {
		return "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><SOAP-ENV:Fault><faultcode>SOAP-ENV:Server</faultcode><faultstring xml:lang=\"en\">createFormula.formula: должно быть больше, чем или равно 0</faultstring></SOAP-ENV:Fault></SOAP-ENV:Body></SOAP-ENV:Envelope>";
	}
	private String firstGettingFormulaExpectedXml() {
		return "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns2:formulaResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\"><ns2:id>2</ns2:id><ns2:number>22.12</ns2:number><ns2:formula>двадцать двe целые двенадцать сотых</ns2:formula></ns2:formulaResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
	}
	private String secondGettingFormulaExpectedXml() {
		return "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><SOAP-ENV:Fault><faultcode>SOAP-ENV:Server</faultcode><faultstring xml:lang=\"en\">getFormula.id: должно быть не меньше 0</faultstring></SOAP-ENV:Fault></SOAP-ENV:Body></SOAP-ENV:Envelope>";
	}
}
