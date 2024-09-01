package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {

}