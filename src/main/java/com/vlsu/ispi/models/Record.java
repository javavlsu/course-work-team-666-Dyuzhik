package com.vlsu.ispi.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name="date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne()
    @JoinColumn(name="status_id")
    private Status status;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne()
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Record(Date date) {
        this.date = date;
    }

    public Record() {
    }
}
