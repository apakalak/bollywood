package com.allstate.services;


import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
//    Option 1 - Injection of Dependency
    @Autowired
    private IMovieRepository repository;

//    Options2 - - Injection of Dependency
//    @Autowired
//    public MovieService(IMovieRepository repository) {
//        this.repository =repository;
//    }
//
//     Option 3 - - Injection of Dependency
//    @Autowired
//    public void setRepository(IMovieRepository repository) {
//        this.repository = repository;
//    }

    public Movie create(Movie m){
        return this.repository.save(m);
    }

}
