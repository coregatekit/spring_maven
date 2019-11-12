package com.example.demo.Repository;

import com.example.demo.Entity.Plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlaneRepository extends JpaRepository<Plane, Long> {

}