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
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "character_sheet")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class CharacterSheetEntity extends BaseEntity {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "character_name", columnDefinition = "varchar(100)", nullable = false)
    private String characterName;

    @Column(name = "player_id", columnDefinition = "uuid", nullable = false)
    private UUID playerId;

    @Column(name = "trpg_system", nullable = false, columnDefinition = "varchar(45)")
    @Enumerated(EnumType.STRING)
    private ETRPGSystem trpgSystem;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SessionEntity.class)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private SessionEntity session;
}
