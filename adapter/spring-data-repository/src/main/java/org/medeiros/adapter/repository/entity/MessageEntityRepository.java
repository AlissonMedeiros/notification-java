package org.medeiros.adapter.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageEntityRepository extends JpaRepository<MessageEntity, Long> {
}
