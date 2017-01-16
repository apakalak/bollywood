package com.allstate.services;

import com.allstate.entities.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class MovieServiceTest {
    @Autowired
    private MovieService service;

    @Test
    public void create_createsMovieByAddingNewEntryinDB() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Matrix");
        Movie after = this.service.create(before);
        assertEquals(after.getTitle(),"The Matrix");
        assertEquals(after.getId(),2);
        assertEquals(after.getVersion(),0);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_doesNotCreateMovie() throws Exception {
        Movie before = new Movie();
        Movie after = this.service.create(before);
        assertNotEquals(after.getId(),2);
    }

    @Test
    public void findMovieById() throws Exception{
        Movie result = this.service.findById(1);
        assertEquals(result.getTitle(), "The Avengers");
    }

    @Test
    public void findMovieById_doesntFindRecord() throws Exception{
        Movie result = this.service.findById(2);
        assertNull(result);
    }

    @Test
    public void findAllMovies() throws Exception{
        List<Movie> result = this.service.findAll();
        assertEquals(result.size(),1);
    }

    @Test
    @Sql(value = {"/sql/truncate.sql"})
    public void findAllMoviesWithEmpltyList() throws Exception{
        List<Movie> result = this.service.findAll();
        assertEquals(result.size(),0);
    }

    @Test
    public void findAllMoviesWithList() throws Exception{
        Movie before = new Movie();
        before.setTitle("The Matrix");
        this.service.create(before);
        before = new Movie();
        before.setTitle("Maze Runner");
        this.service.create(before);
        List<Movie> result = this.service.findAll();
        assertEquals(result.size(),3);
    }

    @Test
    public void findByTitle() throws Exception{
        Movie result = this.service.findByTitle("The Avengers");
        assertEquals(result.getId(),1);
    }

    @Test
    public void findByTitle_NoRecordFound() throws Exception{
        Movie result = this.service.findByTitle("The Matrix");
        assertNull(result);
    }

    @Test
    public void deleteById() throws Exception{
        this.service.deleteById(1);
        assertNull(this.service.findById(1));

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteById_MovieNotFound() throws Exception{
        this.service.deleteById(10);

    }

    @Test
    public void updateById() throws Exception{
        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("Harry Potter");
        updatedMovie.setRating("PG");
        Movie oldMovie = this.service.findById(1);
        this.service.updateById(1,updatedMovie);
        assertEquals("Harry Potter",this.service.findById(1).getTitle());
        assertEquals(0,this.service.findById(1).getLength());
        assertEquals(this.service.findById(1).getVersion(),oldMovie.getVersion()+1);


    }
}