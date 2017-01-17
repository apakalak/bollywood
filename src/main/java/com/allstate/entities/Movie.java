package com.allstate.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movies")
@Data
public class Movie {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    @Column(nullable = false)
    private String title;

    private boolean watched;
    private String rating;
    private Date released;
    private int length;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;
}
