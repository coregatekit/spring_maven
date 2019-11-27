package com.example.demo;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.example.demo.Entity.Hub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class HubJunitTest {

    @Autowired private TestEntityManager entityManager;

    @Test
    public void PlaneNameNotPatter() {
        Hub hub = new Hub();
        hub.setHubName("Changi Airport");
        hub.setHubCode("SIN");
        
        try {
            entityManager.persist(hub);
            entityManager.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations, false);
            assertEquals(violations.size(), 1);
        }
    }
}