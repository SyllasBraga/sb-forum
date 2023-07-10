package com.sb.forum.repository;

import com.sb.forum.entities.TopicStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicStatusRepository extends JpaRepository<TopicStatus, Long> {
}
