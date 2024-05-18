package dev.mayank.cli.app.dao;

import dev.mayank.cli.app.entity.Instructor;

public interface InstructorDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}