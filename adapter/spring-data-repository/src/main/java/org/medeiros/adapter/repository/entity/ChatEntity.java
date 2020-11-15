package org.medeiros.adapter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_entity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "chat_status")
	@Enumerated(EnumType.STRING)
	private StatusEntity status;
	@Column(name = "chat_date")
	private LocalDateTime date;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id", referencedColumnName = "id")
	private MessageEntity message;

}
