package com.firstapp.demo.controllers;

import com.firstapp.demo.models.Session;
import com.firstapp.demo.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Lob;
import java.util.List;

@RestController//this will respond to payloads incoming and outgoing as json rest poitns
@RequestMapping("/api/v1/sessions")//tells the routing what the mapping will look like
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }


    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//what I want to occur when the method executes and finishes
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }
}
