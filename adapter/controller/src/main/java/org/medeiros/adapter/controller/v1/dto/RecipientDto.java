package org.medeiros.adapter.controller.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipientDto {

	private String name;
	private String email;
	private String phoneNumber;
	private String phoneId;

}
