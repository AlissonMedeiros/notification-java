package org.medeiros.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.medeiros.domain.entity.Message;

class PushRequestNotificationTest {

	private PushRequestNotification pushRequestNotification = new PushRequestNotification();

	@Test
	public void whenReceiveNotificationThenSchedule() {
		var message = pushRequestNotification.push(Message.builder().build());
		Assertions.assertThat(message).isNotNull();
	}

}
