package com.example.demo;

import com.example.demo.Entity.Plane;
import com.example.demo.Repository.PlaneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired private PlaneRepository planeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Plane p1 = new Plane();
        p1.setPlaneName("สวรรณภูมิ");
        p1.setPlaneModel("Airbus A350 XBW");
        planeRepository.save(p1);

        Plane p2 = new Plane();
        p2.setPlaneName("ดอนเมือง");
        p2.setPlaneModel("Airbus A380-800");
        planeRepository.save(p2);

        Plane p3 = new Plane();
        p3.setPlaneName("สวรรณหงส์");
        p3.setPlaneModel("Boeing 787 Dreamliner");
        planeRepository.save(p3);
    }
}