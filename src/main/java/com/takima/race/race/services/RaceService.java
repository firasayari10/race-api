package com.takima.race.race.services;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getAll(String location) {
        if (location == null || location.isBlank()) {
            return raceRepository.findAll();
        }
        return raceRepository.findByLocationIgnoreCase(location);
    }

    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Course %s introuvable", id)
            )
        );
    }

    public Race create(Race race) {
        return raceRepository.save(race);
    }
}
