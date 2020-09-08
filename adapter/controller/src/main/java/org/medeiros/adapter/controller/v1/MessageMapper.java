package org.medeiros.adapter.controller.v1;

import org.medeiros.adapter.controller.v1.dto.MessageCreateDto;
import org.medeiros.adapter.controller.v1.dto.MessageResponseDto;
import org.medeiros.domain.Message;
import org.modelmapper.ModelMapper;

public class MessageMapper {

	private static ModelMapper modelMapper = new ModelMapper();

	public static MessageResponseDto toDto(Message message) {
		return modelMapper.map(message, MessageResponseDto.class);
	}

	public static Message toEntity(MessageResponseDto message) {
		return modelMapper.map(message, Message.class);
	}

	public static Message toEntity(MessageCreateDto message) {
		return modelMapper.map(message, Message.class);
	}
}
