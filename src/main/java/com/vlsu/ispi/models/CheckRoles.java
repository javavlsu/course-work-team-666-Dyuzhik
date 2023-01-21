package com.vlsu.ispi.models;

import java.util.ArrayList;
import java.util.List;

public class CheckRoles {
    private List<Integer> admRoles = new ArrayList<>();
    private List<Integer> clRoles = new ArrayList<>();
    private List<Integer> barRoles = new ArrayList<>();
    private List<Integer> probarRoles = new ArrayList<>();

    public CheckRoles() {
    }

    public List<Integer> getAdmRoles() {
        return admRoles;
    }

    public void setAdmRoles(List<Integer> admRoles) {
        this.admRoles = admRoles;
    }

    public List<Integer> getClRoles() {
        return clRoles;
    }

    public void setClRoles(List<Integer> clRoles) {
        this.clRoles = clRoles;
    }

    public List<Integer> getBarRoles() {
        return barRoles;
    }

    public void setBarRoles(List<Integer> barRoles) {
        this.barRoles = barRoles;
    }

    public List<Integer> getProbarRoles() {
        return probarRoles;
    }

    public void setProbarRoles(List<Integer> probarRoles) {
        this.probarRoles = probarRoles;
    }
}
