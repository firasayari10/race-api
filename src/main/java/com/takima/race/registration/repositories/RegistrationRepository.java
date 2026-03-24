package com.takima.race.registration.repositories;

import com.takima.race.registration.entities.Registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    long countByRaceId(Long raceId);
    boolean existsByRunnerIdAndRaceId(Long runnerId, Long raceId);
    List<Registration> findByRaceId(Long raceId);
    List<Registration> findByRunnerId(Long runnerId);
}
