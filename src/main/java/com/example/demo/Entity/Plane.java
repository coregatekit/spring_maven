package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Plane {
    @Id
    @SequenceGenerator(name="Plane_seq", sequenceName = "Plane_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Plane_seq")
    private Long id;

    @Column(unique=true)
    @Pattern(regexp = "[ก-์|A-z|\\s].+")
    private @NotNull String planeName;

    @Column(unique=true)
    @Pattern(regexp = "[A-z|\\s].+")
    private @NotNull String planeModel;

    public Plane() {}
    public Plane(String planeName, String planeModel) {
        this.planeName = planeName;
        this.planeModel = planeModel;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hubId", nullable = false)
    @JsonIgnore
    private Hub hub;
}