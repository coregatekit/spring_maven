package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

import com.example.demo.Entity.Plane;
import com.example.demo.Entity.Hub;
import com.example.demo.Repository.HubRepository;
import com.example.demo.Repository.PlaneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class PlaneController {
    @Autowired private PlaneRepository planeRepository;
    @Autowired private HubRepository hubRepository;

    @GetMapping("/Planes")
    public Collection<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    // @PostMapping("/Planes")
    // public Plane postPlane(@RequestBody Map<String, Object> body){
    //     Optional<Hub> hub = hubRepository.findById(Long.valueOf(body.get("hubId").toString()));
    //     Plane plane = new Plane();
    //     plane.setPlaneName(body.get("planeName").toString());
    //     plane.setPlaneModel(body.get("planeModel").toString());
    //     plane.setHub(hub.get());
    //     return planeRepository.save(plane);
    // }

    @PostMapping("/Planes/{planeName}/{planeModel}/{hubId}")
    public Plane postPlane(@PathVariable String planeName, @PathVariable String planeModel, @PathVariable Long hubId){
        Optional<Hub> hub = hubRepository.findById(hubId);
        Plane plane = new Plane();
        plane.setPlaneName(planeName + "#@#");
        plane.setPlaneModel(planeModel);
        plane.setHub(hub.get());
        return planeRepository.save(plane);
    }

    @GetMapping("/Planes/{id}")
    public Optional<Plane> getPlane(@PathVariable Long id) {
        return planeRepository.findById(id);
    }
}