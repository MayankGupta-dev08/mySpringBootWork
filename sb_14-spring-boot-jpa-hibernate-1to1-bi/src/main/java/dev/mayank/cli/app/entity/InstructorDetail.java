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

    /**
     * This is done for Bi-directional OneToOne Mapping b/w Instructor and InstructorDetail
     * Also we allowed deleting the instructor_detail alone w/o deleting instructor, for other operations both the entities will participate.
     */
    // @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail(String ytChannel, String coreExpertise) {
        this.ytChannel = ytChannel;
        this.coreExpertise = coreExpertise;
    }

    @Override
    public String toString() {
        return "InstructorDetail{id=%d, coreExpertise='%s', ytChannel='%s', instructorId=%d}"
                .formatted(id, coreExpertise, ytChannel, instructor != null ? instructor.getId() : null);
    }
}