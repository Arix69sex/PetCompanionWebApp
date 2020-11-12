package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
