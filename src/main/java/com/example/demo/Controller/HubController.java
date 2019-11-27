package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.example.demo.Entity.Hub;
import com.example.demo.Repository.HubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HubController {

    @Autowired private HubRepository hubRepository;

    @GetMapping("/Hubs")
    public Collection<Hub> getAllHubs() {
        return hubRepository.findAll();
    }

    @PostMapping("/Hubs")
    public Hub postHub(@RequestBody Map<String, Object> body){
        Hub hub = new Hub();
        hub.setHubName(body.get("hubName").toString());
        hub.setHubCode(body.get("hubCode").toString());
        return hubRepository.save(hub);
    }

    @GetMapping("/Hubs/{id}")
    public Optional<Hub> getHub(@PathVariable Long id) {
        return hubRepository.findById(id);
    }
}