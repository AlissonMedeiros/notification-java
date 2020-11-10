package org.medeiros.adapter.controller.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipientDto {

	@NotBlank(message = "Recipient name can't be null!")
	private String name;
	@NotBlank(message = "Recipient email can't be null!")
	private String email;
	@NotBlank(message = "Recipient phone number can't be null!")
	private String phoneNumber;
	@NotBlank(message = "Recipient phone id can't be null!")
	private String phoneId;

}
