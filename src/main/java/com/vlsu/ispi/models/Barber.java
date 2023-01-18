package com.vlsu.ispi.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Barber")
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL)
    private List<Record> records;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Barber() {
    }

    public Barber(int id) {
        this.id = id;
    }
}
