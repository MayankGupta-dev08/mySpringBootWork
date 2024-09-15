package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactDetail, Integer> {

    List<ContactDetail> findByStatus(String status);

    //@Query("SELECT c FROM ContactDetail c WHERE c.status = ?1")
    @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true)
    Page<ContactDetail> findByStatus(@Param("status") String status, Pageable pageable);

    @Modifying @Transactional
    @Query("UPDATE ContactDetail c SET c.status = ?2 WHERE c.contactId = ?1")
    int updateStatusById(int contactId, String status);
}