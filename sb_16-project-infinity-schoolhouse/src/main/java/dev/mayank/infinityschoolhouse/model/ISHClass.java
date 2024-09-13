package dev.mayank.infinityschoolhouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "class")
public class ISHClass extends BaseEntity {

    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    @Column(name = "name")
    @NotBlank(message = "Class name must not be blank")
    @Size(min = 3, message = "Class name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "iSHClass", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> students;
}
