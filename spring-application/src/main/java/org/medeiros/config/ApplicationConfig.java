package org.medeiros.config;

import org.medeiros.usecase.DeleteRequestNotification;
import org.medeiros.usecase.FindRequestNotification;
import org.medeiros.usecase.PushRequestNotification;
import org.medeiros.usecase.port.MessageRepository;
import org.medeiros.usecase.validator.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Autowired
	private MessageRepository messageRepository;

	@Bean
	public MessageValidator messageValidator() {
		return new MessageValidator();
	}

	@Bean
	public DeleteRequestNotification deleteRequestNotification() {
		return new DeleteRequestNotification(messageRepository);
	}

	@Bean
	public FindRequestNotification findRequestNotification() {
		return new FindRequestNotification(messageRepository);
	}

	@Bean
	public PushRequestNotification pushRequestNotification() {
		return new PushRequestNotification(messageRepository, messageValidator());
	}

}
