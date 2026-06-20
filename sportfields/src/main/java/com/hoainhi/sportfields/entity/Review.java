package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_review;

    @Column(columnDefinition = "text")
    private String comment;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_facility" , nullable = false)
    private Facility facility;
}
