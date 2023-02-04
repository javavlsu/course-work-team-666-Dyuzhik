package com.vlsu.ispi.classes;

import com.vlsu.ispi.models.Role;
import com.vlsu.ispi.models.User;

import java.util.Date;
import java.util.List;

public class TimeBarber {
    private Date time;
    private List<BarberCost> barbers;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<BarberCost> getBarbers() {
        return barbers;
    }

    public void setBarbers(List<BarberCost> barbers) {
        this.barbers = barbers;
    }

    public TimeBarber(Date time, List<BarberCost> barbers) {
        this.time = time;
        this.barbers = barbers;
    }

    public TimeBarber(){}
}
