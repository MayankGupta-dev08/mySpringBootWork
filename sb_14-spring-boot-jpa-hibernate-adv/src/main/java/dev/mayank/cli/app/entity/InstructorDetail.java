package dev.mayank.cli.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "yt_channel")
    private String ytChannel;

    @Column(name = "core_expertise")
    private String coreExpertise;

    public InstructorDetail(String ytChannel, String coreExpertise) {
        this.ytChannel = ytChannel;
        this.coreExpertise = coreExpertise;
    }
}