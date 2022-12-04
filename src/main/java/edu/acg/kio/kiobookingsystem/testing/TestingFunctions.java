package edu.acg.kio.kiobookingsystem.testing;

import edu.acg.kio.kiobookingsystem.classes.*;
import edu.acg.kio.kiobookingsystem.enumerators.*;
import edu.acg.kio.kiobookingsystem.functions.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static edu.acg.kio.kiobookingsystem.functions.Menus.loginRegisterMenu;
import static edu.acg.kio.kiobookingsystem.functions.Menus.mainMenu;
import static edu.acg.kio.kiobookingsystem.functions.SubSystems.login;
import static edu.acg.kio.kiobookingsystem.functions.SubSystems.register;
import static edu.acg.kio.kiobookingsystem.functions.TableManagement.copyFile;
import static edu.acg.kio.kiobookingsystem.functions.TableManagement.insertReservations;


public class TestingFunctions {
    public static void main(String[] args) throws IOException {
        mainMenu();
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
        /*
        ArrayList<User> users = UserManagement.readUsersFromFile();
        System.out.println(users);
        ArrayList<TableSlot> tableSLots = TableManagement.readTableSlotFromFile();
        System.out.println(tableSLots);

        //String[] days ={"M","T","W","R","F","ST","SU"};
        String pathName = "files/tables.csv";



        ArrayList<Table> tables = TableManagement.readTableFromFile(pathName);
        copyFile();
        //System.out.println(tables);
        ArrayList<Table> tablesM = TableManagement.readTableFromFile("files/tablesPerWeek/tablesM.csv");
        ArrayList<Table> tablesT = TableManagement.readTableFromFile("files/tablesPerWeek/tablesT.csv");
        ArrayList<Table> tablesW = TableManagement.readTableFromFile("files/tablesPerWeek/tablesW.csv");
        ArrayList<Table> tablesR = TableManagement.readTableFromFile("files/tablesPerWeek/tablesR.csv");
        ArrayList<Table> tablesF = TableManagement.readTableFromFile("files/tablesPerWeek/tablesF.csv");
        ArrayList<Table> tablesST = TableManagement.readTableFromFile("files/tablesPerWeek/tablesST.csv");
        ArrayList<Table> tablesSU = TableManagement.readTableFromFile("files/tablesPerWeek/tablesSU.csv");
        insertReservations(tableSLots,tablesM,Days.MONDAY);
        insertReservations(tableSLots,tablesT,Days.TUESDAY);
        insertReservations(tableSLots,tablesW,Days.WEDNESDAY);
        insertReservations(tableSLots,tablesR,Days.THURSDAY);
        insertReservations(tableSLots,tablesF,Days.FRIDAY);
        insertReservations(tableSLots,tablesST,Days.SATURDAY);
        insertReservations(tableSLots,tablesSU,Days.SUNDAY);
        //System.out.println(tablesM);
        //System.out.println(tablesT);
        //System.out.println(tablesW);
        //System.out.println(tablesR);
        //System.out.println(tablesF);
        //System.out.println(tablesST);
        //System.out.println(tablesSU);

    }
    public static void CLS(){
        // CLEARING SCREEN COMMAND (START)
        System.out.println("\033[H\033[2J");
        System.out.flush();
        // CLEARING SCREEN COMMAND (END)
    }
} */
    }
}

