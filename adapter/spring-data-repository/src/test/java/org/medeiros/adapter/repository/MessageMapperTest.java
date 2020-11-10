package org.medeiros.adapter.repository;

import org.junit.jupiter.api.Test;
import org.medeiros.adapter.repository.entity.CommunicationChannelEntity;
import org.medeiros.adapter.repository.entity.MessageEntity;
import org.medeiros.adapter.repository.entity.RecipientEntity;
import org.medeiros.adapter.repository.entity.StatusEntity;
import org.medeiros.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageMapperTest {

	private final MessageMapper mapper = new MessageMapper();

	@Test
	public void whenBodyHasDomainThenConvertToEntity() {
		var message = MessageEntity.builder()
			.body("Test")
			.build();
		var domain = mapper.toDomain(message);
		assertThat(domain).isNotNull();
		assertThat(domain.getBody()).isEqualTo("Test");
	}

	@Test
	public void whenHasBodyInCreateDtoThenConvertToDomain() {
		var message = Message.builder()
			.body("Test")
			.build();
		var entity = mapper.toEntity(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getBody()).isEqualTo("Test");
	}

	@Test
	public void whenHasDateThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.scheduleDate(date)
			.build();
		var domain = mapper.toEntity(message);
		assertThat(domain).isNotNull();
		assertThat(domain.getScheduleDate()).isEqualTo(date);
	}

	@Test
	public void whenHasDateInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageEntity.builder()
			.scheduleDate(date)
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getScheduleDate()).isEqualTo(date);
	}

	@Test
	public void whenEntityHasWhatsAppThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChannel()).isEqualTo(CommunicationChannelEntity.WHATSAPP);
	}

	@Test
	public void whenHasWhatsAppInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageEntity.builder()
			.channel(CommunicationChannelEntity.WHATSAPP)
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getChannel()).isEqualTo(CommunicationChannel.WHATSAPP);
	}

	@Test
	public void whenEntityHasEmailThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.channel(CommunicationChannel.EMAIL)
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChannel()).isEqualTo(CommunicationChannelEntity.EMAIL);
	}

	@Test
	public void whenHasEmailInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageEntity.builder()
			.channel(CommunicationChannelEntity.EMAIL)
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getChannel()).isEqualTo(CommunicationChannel.EMAIL);
	}

	@Test
	public void whenEntityHasSmsThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.channel(CommunicationChannel.SMS)
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChannel()).isEqualTo(CommunicationChannelEntity.SMS);
	}

	@Test
	public void whenHasSmsInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageEntity.builder()
			.channel(CommunicationChannelEntity.SMS)
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getChannel()).isEqualTo(CommunicationChannel.SMS);
	}

	@Test
	public void whenEntityHasPushThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.channel(CommunicationChannel.PUSH)
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChannel()).isEqualTo(CommunicationChannelEntity.PUSH);
	}

	@Test
	public void whenHasPushInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageEntity.builder()
			.channel(CommunicationChannelEntity.PUSH)
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getChannel()).isEqualTo(CommunicationChannel.PUSH);
	}

	@Test
	public void whenWaitingStatusOnChannelThenConvertToDto() {
		var message = Message.builder()
			.chats(List.of(Chat.builder().status(Status.WAITING).build()))
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChats()).hasSize(1).first().satisfies(
			c -> assertThat(c.getStatus()).isEqualTo(StatusEntity.WAITING)
		);
	}

	@Test
	public void whenSentStatusOnChannelThenConvertToDto() {
		var message = Message.builder()
			.chats(List.of(Chat.builder().status(Status.SENT).build()))
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChats()).hasSize(1).first().satisfies(
			c -> assertThat(c.getStatus()).isEqualTo(StatusEntity.SENT)
		);
	}

	@Test
	public void whenSendingStatusOnChannelThenConvertToDto() {
		var message = Message.builder()
			.chats(List.of(Chat.builder().status(Status.SENDING).build()))
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChats()).hasSize(1).first().satisfies(
			c -> assertThat(c.getStatus()).isEqualTo(StatusEntity.SENDING)
		);
	}

	@Test
	public void whenDateOnChannelThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.chats(List.of(Chat.builder().date(date).build()))
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChats()).hasSize(1).first().satisfies(
			c -> assertThat(c.getDate()).isEqualTo(date)
		);
	}

	@Test
	public void whenHasIdThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.id("123")
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getId()).isEqualTo("123");
	}

	@Test
	public void whenEntityHasRecipientNameThenConvertToDto() {
		var message = Message.builder()
			.recipient(Recipient.builder()
				.name("Alisson")
				.build())
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getRecipient().getName()).isEqualTo("Alisson");
	}

	@Test
	public void whenHasRecipientNameInCreateDtoThenConvertToEntity() {
		var message = MessageEntity.builder()
			.recipient(RecipientEntity.builder()
				.name("Alisson")
				.build())
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getRecipient().getName()).isEqualTo("Alisson");
	}

	@Test
	public void whenEntityHasRecipientEmailThenConvertToDto() {
		var message = Message.builder()
			.recipient(Recipient.builder()
				.email("alisson@mail.com")
				.build())
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getRecipient().getEmail()).isEqualTo("alisson@mail.com");
	}

	@Test
	public void whenHasRecipientEmailInCreateDtoThenConvertToEntity() {
		var message = MessageEntity.builder()
			.recipient(RecipientEntity.builder()
				.email("alisson@mail.com")
				.build())
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getRecipient().getEmail()).isEqualTo("alisson@mail.com");
	}

	@Test
	public void whenEntityHasRecipientPhoneIdThenConvertToDto() {
		var message = Message.builder()
			.recipient(Recipient.builder()
				.phoneId("123")
				.build())
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getRecipient().getPhoneId()).isEqualTo("123");
	}

	@Test
	public void whenHasRecipientPhoneIdInCreateDtoThenConvertToEntity() {
		var message = MessageEntity.builder()
			.recipient(RecipientEntity.builder()
				.phoneId("123")
				.build())
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getRecipient().getPhoneId()).isEqualTo("123");
	}

	@Test
	public void whenEntityHasRecipientPhoneNumberThenConvertToDto() {
		var message = Message.builder()
			.recipient(Recipient.builder()
				.phoneNumber("999988887777")
				.build())
			.build();
		var dto = mapper.toEntity(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getRecipient().getPhoneNumber()).isEqualTo("999988887777");
	}

	@Test
	public void whenHasRecipientPhoneNumberInCreateDtoThenConvertToEntity() {
		var message = MessageEntity.builder()
			.recipient(RecipientEntity.builder()
				.phoneNumber("999988887777")
				.build())
			.build();
		var entity = mapper.toDomain(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getRecipient().getPhoneNumber()).isEqualTo("999988887777");
	}

}
