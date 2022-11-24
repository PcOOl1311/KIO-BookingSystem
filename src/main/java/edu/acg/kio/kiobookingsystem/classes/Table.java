package edu.acg.kio.kiobookingsystem.classes;

import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

public class Table {
    private TableType type;
    private int minDrinks;
    private int maxPeople;
    private TableSlot tableSlot1;
    private TableSlot tableSlot2;


    // CONSTRUCTOR //



    // RETURN VALUES  //
    @Override
    public String toString() {
        return "------------------------------------" + " \n" +
                " Table Type      = " + type + " \n" +
                " Minimum Drinks  = " + minDrinks + " \n" +
                " Customer Slot 1 = " + minDrinks + " \n" +
                " Customer Slot 2 = " + minDrinks + " \n" +
                " Drinks Slot 1   = " + minDrinks + " \n" +
                " Drinks Slot 2" + minDrinks + " \n" +
                " Price  = " + minDrinks + " \n" +
                "------------------------------------";
    }


}
