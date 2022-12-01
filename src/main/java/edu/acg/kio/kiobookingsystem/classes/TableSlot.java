package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.Days;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

public class TableSlot {
    private String tableName;
    private TimeSlot timeSlot;
    private User customer;
    private Drink drink;
    private int amountOfPeople;
    private Days day;


    // CONSTRUCTOR //

    public TableSlot(String tableName, TimeSlot timeSlot, User customer, Drink drink, int amountOfPeople,Days day) {
        this.tableName = tableName;
        this.timeSlot = timeSlot;
        this.customer = customer;
        this.drink = drink;
        this.amountOfPeople = amountOfPeople;
        this.day = day;
    }

    public TableSlot(String tableName, TimeSlot timeSlot,Days day) {
        this.tableName = tableName;
        this.timeSlot = timeSlot;
        this.customer = null;
        this.drink = null;
        this.amountOfPeople = 0;
        this.day = day;
    }

    // GETTERS //

    public String getTableName() {
        return tableName;
    }

    public String getTimeSlot() {
        return timeSlot.toString();
    }

    public User getCustomer() {
        return customer;
    }

    public Drink getDrink() {
        return drink;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public Days getDay() {
        return day;
    }
    // SETTERS //

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "------------------------------------" + " \n\n" +
                " Table Name       = " + tableName + " \n" +
                " Time Slot        = " + timeSlot + " \n" +
                " Costumer         = " + customer.getName() + " \n" +
                " Drinks           = " + drink.getName() + " \n" +
                " Amount Of People = " + amountOfPeople + " \n" +
                " Day = " + day.toString() + " \n";
    }

    public String toFile() {
        return tableName + "," + timeSlot + "," + customer.getName() + "," + drink.getName() + "," + amountOfPeople + day.toString()+ "\n";

    }
}
