package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

public class Table {
    private TableType type;
    private int minDrinks;
    private int maxPeople;
    private TableSlot tableSlot1;
    private TableSlot tableSlot2;


    // CONSTRUCTORS //
    public Table(TableType type, int minDrinks, int maxPeople, TableSlot tableSlot1, TableSlot tableSlot2) {
        this.type = type;
        this.minDrinks = minDrinks;
        this.maxPeople = maxPeople;
        this.tableSlot1 = tableSlot1;
        this.tableSlot2 = tableSlot2;
    }

    public Table(TableType type) {
        this.type = type;
    }

    // GETTER //


    public TableType getType() {
        return type;
    }

    public int getMinDrinks() {
        return minDrinks;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public TableSlot getTableSlot1() {
        return tableSlot1;
    }

    public TableSlot getTableSlot2() {
        return tableSlot2;
    }

    // SETTERS //

    public void setType(TableType type) {
        this.type = type;
    }

    public void setMinDrinks(int minDrinks) {
        this.minDrinks = minDrinks;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setTableSlot1(TableSlot tableSlot1) {
        this.tableSlot1 = tableSlot1;
    }

    public void setTableSlot2(TableSlot tableSlot2) {
        this.tableSlot2 = tableSlot2;
    }

    @Override
    public String toString() {
        return "------------------------------------" + " \n" +
                " Table Type           = " + type + " \n" +
                " Minimum Drinks       = " + minDrinks + " \n" +
                " Max amount of People = " + maxPeople + " \n" +
                " Table Slot 1         = " + tableSlot1 + " \n" +
                " Table Slot 2         = " + tableSlot2 + " \n" +
                "------------------------------------";
    }
    public String toFile(){
        return type + "," + minDrinks + "," + maxPeople + "," + tableSlot1 + "," + tableSlot2 + "\n";

    }

}