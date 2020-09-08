package org.medeiros.adapter.controller.v1.dto;

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
public class MessageResponseDto {

	private String id;
	private LocalDateTime scheduleDate;
	private String body;
	private RecipientDto recipient;
	private CommunicationChannelDto channel;
	private Collection<ChatResponseDto> chats;

}

