package com.example.demo;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.example.demo.Entity.Plane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class PlaneJunitTest {

    @Autowired private TestEntityManager entityManager;

    @Test
    public void PlaneNameNotPatter() {
        Plane plane = new Plane();
        plane.setPlaneName("สุวรรณภูมิ");
        plane.setPlaneModel("Airbus A350-800");
        
        try {
            entityManager.persist(plane);
            entityManager.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations, false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n\n PlaneNameNotPattern\n" + violations);
        }
    }

    @Test
    public void PlaneNullModel() {
        Plane plane = new Plane();
        plane.setPlaneName("นครราชสีมา");
        plane.setPlaneModel("Airbus A350-900");

        try {
            entityManager.persist(plane);
            entityManager.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations, false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n\n PlaneNullModel\n" + violations);
        }
    }
}