package com.codewithrakhi.traffic.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tata_user_id;

    @Column(name="tata_user_name", nullable = false,length =100)
    private String name;

    private String email;

    private String password;

    private String department;

    private int vehicleNo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<Violation> violation = new ArrayList<>();



}
