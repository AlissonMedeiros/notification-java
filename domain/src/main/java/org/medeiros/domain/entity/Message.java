package org.medeiros.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Builder
public class Message {

	private String id;
	private LocalDateTime scheduleDate;
	private String body;
	private Recipient recipient;
	private CommunicationChannel channel;
	private Collection<Chat> chats;

}

