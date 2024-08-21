package com.hbsites.commons.rpgtracker.infrastructure.entity;

import com.hbsites.commons.infrastructure.entity.BaseEntity;
import com.hbsites.commons.rpgtracker.domain.enumeration.ETRPGSystem;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "session")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class SessionEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid")
    private UUID sessionId;

    @Column(name = "session_name", columnDefinition = "varchar(100)", nullable = false)
    private String sessionName;

    @Column(name = "dm_id", columnDefinition = "uuid", nullable = false)
    private UUID dmId;

    @Column(name = "in_play", columnDefinition = "boolean")
    private Boolean inPlay;

    @Column(name = "trpg_system", nullable = false, columnDefinition = "varchar(45)")
    @Enumerated(EnumType.STRING)
    private ETRPGSystem trpgSystem;

    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER, targetEntity = CharacterSheetEntity.class)
    private List<CharacterSheetEntity> sheets;

}