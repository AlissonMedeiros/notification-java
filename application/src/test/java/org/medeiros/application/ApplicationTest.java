package org.medeiros.application;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medeiros.adapter.controller.v1.dto.CommunicationChannelDto;
import org.medeiros.adapter.controller.v1.dto.MessageCreateDto;
import org.medeiros.adapter.controller.v1.dto.RecipientDto;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test-application.properties")
public class ApplicationTest {

	@LocalServerPort
	int serverPort;

	@BeforeEach
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = serverPort;
	}

	@Test
	public void whenCreateNewScheduleThenReturn() {
		var now = LocalDateTime.now();
		String id = given()
			.contentType(ContentType.JSON)
			.body(MessageCreateDto.builder()
				.channel(CommunicationChannelDto.WHATSAPP)
				.scheduleDate(now)
				.body("Hello")
				.recipient(RecipientDto.builder()
					.name("John James")
					.email("johm@jame.com")
					.phoneId("123")
					.phoneNumber("89898989")
					.build())
				.build())
			.when()
			.post("/v1/message/")
			.then()
			.statusCode(201)
			.body("id", notNullValue())
			.body("scheduleDate", equalTo(now.toString()))
			.body("scheduleDate", equalTo(now.toString()))
			.body("body", equalTo("Hello"))
			.body("recipient.name", equalTo("John James"))
			.body("chats[0].status", equalTo("WAITING"))
			.extract()
			.path("id");

		given()
			.when()
			.get("/v1/message/{id}", id)
			.then()
			.statusCode(200)
			.body("scheduleDate", equalTo(now.toString()))
			.body("scheduleDate", equalTo(now.toString()))
			.body("body", equalTo("Hello"))
			.body("recipient.name", equalTo("John James"))
			.body("recipient.phoneId", equalTo("123"))
			.body("recipient.email", equalTo("johm@jame.com"))
			.body("recipient.phoneNumber", equalTo("89898989"))
			.body("chats[0].status", equalTo("WAITING"));
	}

	@Test
	public void whenHasInvalidIdThenReturnError() {
		given()
			.when()
			.get("/v1/message/{id}", "123123")
			.then()
			.statusCode(404)
			.body("message", equalTo("Mensagem com o id: 123123 não foi encontrada, confira se o identificador está correto e tente novamente."));
	}

}
