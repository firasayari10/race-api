package com.takima.race.registration.services;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RunnerRepository runnerRepository;
    private final RaceRepository raceRepository;

    public RegistrationService(
        RegistrationRepository registrationRepository,
        RunnerRepository runnerRepository,
        RaceRepository raceRepository
    ) {
        this.registrationRepository = registrationRepository;
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
    }

    public Registration registerRunner(Long raceId, Long runnerId) {
        Runner runner = runnerRepository.findById(runnerId).orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Coureur %s introuvable", runnerId)
            )
        );

        Race race = raceRepository.findById(raceId).orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Course %s introuvable", raceId)
            )
        );

        if (registrationRepository.existsByRunnerIdAndRaceId(runnerId, raceId)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Le coureur est déjà inscrit à cette course"
            );
        }

        long currentParticipants = registrationRepository.countByRaceId(raceId);
        if (race.getMaxParticipants() != null && currentParticipants >= race.getMaxParticipants()) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "La course est complète"
            );
        }

        Registration registration = new Registration();
        registration.setRunner(runner);
        registration.setRace(race);
        registration.setRegistrationDate(LocalDate.now());

        return registrationRepository.save(registration);
    }

    public List<Runner> listParticipants(Long raceId) {
        if (!raceRepository.existsById(raceId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Course %s introuvable", raceId)
            );
        }
        return registrationRepository.findByRaceId(raceId)
            .stream()
            .map(Registration::getRunner)
            .toList();
    }

    public List<Race> listRacesForRunner(Long runnerId) {
        if (!runnerRepository.existsById(runnerId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Coureur %s introuvable", runnerId)
            );
        }
        return registrationRepository.findByRunnerId(runnerId)
            .stream()
            .map(Registration::getRace)
            .toList();
    }

    public long countParticipants(Long raceId) {
        if (!raceRepository.existsById(raceId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Course %s introuvable", raceId)
            );
        }
        return registrationRepository.countByRaceId(raceId);
    }
}
