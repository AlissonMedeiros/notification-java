package org.medeiros.adapter.repository;

import org.medeiros.domain.Message;
import org.medeiros.adapter.repository.entity.MessageEntity;
import org.modelmapper.ModelMapper;

public class MessageMapper {

	private static ModelMapper modelMapper = new ModelMapper();

	public static MessageEntity toEntity(Message message) {
		return modelMapper.map(message, MessageEntity.class);
	}

	public static Message toDomain(MessageEntity message) {
		return modelMapper.map(message, Message.class);
	}

}
