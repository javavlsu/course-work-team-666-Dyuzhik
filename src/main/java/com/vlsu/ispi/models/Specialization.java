package com.vlsu.ispi.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL)
    private List<Barber> barbers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialization() {
    }

    public Specialization(String name) {
        this.name = name;
    }
}
