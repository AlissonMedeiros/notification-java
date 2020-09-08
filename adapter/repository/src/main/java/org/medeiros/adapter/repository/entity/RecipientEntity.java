package org.medeiros.adapter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipientEntity {

	@Column(name = "message_recipient_name")
	private String name;
	@Column(name = "message_recipient_email")
	private String email;
	@Column(name = "message_recipient_phone_number")
	private String phoneNumber;
	@Column(name = "message_recipient_phone_id")
	private String phoneId;

}
