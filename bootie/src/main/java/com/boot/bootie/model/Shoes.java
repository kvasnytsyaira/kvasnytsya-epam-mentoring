package com.boot.bootie.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "shoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shoes")
@ToString
public class Shoes {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private int size;
    @Column
    private String model;
    @Column
    private String brand;


    public Shoes(int size, String model, String brand) {
        this.size = size;
        this.model = model;
        this.brand = brand;
    }
}
