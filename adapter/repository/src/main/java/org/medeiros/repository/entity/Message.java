package org.medeiros.repository.entity;

import org.medeiros.domain.entity.CommunicationChannel;
import org.medeiros.domain.entity.Recipient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Message {

	@Id
	private String id;
	private LocalDateTime scheduleDate;
	private String body;
	private Recipient recipient;
	@Enumerated(EnumType.STRING)
	private CommunicationChannel channel;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Chat> chats;

}
