package com.vlsu.ispi.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Пожалуйста, введите название")
    @Size(min=2, max=500, message = "Название не должно быть короче 2 и длиннее 500 символов")
    private String name;

    @Column(name = "photo")
    @NotEmpty(message = "Пожалуйста, добавьте фото")
    private String photo;

    @Column(name = "cost")
    @Min(value = 0, message = "Стоимость не может быть меньше нуля")
    private int cost;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Record> records;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Service() {
    }

    public Service( String name, String photo) {
        this.name = name;
        this.photo = photo;
    }
}
