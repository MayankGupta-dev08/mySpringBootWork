package dev.mayank.cli.app.dao;

import dev.mayank.cli.app.entity.Instructor;
import dev.mayank.cli.app.entity.InstructorDetail;

public interface InstructorDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);
}