package com.vlsu.ispi.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "Record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "time")
    @DateTimeFormat(pattern = "hh:mm aa")
    @Temporal(TemporalType.TIME)
    private Date time;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private Status status;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Column(name = "cost")
    @Min(value = 0, message = "Стоимость не может быть меньше 0")
    private int cost;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne()
    @JoinColumn(name = "barber_id")
    private User barber;

    @Transient
    private int barberID = -1;

    public int getBarberID() {
        return barberID;
    }

    public void setBarberID(int barberID) {
        this.barberID = barberID;
    }

    public User getBarber() {
        return barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Record(int cost){
        this.cost = cost;
    }

    public Record() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
