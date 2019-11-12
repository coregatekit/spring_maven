package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

import com.example.demo.Entity.Plane;
import com.example.demo.Repository.PlaneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PlaneController {
    @Autowired private PlaneRepository planeRepository;

    @GetMapping("/Planes")
    public Collection<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    @PostMapping("/Planes")
    public Plane postPlane(@RequestBody Map<String, Object> body){
        Plane plane = new Plane();
        plane.setPlaneName(body.get("planeName").toString());
        plane.setPlaneModel(body.get("planeModel").toString());
        return planeRepository.save(plane);
    }
}