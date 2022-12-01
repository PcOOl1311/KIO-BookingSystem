package edu.acg.kio.kiobookingsystem.testing;

import edu.acg.kio.kiobookingsystem.classes.*;
import edu.acg.kio.kiobookingsystem.enumerators.*;
import edu.acg.kio.kiobookingsystem.functions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static edu.acg.kio.kiobookingsystem.functions.TableManagement.copyFile;


public class TestingFunctions {
    static String pathName = "files/tables.csv";
    public static void main(String[] args) throws IOException {

        //TESTING ALL CLASSES
        /*
        Drink d1 = new Drink("vodka",90.2 );
        System.out.println(d1);

        User u1 = new User("babis","iambabis2000","6923573621", UserType.CUSTOMER);
        System.out.println(u1);

        TableSlot ts1 = new TableSlot(TimeSlot.LATE,u1,d1,5);
        System.out.println(ts1);

        Table t1 = new Table("v1",TableType.VIP,3,8,ts1,ts1);
        System.out.println(t1);

        Reservation r1= new Reservation(u1,t1,TimeSlot.LATE,"Monday");
        System.out.println(r1);
        */


       // ArrayList<Drink> drinks = DrinkManagement.readDrinksFromFile();
       // System.out.println(drinks);
        //ArrayList<User> users = UserManagement.readUsersFromFile();
        //System.out.println(users);
        //ArrayList<TableSlot> tableSLots = TableManagement.readTableSlotFromFile();
        //System.out.println(tableSLots);
        //ArrayList<Table> tables = TableManagement.readTableFromFile(pathName);
        copyFile();

    }



}
