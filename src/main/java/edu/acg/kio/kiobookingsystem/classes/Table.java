package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

public class Table {
    private TableType type;
    private int minDrinks;
    private User Customer1;
    private User Customer2;
    private Drink Drink1;
    private Drink Drink2;
    private TimeSlot timeSlot;

    public Table(TableType type, int minDrinks, User customer1, User customer2, Drink drink1, Drink drink2, TimeSlot timeSlot) {
        this.type = type;
        this.minDrinks = minDrinks;
        Customer1 = customer1;
        Customer2 = customer2;
        Drink1 = drink1;
        Drink2 = drink2;
        this.timeSlot = timeSlot;
    }

    //Getters
    public TableType getType() {
        return type;
    }

    public int getMinDrinks() {
        return minDrinks;
    }

    public User getCustomer1() {
        return Customer1;
    }

    public User getCustomer2() {
        return Customer2;
    }

    public Drink getDrink1() {
        return Drink1;
    }

    public Drink getDrink2() {
        return Drink2;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    //Setters
    public void setType(TableType type) {
        this.type = type;
    }

    public void setMinDrinks(int minDrinks) {
        this.minDrinks = minDrinks;
    }

    public void setCustomer1(User customer1) {
        Customer1 = customer1;
    }

    public void setCustomer2(User customer2) {
        Customer2 = customer2;
    }

    public void setDrink1(Drink drink1) {
        Drink1 = drink1;
    }

    public void setDrink2(Drink drink2) {
        Drink2 = drink2;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "Table{" +
                "type=" + type +
                ", minDrinks=" + minDrinks +
                ", Customer1=" + Customer1 +
                ", Customer2=" + Customer2 +
                ", Drink1=" + Drink1 +
                ", Drink2=" + Drink2 +
                ", timeSlot=" + timeSlot +
                '}';
    }
}
