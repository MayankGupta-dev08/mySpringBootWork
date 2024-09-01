package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactDetail, Integer> {

    List<ContactDetail> findByStatus(String status);
}