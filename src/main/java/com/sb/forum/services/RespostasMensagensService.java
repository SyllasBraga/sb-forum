package com.sb.forum.services;

import com.sb.forum.repository.RespostaMensagensRepository;
import org.springframework.stereotype.Service;

@Service
public class RespostasMensagensService {

    private RespostaMensagensRepository respostaRepository;

    public RespostasMensagensService(RespostaMensagensRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }


}
