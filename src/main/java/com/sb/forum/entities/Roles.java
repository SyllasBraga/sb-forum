package com.sb.forum.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoAcesso;
}
