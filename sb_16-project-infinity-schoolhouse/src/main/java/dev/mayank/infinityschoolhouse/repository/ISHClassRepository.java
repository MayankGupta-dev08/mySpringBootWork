package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.ISHClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISHClassRepository extends JpaRepository<ISHClass, Integer> {

}
