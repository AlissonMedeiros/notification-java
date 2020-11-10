package org.medeiros.adapter.repository;

import org.medeiros.adapter.repository.entity.MessageEntity;
import org.medeiros.domain.Message;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

	private static ModelMapper modelMapper = new ModelMapper();

	public MessageEntity toEntity(Message message) {
		return modelMapper.map(message, MessageEntity.class);
	}

	public Message toDomain(MessageEntity message) {
		return modelMapper.map(message, Message.class);
	}

}
