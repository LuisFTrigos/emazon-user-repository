package com.example.emazon_aux.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "Users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, unique = true, length = 10)
    private String idDocument;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false, length = 13)
    private String phone;


    @ManyToOne
    @JoinColumn(nullable = false, name = "idRole")
    private RoleEntity idRole;

}
