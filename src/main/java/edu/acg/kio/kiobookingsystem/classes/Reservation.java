package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.Days;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

public class Reservation {
    private User reservee;
    private Table table;
    private TimeSlot timeslot;
    private Days day;

    //Constructor

    public Reservation(User reservee, Table table, TimeSlot timeslot, Days day) {
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

    public Days getDay() {
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

    public void setDay(Days day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "------------------------------------" + " \n\n" +
                " Reservee         = " + reservee.getName() + " \n" +
                " Table            = " + table.getTableName() + " \n" +
                " Timeslot         = " + timeslot + " \n" +
                " Day              = " + day + " \n";
    }

    public  String toFile(){
        return reservee + "," + table + "," + timeslot + "," + day + "\n";
    }

}


