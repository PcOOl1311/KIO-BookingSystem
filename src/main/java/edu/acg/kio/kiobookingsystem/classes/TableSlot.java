package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

import java.lang.reflect.Type;

public class TableSlot {

    private TimeSlot timeSlot;
    private User customer;
    private Drink drink;
    private int amountOfPeople;


    // CONSTRUCTOR //

    public TableSlot(TimeSlot timeSlot, User customer, Drink drink, int amountOfPeople) {
        this.timeSlot = timeSlot;
        this.customer = customer;
        this.drink = drink;
        this.amountOfPeople = amountOfPeople;
    }

    public TableSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    // GETTERS //
    public TimeSlot getTimeSlot() {
        return timeSlot;
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
                " Time Slot        = " + timeSlot + " \n" +
                " Costumer         = " + customer.getName() + " \n" +
                " Drinks           = " + drink.getName() + " \n" +
                " Amount Of People = " + amountOfPeople + " \n";
    }

    public String toFile(){
        return timeSlot + "," + customer + "," + drink + "," + amountOfPeople + "\n";

    }
}
