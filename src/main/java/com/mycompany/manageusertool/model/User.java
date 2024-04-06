package com.mycompany.manageusertool.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true, length = 45)
    private String email;
    @Column(nullable = false,length = 15)
    private String password;
    @Column(nullable = false,length =45, name = "first_name")
    private String firstName;
    @Column(nullable = false,length =45, name = "last_name")
    private String lastName;
}
