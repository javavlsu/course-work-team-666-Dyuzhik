package com.vlsu.ispi.classes;

import com.vlsu.ispi.models.User;

import java.util.Date;
import java.util.List;

public class StructTimeBarber {
    private Date time;
    private List<User> barbers;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<User> getBarbers() {
        return barbers;
    }

    public void setBarbers(List<User> barbers) {
        this.barbers = barbers;
    }

    public StructTimeBarber() {
    }

    public StructTimeBarber(String time, List<User> barbers) {
        this.barbers = barbers;
    }
}
