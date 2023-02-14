package com.vlsu.ispi.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="mark")
    @Min(message = "Оценка не может быть меньше 1", value = 1)
    @Max(message = "Оценка не может быть больше 10", value = 10)
    private int mark;

    @Column(name="text")
    private String text;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne()
    @JoinColumn(name = "barber_id")
    private User barber;

    public Feedback() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getBarber() {
        return barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }
}
