package org.medeiros.adapter.controller.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreateDto {

	@NotNull(message = "Schedule date can't be null.")
	private LocalDateTime scheduleDate;
	@NotBlank(message = "Body can't be null.")
	private String body;
	@Valid
	@NotNull(message = "Message need a recipient!")
	private RecipientDto recipient;
	@NotNull(message = "Channel can't be null.")
	private CommunicationChannelDto channel;

}

