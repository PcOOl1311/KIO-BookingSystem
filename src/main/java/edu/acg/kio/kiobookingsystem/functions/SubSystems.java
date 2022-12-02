package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;
import edu.acg.kio.kiobookingsystem.classes.TableSlot;
import edu.acg.kio.kiobookingsystem.classes.User;
import edu.acg.kio.kiobookingsystem.enumerators.Days;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static edu.acg.kio.kiobookingsystem.functions.DrinkManagement.searchDrink;
import static edu.acg.kio.kiobookingsystem.functions.TableManagement.searchTableSlot;
import static edu.acg.kio.kiobookingsystem.functions.UserManagement.searchUser;

public class SubSystems {
    static ArrayList<User> users;

    static ArrayList<Drink> drinks;
    static {
        try {
            drinks = DrinkManagement.readDrinksFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            users = UserManagement.readUsersFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SubSystems() throws FileNotFoundException {
    }

    public static TableSlot newReservation() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.println("Set Customer Name: \n");
        String customerName = input.nextLine();
        User user = searchUser(customerName,users);

        System.out.println("Set Table: \n");
        String table = input.nextLine();

        System.out.println("Set Time Slot: \n");
        TimeSlot timeSlot = TimeSlot.valueOf(input.nextLine());

        System.out.println("Set Drink Type: \n");
        String drinkType = input.nextLine();
        Drink drink = searchDrink(drinkType,drinks);

        System.out.println("Set Number of People: \n");
        int peopleNum = input.nextInt();

        System.out.println("Set Day : \n");
        Days day = Days.valueOf(input.nextLine());

        TableSlot tempTS = new TableSlot(table,timeSlot,user,drink,peopleNum,day);
        ArrayList<TableSlot> tableSLots = TableManagement.readTableSlotFromFile();
        if (!Objects.equals(searchTableSlot(String.valueOf(tempTS.getCustomer()), tableSLots).getCustomer(),user)){


        }
        System.out.println("Reservation Set Successfully!");

    }
}
