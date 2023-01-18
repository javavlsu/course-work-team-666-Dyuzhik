package com.vlsu.ispi.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Record> records;

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

    public Service() {
    }

    public Service( String name) {
        this.name = name;
    }
}
