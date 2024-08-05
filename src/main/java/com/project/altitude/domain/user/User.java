package com.project.altitude.domain.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50, name = "first_name")
    private String firstName;

    @Column(nullable = false, length = 50, name = "last_name")
    private String lastName;

    @Column(nullable = false, length = 70)
    private String email;

    @Column(nullable = false, length = 50)
    private String phone;

    @Column(nullable = false)
    private String cpf;


    public User (RegisterUserRequestPayload payload){
        this.password = payload.password();
        this.username = payload.username();
        this.firstName = payload.firstName();
        this.lastName = payload.lastName();
        this.email = payload.email();
        this.phone = payload.phone();
        this.cpf = payload.cpf();
    }
}
