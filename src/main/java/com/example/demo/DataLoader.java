package com.example.demo;

import com.example.demo.Entity.Hub;
import com.example.demo.Entity.Plane;
import com.example.demo.Repository.HubRepository;
import com.example.demo.Repository.PlaneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired private PlaneRepository planeRepository;
    @Autowired private HubRepository hubRepository;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Hub hub = new Hub();
        hub.setHubName("Suvarnabhumi International Airport");
        hub.setHubCode("BKK");
        hubRepository.save(hub);

        Hub hub2 = new Hub();
        hub2.setHubName("Singapore Changi Airport");
        hub2.setHubCode("SIN");
        hubRepository.save(hub2);

        Plane p1 = new Plane();
        p1.setPlaneName("สวรรณภูมิ");
        p1.setPlaneModel("Airbus A350 XBW");
        p1.setHub(hub);
        planeRepository.save(p1);

        Plane p2 = new Plane();
        p2.setPlaneName("ดอนเมือง");
        p2.setPlaneModel("Airbus A380-800");
        p2.setHub(hub);
        planeRepository.save(p2);

        Plane p3 = new Plane();
        p3.setPlaneName("สวรรณหงส์");
        p3.setPlaneModel("Boeing 787 Dreamliner");
        p3.setHub(hub);
        planeRepository.save(p3);

        Plane p4 = new Plane();
        p4.setPlaneName("Xforce");
        p4.setPlaneModel("Boeing 787 Max 10");
        p4.setHub(hub2);
        planeRepository.save(p4);
    }
}