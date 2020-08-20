package com.firstapp.demo.controllers;

import com.firstapp.demo.models.Session;
import com.firstapp.demo.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//this will respond to payloads incoming and outgoing as json rest points
@RequestMapping("/api/v1/sessions")//tells the routing what the mapping will look like
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping//tells exactly which http verb to use
    // which will be a get verb to call this end point
    public List<Session> list() {
        return sessionRepository.findAll();
    }


    @GetMapping//tells exactly which http verb to use
    // which will be a get verb to call this end point
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }//adding an additional id to the url for the GET method

    @PostMapping
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)//what I want to occur when the method executes and finishes
//    public Session create(@RequestBody final Session session){
//        return sessionRepository.saveAndFlush(session);
//    }
}
