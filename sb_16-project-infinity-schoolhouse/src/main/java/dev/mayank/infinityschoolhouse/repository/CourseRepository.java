package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}