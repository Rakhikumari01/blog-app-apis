package com.codewithrakhi.traffic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Violation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int violationId;

    @Column(length = 100, nullable = false)
    private String violationTitle;

    @Column(nullable = false)
    private String violationImage;

    private Date addedDate;

    @ManyToOne
    private User user;

    //mapping
}
