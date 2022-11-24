package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

public class Reservation {
    private User reservee;
    private Table table;
    private TimeSlot timeslot;
    private String day; //TODO find a data type for day

    //Constructor

    public Reservation(User reservee, Table table, TimeSlot timeslot, String day) {
        this.reservee = reservee;
        this.table = table;
        this.timeslot = timeslot;
        this.day = day;
    }

    //Getters
    public User getReservee() {
        return reservee;
    }

    public Table getTable() {
        return table;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public String getDay() {
        return day;
    }

    //Setters
    public void setReservee(User reservee) {
        this.reservee = reservee;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservee=" + reservee +
                ", table=" + table +
                ", timeslot=" + timeslot +
                ", day='" + day + '\'' +
                '}';
    }

    public  String toFile(){
        return reservee + "," + table + "," + timeslot + "," + day + "\n";
    }

}


