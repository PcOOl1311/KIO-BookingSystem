package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Table;
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

public class Menus {
    public static void mainMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println(
                    "Choose an option: \n" +
                            "1.Login/Registration\n" +
                            "2.Continue as customer\n" +
                            "3.EXIT\n");

            option = 0;
            option = input.nextInt();
            switch (option) {
                case 1: {
                    loginRegisterMenu();
                }
                break;
                case 2: {
                    customerMenu();
                }
                break;
                case 3: {
                    System.out.println("Goodbye\n");
                    System.exit(0);
                }
                default:
                    System.out.println("Please enter an integer from 1-3\n");
            }
        }
    }

    public static void loginRegisterMenu() throws FileNotFoundException {
        User activeUser;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option: \n" +
                    "1.Login\n" +
                    "2.Register\n" +
                    "3.BACK\n");

            int option = 0;
            option = input.nextInt();
            String Junk = input.nextLine();
            switch (option) {
                case 1: {
                    System.out.println("Logging in, type BACK in username if you want to return\n");
                    System.out.println("Insert your username: ");
                    String username = input.nextLine();
                    if (username.toUpperCase(Locale.ROOT).equals("BACK")) {
                        break;
                    }

                    System.out.println("Insert your password: ");
                    String password = input.nextLine();
                    ;
                }
                break;
                case 2: {
                    System.out.println("Registering, type BACK in username if you want to return\n");
                    System.out.println("Insert a username: ");
                    String username = input.nextLine();
                    while (true) {
                        System.out.println("Insert a password: ");
                        String password = input.nextLine();
                        System.out.println("Verify your password: ");
                        String passwordVer = input.nextLine();
                        if (Objects.equals(password, passwordVer)) {
                            System.out.println("Insert phone number");
                            String number = input.nextLine();
                            System.out.println("Account has been registered successfully");
                            break;
                        } else {
                            System.out.println("Passwords don't match try again");
                        }
                    }
                }
                case 3: {
                    break;
                }
                default:
                    System.out.println("Please enter an integer from 1-3\n");
            }
            //TODO if user type is admin or employee show specific menu
            employeeMenu();
        }
    }


    public static void customerMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to do? \n");
        System.out.println("1. Make a reservation\n" +
                "2. Check availability\n" +
                "3. Check your reservations\n" +
                "4. BACK");
        int option = 0;
        option = input.nextInt();
        String Junk = input.nextLine();
        switch (option) {
            case 1:
                Days tempDay = null;
                TimeSlot tempTimeSlot = null;
                System.out.println("Insert Table Name: \n");
                String reservationName = input.nextLine();

                System.out.println("Insert Day: \n");
                String day = input.nextLine();
                if (Objects.equals(day, "MONDAY")) tempDay = Days.MONDAY;
                else if (Objects.equals(day, "TUESDAY")) tempDay = Days.TUESDAY;
                else if (Objects.equals(day, "WEDNESDAY")) tempDay = Days.WEDNESDAY;
                else if (Objects.equals(day, "THURSDAY")) tempDay = Days.THURSDAY;
                else if (Objects.equals(day, "FRIDAY")) tempDay = Days.FRIDAY;
                else if (Objects.equals(day, "SATURDAY")) tempDay = Days.SATURDAY;
                else if (Objects.equals(day, "SUNDAY")) tempDay = Days.SUNDAY;

                System.out.println("Insert Time Slot: \n");                //TODO search function for availability
                String timeSlot = input.nextLine();
                if (Objects.equals(timeSlot, "EARLY")) tempTimeSlot = TimeSlot.EARLY;
                else if (Objects.equals(timeSlot, "LATE")) tempTimeSlot = TimeSlot.LATE;
                TableSlot tempTableSlot = new TableSlot(reservationName, tempTimeSlot, tempDay);

                //SEARCH FUNCTION

                System.out.println("Insert Phone Number: \n");
                String phoneNumber = input.nextLine();
                System.out.println("Insert Table Type: \n");
                String tableType = input.nextLine();
                System.out.println("Insert Drink Type: \n");
                String drinkType = input.nextLine();

            case 2:

            case 3:

            case 4:
                break;

            default:
                System.out.println("Please enter an integer from 1-4\n");
        }
    }
    public static void employeeMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do? \n" +
                    "1. Add Reservation\n" +
                    "2. Search Reservation\n" +
                    "3. View Reservations \n" +
                    "4. BACK \n");

            int option = 0;
            switch (option) {
                case 1:
                    System.out.println("Set Reservee Name: \n");
                    String reserveeName = input.nextLine();
                    System.out.println("Set Table: \n");
                    String table = input.nextLine();
                    System.out.println("Set Number of People: \n");
                    int peopleNum = input.nextInt();
                    System.out.println("Set Drink Type");
                    String drinkType = input.nextLine();

                    System.out.println("Reservation Set Successfully!");
                    break;

                case 2:
                    reservationMenuEmployee(); //TODO add the search function here AND call on menu to manage reservation
                    break;

                case 3:
                    ArrayList<TableSlot> tableSLots = TableManagement.readTableSlotFromFile();
                    System.out.println(tableSLots);
                    break;

                case 4: break;

                default:
                    System.out.println("Please type in an integer from 1-4");

            }


        }
    }
    public static void reservationMenuEmployee(User user){
        if (user.getUserType().equals(UserType.EMPLOYEE)){
            int option = 0;
            while(true)

                System.out.println("What do you want to do? \n" +
                        "1. Edit Reservation\n" +
                        "2. Delete Reservation\n" +
                        "3. Get Reservation \n" +
                        "4. BACK \n");

                switch(option){
                    case 1:
        }
        else if (user.getUserType().equals(UserType.CUSTOMER)){
        }
        }
    }
}