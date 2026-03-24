package com.takima.race.registration.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.services.RegistrationService;
import com.takima.race.runner.entities.Runner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/races/{raceId}/registrations")
    public ResponseEntity<Registration> registerRunner(
        @PathVariable Long raceId,
        @RequestBody RegistrationRequest request
    ) {
        Registration created = registrationService.registerRunner(raceId, request.runnerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/races/{raceId}/registrations")
    public List<Runner> listParticipants(@PathVariable Long raceId) {
        return registrationService.listParticipants(raceId);
    }

    @GetMapping("/runners/{runnerId}/races")
    public List<Race> listRacesForRunner(@PathVariable Long runnerId) {
        return registrationService.listRacesForRunner(runnerId);
    }

    public record RegistrationRequest(Long runnerId) { }
}
