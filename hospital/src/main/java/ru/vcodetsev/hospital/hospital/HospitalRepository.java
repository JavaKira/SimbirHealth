package ru.vcodetsev.hospital.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
//    @Override todo
//    @Query("SELECT hospital FROM hospitals f WHERE LOWER(f.name) = LOWER(:name)")
//    Optional<Hospital> findById(Long id);
}
