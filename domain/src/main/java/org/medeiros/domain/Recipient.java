package org.medeiros.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipient {

	private String name;
	private String email;
	private String phoneNumber;
	private String phoneId;

}
