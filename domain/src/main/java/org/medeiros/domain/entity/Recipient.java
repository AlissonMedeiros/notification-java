package org.medeiros.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Recipient {

	private String name;
	private String email;
	private String phoneNumber;
	private String phoneId;

}
