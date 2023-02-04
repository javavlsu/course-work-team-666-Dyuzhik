package com.vlsu.ispi.classes;

public enum Time {
    Ten("10:00"),
    Eleven("11:00"),
    Twelve("12:00"),
    Thirteen("13:00"),
    Fourteen("14:00"),
    Fifteen("15:00"),
    Sixteen("16:00"),
    Seventeen("17:00"),
    Eighteen("18:00");
    private String time;
    private int count = 0;
    Time(String time){
        this.time = time;
    }
    public String getTime(){
        return time;
    }
    public int getCount(){return count;}
//    public void add(){
//        count++;
//    }
//    public void clean(){count=0;}
}
