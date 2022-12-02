package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;
import edu.acg.kio.kiobookingsystem.classes.TableSlot;
import edu.acg.kio.kiobookingsystem.classes.User;
import edu.acg.kio.kiobookingsystem.enumerators.Days;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;
import edu.acg.kio.kiobookingsystem.enumerators.UserType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static edu.acg.kio.kiobookingsystem.functions.DrinkManagement.searchDrink;
import static edu.acg.kio.kiobookingsystem.functions.TableManagement.searchIfTableSlotExists;
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
        TableSlot tempTS = null;
        while (true) {
            Scanner input = new Scanner(System.in);
            User user = null;
            System.out.println("Set Customer Name: \n");
            String customerName = input.nextLine();
            if (customerName.equals("BACK")) return null;
            else {
                user = searchUser(customerName, users);
            }
            System.out.println("Set Table: \n");
            String table = input.nextLine();

            System.out.println("Set Time Slot: \n");
            TimeSlot timeSlot = TimeSlot.valueOf(input.nextLine());

            System.out.println("Set Drink Type: \n");
            String drinkType = input.nextLine();
            Drink drink = searchDrink(drinkType, drinks);

            System.out.println("Set Number of People: \n");
            int peopleNum = input.nextInt();

            System.out.println("Set Day : \n");
            Days day = Days.valueOf(input.nextLine());

            tempTS = new TableSlot(table, timeSlot, user, drink, peopleNum, day);
            ArrayList<TableSlot> tableSLots = TableManagement.readTableSlotFromFile();
            if (searchIfTableSlotExists(tempTS, tableSLots) != null) {
                break;

            } else {
                System.out.println("This Table and Time is occupied");
                return null;
            }
        }
        return tempTS;
    }

    public static User login() {
        Scanner input = new Scanner(System.in);
        User tempUser = null;
        while (true) {
            System.out.println("Logging in, type BACK in username if you want to return\n");
            System.out.println("Insert your username: ");
            String username = input.nextLine();
            if (username.toUpperCase(Locale.ROOT).equals("BACK")) {
                break;
            } else if (searchUser(username, users) == null) {
                System.out.println("User not Found, try again\n");
                continue;
            } else if (searchUser(username, users) != null) {
                tempUser = searchUser(username, users);
                while (true) {
                    System.out.println("Insert your password: ");
                    String password = input.nextLine();
                    if (tempUser.getPassword().equals(password)) {
                        System.out.println("Login Successful");
                        continue;
                    } else if (!Objects.equals(tempUser.getPassword(), password)) {
                        System.out.println("Wrong password, try again");
                    }
                }
            }

        }
        return tempUser;

    }

    public static User register() {
        User tempUser = null;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Insert a username: ");
            System.out.println("Registering, type BACK in username if you want to return\n");
            String username = input.nextLine();
            if (username.toUpperCase(Locale.ROOT).equals("BACK")) break;

            String password = null;
            while (true) {
                System.out.println("Insert a password: ");
                password = input.nextLine();

                System.out.println("Verify your password: ");
                String passwordVer = input.nextLine();

                if (Objects.equals(password, passwordVer)) {
                    break;
                }
                else {
                    System.out.println("Passwords don't match try again");
                }

            }
            System.out.println("Insert phone number");
            String number = input.nextLine();

            if (searchUser(username, users) == null) {
                tempUser = new User(username,password,number, UserType.CUSTOMER);
                System.out.println("Account has been registered successfully");
                break;
            } else if (searchUser(username, users) != null) {
                tempUser = searchUser(username, users);
                if (tempUser.getContactInfo().equals(number)) {
                    System.out.println("This account is already registered, try again");
                    continue;
                }
            }
            System.out.println("Account has been registered successfully");
            break;
        }
    return tempUser;
    }
}
