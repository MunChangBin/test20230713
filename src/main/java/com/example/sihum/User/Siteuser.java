package com.example.sihum.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Siteuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 25)
    @Column(unique = true)
    private String name;

    private String password;

    @Column(unique = true)
    private String email;
}
