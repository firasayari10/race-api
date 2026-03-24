package com.takima.race.registration.entities;

import com.takima.race.race.entities.Race;
import com.takima.race.runner.entities.Runner;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "runner_id")
    private Runner runner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "race_id")
    private Race race;

    private LocalDate registrationDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() { return id; }
    public Runner getRunner() { return runner; }
    public Race getRace() { return race; }
    public LocalDate getRegistrationDate() { return registrationDate; }

    public void setId(Long id) { this.id = id; }
    public void setRunner(Runner runner) { this.runner = runner; }
    public void setRace(Race race) { this.race = race; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
}
