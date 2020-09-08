package org.medeiros.adapter.controller.v1;

import org.medeiros.adapter.controller.v1.dto.MessageDto;
import org.medeiros.domain.Message;
import org.modelmapper.ModelMapper;

public class MessageMapper {

	private static ModelMapper modelMapper = new ModelMapper();

	public static MessageDto toDto(Message message) {
		return modelMapper.map(message, MessageDto.class);
	}

	public static Message toEntity(MessageDto message) {
		return modelMapper.map(message, Message.class);
	}

}
