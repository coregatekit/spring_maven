package com.example.demo.Repository;

import com.example.demo.Entity.Hub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HubRepository extends JpaRepository<Hub, Long> {

}