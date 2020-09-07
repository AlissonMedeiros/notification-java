package org.medeiros.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Chat {
	private Status status;
	private LocalDateTime date;
}
