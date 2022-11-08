package com.boot.bootie.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users_table")
@ToString
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_email")
    private String email;

//    @OneToMany(mappedBy = "user")
//    private List<Order> orders = new ArrayList<>();


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
