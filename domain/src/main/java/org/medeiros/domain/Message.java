package org.medeiros.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	private String id;
	private LocalDateTime scheduleDate;
	private String body;
	private Recipient recipient;
	private CommunicationChannel channel;
	private Collection<Chat> chats;

}

