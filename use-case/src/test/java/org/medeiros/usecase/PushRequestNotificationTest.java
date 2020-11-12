package org.medeiros.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medeiros.domain.CommunicationChannel;
import org.medeiros.domain.Message;
import org.medeiros.domain.Recipient;
import org.medeiros.domain.Status;
import org.medeiros.usecase.exception.NotificationException;
import org.medeiros.usecase.port.MessageRepository;
import org.medeiros.usecase.validator.MessageValidator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PushRequestNotificationTest {

	@InjectMocks
	private PushRequestNotification pushRequestNotification;
	@Mock
	private MessageRepository repository;
	@Mock
	private MessageValidator messageValidator;

	@Test
	public void whenReceiveNotificationThenSchedule() throws NotificationException {
		when(repository.create(any())).thenAnswer(a -> a.getArgument(0));
		var message = pushRequestNotification.push(Message.builder()
			.body("Test")
			.scheduleDate(LocalDateTime.now())
			.channel(CommunicationChannel.WHATSAPP)
			.recipient(Recipient.builder()
				.name("João Graça")
				.phoneNumber("047 977665544")
				.build())
			.build());
		assertThat(message).isNotNull()
			.satisfies(m -> {
				assertThat(m.getChats()).hasSize(1)
					.first()
					.satisfies(c -> {
						assertThat(c.getStatus()).isEqualTo(Status.WAITING);
						assertThat(c.getDate()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
					});
			});
		verify(repository).create(message);
		verify(messageValidator).validate(message);
	}

}
