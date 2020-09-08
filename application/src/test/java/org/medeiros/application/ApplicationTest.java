package org.medeiros.application;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medeiros.NotificationApplication;
import org.medeiros.adapter.controller.v1.dto.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NotificationApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(resolver = ActiveProfileResolver.class)
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
		RestAssured.given()
			.contentType(ContentType.JSON)
			.body(MessageDto.builder()
				.channel(CommunicationChannelDto.WHATSAPP)
				.scheduleDate(now)
				.body("Hello")
				.recipient(RecipientDto.builder()
					.name("John James")
					.email("johm@jame.com")
					.phoneId("123")
					.phoneNumber("89898989")
					.build())
				.chats(List.of(ChatDto.builder()
					.date(now)
					.status(StatusDto.WAITING)
					.build()))
				.build())
			.when()
			.post("/v1/message/")
			.then()
			.statusCode(201)
			.body("id", notNullValue())
			.body("scheduleDate", equalTo(now.toString()));
	}

}
