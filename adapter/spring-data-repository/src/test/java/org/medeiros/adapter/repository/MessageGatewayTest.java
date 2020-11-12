package org.medeiros.adapter.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medeiros.adapter.repository.entity.MessageEntity;
import org.medeiros.adapter.repository.entity.MessageEntityRepository;
import org.medeiros.domain.Message;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageGatewayTest {

	@InjectMocks
	private MessageGateway messageGateway;
	@Mock
	private MessageEntityRepository repository;
	@Spy
	private MessageMapper messageMapper = new MessageMapper();

	@Test
	public void whenCreateThenCallRepository() {
		var id = "123";
		var message = Message.builder()
			.id(id)
			.chats(List.of(org.medeiros.domain.Chat.builder().build()))
			.build();
		var createdMessage = messageGateway.create(message);
		assertThat(createdMessage).isNotNull();
		assertThat(createdMessage.getId()).isEqualTo(id);
		ArgumentCaptor<MessageEntity> captor = ArgumentCaptor.forClass(MessageEntity.class);
		verify(repository).save(captor.capture());
		var messageEntity = captor.getValue();
		assertThat(messageEntity.getChats().iterator().next().getMessage().getId()).isEqualTo(123L);

	}

	@Test
	public void whenDeleteThenCallRepository() {
		var id = 123L;
		messageGateway.delete(String.valueOf(id));
		verify(repository).deleteById(id);
	}

	@Test
	public void whenExistThenCallRepository() {
		var id = 123L;
		when(repository.existsById(id)).thenReturn(true);
		var result = messageGateway.exists(String.valueOf(id));
		assertThat(result).isTrue();
		verify(repository).existsById(id);
	}

	@Test
	public void whenNotExistThenCallRepository() {
		var id = 123L;
		when(repository.existsById(id)).thenReturn(false);
		var result = messageGateway.exists(String.valueOf(id));
		assertThat(result).isFalse();
		verify(repository).existsById(id);
	}

	@Test
	public void whenFindByExistentIdThenCallRepository() {
		var id = 123L;
		when(repository.findById(id)).thenReturn(Optional.of(MessageEntity.builder().id(id).build()));
		var result = messageGateway.find(String.valueOf(id));
		assertThat(result.get().getId()).isEqualTo("123");
		verify(repository).findById(id);
	}

	@Test
	public void whenFindByInvalidIdThenCallRepository() {
		var id = 123L;
		when(repository.findById(id)).thenReturn(Optional.empty());
		var result = messageGateway.find(String.valueOf(id));
		assertThat(result.isEmpty()).isTrue();
		verify(repository).findById(id);
	}

}
