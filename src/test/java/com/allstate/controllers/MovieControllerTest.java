package com.allstate.controllers;

import com.allstate.entities.Movie;
import com.allstate.services.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieService service;

    @Test
    public void shouldCreateMovies() throws Exception {
        Movie m = new Movie();
        m.setId(3);
        m.setTitle("DDLJ");
        given(this.service.create(Mockito.any(Movie.class)))
                .willReturn(m);

         MockHttpServletRequestBuilder request = post("/movies")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content("{\"title\":\"DDLJ\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(3)))
                .andExpect(jsonPath("$.title",is("DDLJ")));
    }

    @Test
    public void findsMovie() throws Exception {
        Movie m = new Movie();
        m.setId(3);
        m.setTitle("DDLJ");
        given(this.service.findById(3))
                .willReturn(m);

        MockHttpServletRequestBuilder request = get("/movies/3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(3)))
                .andExpect(jsonPath("$.title",is("DDLJ")));
    }
}