package org.medeiros.repository.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Recipient {

	private String name;
	private String email;
	private String phoneNumber;
	private String phoneId;

}
