package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

public class TableSlot {
    private String tableName;
    private TimeSlot timeSlot;
    private User customer;
    private Drink drink;
    private int amountOfPeople;


    // CONSTRUCTOR //

    public TableSlot(String tableName, TimeSlot timeSlot, User customer, Drink drink, int amountOfPeople) {
        this.tableName = tableName;
        this.timeSlot = timeSlot;
        this.customer = customer;
        this.drink = drink;
        this.amountOfPeople = amountOfPeople;
    }

    public TableSlot(String tableName, TimeSlot timeSlot) {
        this.tableName = tableName;
        this.timeSlot = timeSlot;
        this.customer = null;
        this.drink = null;
        this.amountOfPeople = 0;
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

    @Override
    public String toString() {
        return "------------------------------------" + " \n\n" +
                " Table Name       = " + tableName + " \n" +
                " Time Slot        = " + timeSlot + " \n" +
                " Costumer         = " + customer.getName() + " \n" +
                " Drinks           = " + drink.getName() + " \n" +
                " Amount Of People = " + amountOfPeople + " \n";
    }

    public String toFile() {
        return tableName + "," + timeSlot + "," + customer.getName() + "," + drink.getName() + "," + amountOfPeople + "\n";

    }
}
