package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Hub {
    @Id
    @SequenceGenerator(name="Plane_seq", sequenceName = "Plane_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Plane_seq")
    private Long id;

    @Column(unique=true)
    @Pattern(regexp = "[ก-์|A-z|\\s].+")
    private @NotNull String hubName;

    @Column(unique = true)
    @Pattern(regexp = "[ก-์|A-z|\\s].+")
    private @NotNull String hubCode;

    public Hub() {}
    public Hub(String hubName, String hubCode) {
        this.hubCode = hubCode;
        this.hubName = hubName;
    }
    
}