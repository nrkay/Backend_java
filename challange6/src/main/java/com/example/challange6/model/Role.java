package com.example.challange6.model;


import com.example.challange6.model.auditModel.AuditModelConfig;
import com.example.challange6.model.enums.ERole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "roles")
public class Role extends AuditModelConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
