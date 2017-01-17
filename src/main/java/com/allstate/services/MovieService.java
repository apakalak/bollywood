package com.allstate.services;


import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
//    Option 1 - Injection of Dependency
    @Autowired
    private IMovieRepository repository;

//    Options2 - - Injection of Dependency
//    private IMovieRepository repository;
//    @Autowired
//    public MovieService(IMovieRepository repository) {
//        this.repository =repository;
//    }
//
//     Option 3 - - Injection of Dependency
//     private IMovieRepository repository;
//     @Autowired
//     public void setRepository(IMovieRepository repository) {
//        this.repository = repository;
//     }

    public Movie create(Movie m){
        return this.repository.save(m);
    }


    public Movie findById(int id){
        return this.repository.findOne(id);
    }

    public List<Movie> findAll(){
        return (List<Movie>)this.repository.findAll();
    }

    public Movie findByTitle(String title){
        return this.repository.findByTitle(title);
    }

    public void deleteById(int id){
        this.repository.delete(id);
    }


    public Movie updateById(int id, Movie updatedMovie){
        Movie m1 =this.repository.findOne(id);
        m1.setTitle(updatedMovie.getTitle());
        m1.setRating(updatedMovie.getRating());
        m1.setLength(updatedMovie.getLength());
        m1.setReleased(updatedMovie.getReleased());

        this.repository.save(m1);
        return this.repository.findOne(m1.getId());
    }
}
