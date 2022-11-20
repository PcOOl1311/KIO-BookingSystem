package edu.acg.kio.kiobookingsystem.classs;

import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

public class Reservation {
    private User user;
    private Table table;
    private TimeSlot time;
    private String day; //TODO find a type for date

    public Reservation(User user, Table table, TimeSlot time, String day) {
        this.user = user;
        this.table = table;
        this.time = time;
        this.day = day;
    }

    //Getters
    public User getUser() {
        return user;
    }

    public Table getTable() {
        return table;
    }

    public TimeSlot getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    //Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setTime(TimeSlot time) {
        this.time = time;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "user=" + user +
                ", table=" + table +
                ", time=" + time +
                ", day='" + day + '\'' +
                '}';
    }
}
