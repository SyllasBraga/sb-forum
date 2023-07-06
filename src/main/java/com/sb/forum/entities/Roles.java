package com.sb.forum.entities;

import javax.persistence.*;

import com.sb.forum.enums.RolesEnum;
import lombok.Data;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RolesEnum tipoAcesso;
}
