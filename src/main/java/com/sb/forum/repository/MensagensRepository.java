package com.sb.forum.repository;

import com.sb.forum.entities.MensagensTopico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagensRepository extends JpaRepository<MensagensTopico, Long> {
}
