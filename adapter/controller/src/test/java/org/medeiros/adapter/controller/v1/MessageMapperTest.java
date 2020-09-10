package org.medeiros.adapter.controller.v1;

import org.junit.jupiter.api.Test;
import org.medeiros.adapter.controller.v1.dto.CommunicationChannelDto;
import org.medeiros.adapter.controller.v1.dto.MessageCreateDto;
import org.medeiros.adapter.controller.v1.dto.RecipientDto;
import org.medeiros.adapter.controller.v1.dto.StatusResponseDto;
import org.medeiros.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageMapperTest {

	private final ControllerMessageMapper mapper = new ControllerMessageMapper();

	@Test
	public void whenBodyHasEntityThenConvertToDto() {
		var message = Message.builder()
			.body("Test")
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getBody()).isEqualTo("Test");
	}

	@Test
	public void whenHasBodyInCreateDtoThenConvertToEntity() {
		var message = MessageCreateDto.builder()
			.body("Test")
			.build();
		var entity = mapper.toEntity(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getBody()).isEqualTo("Test");
	}

	@Test
	public void whenDateHasEntityThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.scheduleDate(date)
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getScheduleDate()).isEqualTo(date);
	}

	@Test
	public void whenHasDateInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageCreateDto.builder()
			.scheduleDate(date)
			.build();
		var entity = mapper.toEntity(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getScheduleDate()).isEqualTo(date);
	}

	@Test
	public void whenEntityHasWhatsAppThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChannel()).isEqualTo(CommunicationChannelDto.WHATSAPP);
	}

	@Test
	public void whenHasWhatsAppInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageCreateDto.builder()
			.channel(CommunicationChannelDto.WHATSAPP)
			.build();
		var entity = mapper.toEntity(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getChannel()).isEqualTo(CommunicationChannel.WHATSAPP);
	}

	@Test
	public void whenEntityHasEmailThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.channel(CommunicationChannel.EMAIL)
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChannel()).isEqualTo(CommunicationChannelDto.EMAIL);
	}

	@Test
	public void whenHasEmailInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageCreateDto.builder()
			.channel(CommunicationChannelDto.EMAIL)
			.build();
		var entity = mapper.toEntity(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getChannel()).isEqualTo(CommunicationChannel.EMAIL);
	}

	@Test
	public void whenEntityHasSmsThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.channel(CommunicationChannel.SMS)
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChannel()).isEqualTo(CommunicationChannelDto.SMS);
	}

	@Test
	public void whenHasSmsInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageCreateDto.builder()
			.channel(CommunicationChannelDto.SMS)
			.build();
		var entity = mapper.toEntity(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getChannel()).isEqualTo(CommunicationChannel.SMS);
	}

	@Test
	public void whenEntityHasPushThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.channel(CommunicationChannel.PUSH)
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChannel()).isEqualTo(CommunicationChannelDto.PUSH);
	}

	@Test
	public void whenHasPushInCreateDtoThenConvertToEntity() {
		LocalDateTime date = LocalDateTime.now();
		var message = MessageCreateDto.builder()
			.channel(CommunicationChannelDto.PUSH)
			.build();
		var entity = mapper.toEntity(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getChannel()).isEqualTo(CommunicationChannel.PUSH);
	}

	@Test
	public void whenWaitingStatusOnChannelThenConvertToDto() {
		var message = Message.builder()
			.chats(List.of(Chat.builder().status(Status.WAITING).build()))
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChats()).hasSize(1).first().satisfies(
			c -> assertThat(c.getStatus()).isEqualTo(StatusResponseDto.WAITING)
		);
	}

	@Test
	public void whenSentStatusOnChannelThenConvertToDto() {
		var message = Message.builder()
			.chats(List.of(Chat.builder().status(Status.SENT).build()))
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChats()).hasSize(1).first().satisfies(
			c -> assertThat(c.getStatus()).isEqualTo(StatusResponseDto.SENT)
		);
	}

	@Test
	public void whenSendingStatusOnChannelThenConvertToDto() {
		var message = Message.builder()
			.chats(List.of(Chat.builder().status(Status.SENDING).build()))
			.build();
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getChats()).hasSize(1).first().satisfies(
			c -> assertThat(c.getStatus()).isEqualTo(StatusResponseDto.SENDING)
		);
	}

	@Test
	public void whenDateOnChannelThenConvertToDto() {
		LocalDateTime date = LocalDateTime.now();
		var message = Message.builder()
			.chats(List.of(Chat.builder().date(date).build()))
			.build();
		var dto = mapper.toDto(message);
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
		var dto = mapper.toDto(message);
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
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getRecipient().getName()).isEqualTo("Alisson");
	}

	@Test
	public void whenHasRecipientNameInCreateDtoThenConvertToEntity() {
		var message = MessageCreateDto.builder()
			.recipient(RecipientDto.builder()
				.name("Alisson")
				.build())
			.build();
		var entity = mapper.toEntity(message);
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
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getRecipient().getEmail()).isEqualTo("alisson@mail.com");
	}

	@Test
	public void whenHasRecipientEmailInCreateDtoThenConvertToEntity() {
		var message = MessageCreateDto.builder()
			.recipient(RecipientDto.builder()
				.email("alisson@mail.com")
				.build())
			.build();
		var entity = mapper.toEntity(message);
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
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getRecipient().getPhoneId()).isEqualTo("123");
	}

	@Test
	public void whenHasRecipientPhoneIdInCreateDtoThenConvertToEntity() {
		var message = MessageCreateDto.builder()
			.recipient(RecipientDto.builder()
				.phoneId("123")
				.build())
			.build();
		var entity = mapper.toEntity(message);
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
		var dto = mapper.toDto(message);
		assertThat(dto).isNotNull();
		assertThat(dto.getRecipient().getPhoneNumber()).isEqualTo("999988887777");
	}

	@Test
	public void whenHasRecipientPhoneNumberInCreateDtoThenConvertToEntity() {
		var message = MessageCreateDto.builder()
			.recipient(RecipientDto.builder()
				.phoneNumber("999988887777")
				.build())
			.build();
		var entity = mapper.toEntity(message);
		assertThat(entity).isNotNull();
		assertThat(entity.getRecipient().getPhoneNumber()).isEqualTo("999988887777");
	}

}
