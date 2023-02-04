package com.vlsu.ispi.classes;

import com.vlsu.ispi.models.User;

public class BarberCost {
    private User barber;
    private int cost;

    public User getBarber() {
        return barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public BarberCost() {
    }

    public BarberCost(User barber, int cost) {
        this.barber = barber;
        this.cost = cost;
    }
}
