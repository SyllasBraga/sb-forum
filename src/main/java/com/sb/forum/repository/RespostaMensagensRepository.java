package com.sb.forum.repository;

import com.sb.forum.entities.RespostasMensagensTopico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaMensagensRepository extends JpaRepository<RespostasMensagensTopico, Long> {
}
