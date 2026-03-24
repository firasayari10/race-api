package com.takima.race.runner.services;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                String.format("Coureur %s introuvable", id)
                )
        );
    }

    public Runner create(Runner runner)
    {
        validateEmail(runner.getEmail());
        return runnerRepository.save(runner);
    }
    public void delete(Long id) {
        if (!runnerRepository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Coureur %s introuvable", id)
            );
        }
        runnerRepository.deleteById(id);
    }

    public Runner update(Long id, Runner runner)
    {
        Runner existingRunner = getById(id);
        validateEmail(runner.getEmail());
        runner.setId(existingRunner.getId());
        return runnerRepository.save(runner);
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Adresse email invalide"
            );
        }
    }
 }

    
