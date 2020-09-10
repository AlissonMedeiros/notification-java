package org.medeiros.adapter.controller.v1;

import org.medeiros.adapter.controller.v1.dto.MessageCreateDto;
import org.medeiros.adapter.controller.v1.dto.MessageResponseDto;
import org.medeiros.domain.Message;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ControllerMessageMapper {

	private static ModelMapper modelMapper = new ModelMapper();

	public MessageResponseDto toDto(Message message) {
		return modelMapper.map(message, MessageResponseDto.class);
	}

	public Message toEntity(MessageCreateDto message) {
		return modelMapper.map(message, Message.class);
	}
}
