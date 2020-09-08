package org.medeiros.adapter.controller.v1;

import org.junit.jupiter.api.Test;
import org.medeiros.adapter.controller.v1.dto.MessageResponseDto;
import org.medeiros.domain.Message;

import static org.assertj.core.api.Assertions.assertThat;

class MessageMapperTest {

	@Test
	public void whenHasEntityThenConvertToDto() {
		var message = Message.builder()
			.body("")
			.build();
		var dto = MessageMapper.toDto(message);
		assertThat(dto).isNotNull();
	}

	@Test
	public void whenHasDroThenConvertToEntity() {
		var message = MessageResponseDto.builder()
			.body("")
			.build();
		var entity = MessageMapper.toEntity(message);
		assertThat(entity).isNotNull();
	}

}
