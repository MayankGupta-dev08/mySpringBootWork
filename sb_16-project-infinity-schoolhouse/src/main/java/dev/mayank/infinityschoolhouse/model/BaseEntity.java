package dev.mayank.infinityschoolhouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jdk.jfr.Description;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@Description("Base entity for all entities")
public class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
