package com.firstapp.demo.controllers;

import com.firstapp.demo.models.Session;
import com.firstapp.demo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
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


    //tells exactly which http verb to use
    // which will be a get verb to call this end point
    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }//adding an additional id to the url for the GET method

    @PostMapping
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //also need to check for children records before deleting;
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        //because this is a PUT, we expect all attributes to be passed in.
        // A PATCH would only need what
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload

        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        //we ignore session_id because it is pr key, because it will ad null for pk, but pk cannot be null
        return sessionRepository.saveAndFlush(existingSession);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)//what I want to occur when the method executes and finishes
//    public Session create(@RequestBody final Session session){
//        return sessionRepository.saveAndFlush(session);
//    }
}
