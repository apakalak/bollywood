package com.allstate.controllers;

import com.allstate.entities.Movie;
import com.allstate.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/movies")
public class MovieController {

//  Option 1
    @Autowired
    private MovieService service;

//    Option 2 -----------------
//    @Autowired
//    public void setService(MovieService service) {
//        this.service = service;
//    }

//    Option 3 ----------------------------
//    @Autowired
//    public void MovieController(MovieService service) {
//        this.service = service;
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie findById(@PathVariable int id){
        return this.service.findById(id);

    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    public Movie create(@RequestBody Movie movie){
        return this.service.create(movie);

    }
}
