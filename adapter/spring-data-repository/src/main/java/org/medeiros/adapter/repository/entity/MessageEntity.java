package org.medeiros.adapter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "message_entity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

	@Id
	@Column(name = "message_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "message_schedule_date")
	private LocalDateTime scheduleDate;
	@Column(name = "message_body")
	private String body;
	@Embedded
	private RecipientEntity recipient;
	@Column(name = "message_channel")
	@Enumerated(EnumType.STRING)
	private CommunicationChannelEntity channel;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "message", cascade = CascadeType.ALL)
	private List<ChatEntity> chats;

}
