package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.TableType;

public class Table {
    private String tableName;
    private TableType type;
    private int minDrinks;
    private int maxPeople;
    private TableSlot tableSlot1;
    private TableSlot tableSlot2;


    // CONSTRUCTORS //
    public Table(String tableName, TableType type, int minDrinks, int maxPeople, TableSlot tableSlot1, TableSlot tableSlot2) {
        this.tableName = tableName;
        this.type = type;
        this.minDrinks = minDrinks;
        this.maxPeople = maxPeople;
        this.tableSlot1 = tableSlot1;
        this.tableSlot2 = tableSlot2;
    }

    public Table(String tableName, TableType type) {
        this.tableName = tableName;
        this.type = type;
        if (type.equals(TableType.NORMAL)){
            this.minDrinks = 2;
            this.maxPeople = 8;
        }
        else if (type.equals(TableType.VIP)) {
            this.minDrinks = 3;
            this.maxPeople = 8;
        }
        else if (type.equals(TableType.BACKSTAGE)) {
            this.minDrinks = 5;
            this.maxPeople = 12;
        }

        this.tableSlot1 = null;
        this.tableSlot2 = null;

    }

    // GETTER //


    public String getTableName() {
        return tableName;
    }

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


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

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
        return "------------------------------------" + " \n\n" +
                " Table Name           = " + tableName + " \n" +
                " Table Type           = " + type + " \n" +
                " Minimum Drinks       = " + minDrinks + " \n" +
                " Max amount of People = " + maxPeople + " \n" +
                " Table Slot 1         = " + tableSlot1.getCustomer().getName() + " \n" +
                " Table Slot 2         = " + tableSlot2.getCustomer().getName() + " \n";

    }

    public String toFile() {
        return tableName + "," + type + "," + minDrinks + "," + maxPeople + "," + tableSlot1 + "," + tableSlot2 + "\n";

    }

}