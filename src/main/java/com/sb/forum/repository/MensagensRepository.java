package com.sb.forum.repository;

import com.sb.forum.entities.MensagensTopico;
import com.sb.forum.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagensRepository extends JpaRepository<MensagensTopico, Long> {
}
