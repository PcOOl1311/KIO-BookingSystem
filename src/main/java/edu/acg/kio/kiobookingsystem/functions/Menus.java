package edu.acg.kio.kiobookingsystem.functions;

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

import static edu.acg.kio.kiobookingsystem.functions.SubSystems.*;

public class Menus {
    public static void mainMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int option;

        while (true) {
            try {
                option = 0;
                System.out.println(
                        "Choose an option: \n" +
                                "1.Login/Registration\n" +
                                "2.Continue as customer\n" +
                                "3.EXIT\n");

                option = input.nextInt();
                String Junk = input.nextLine();

                switch (option) {
                    case 1: {
                        while(true) {
                            int output = loginRegisterMenu();
                            if(output == 0){
                                break;
                            }
                            else if(output == 1){
                                continue;
                            }
                            continue;
                        }
                    }
                    case 2: {
                        customerMenu();
                        continue;
                    }
                    case 3: {
                        System.out.println("Goodbye\n");
                        System.exit(0);
                    }
                    default:
                        System.out.println("Please enter an integer from 1-3\n");
                }
            }
            catch(Exception e){
                System.out.println("Invalid Input please enter an integer from 1-3");
                continue;
            }
        }
    }

    public static int loginRegisterMenu() throws FileNotFoundException {
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
            int output=0;
            switch (option) {
                case 1: {
                    activeUser = login();
                    if (activeUser == null) continue;
                    else if (activeUser != null) continue;
                    break;
                }
                case 2: {
                    activeUser = register();
                    if(activeUser==null)continue;
                    else if(activeUser!=null)continue;
                }
                case 3: {
                    output = 1;
                    break;
                }
                default:

                    System.out.println("Please enter an integer from 1-3\n");
            }
            //TODO if user type is admin or employee show specific menu
            employeeMenu();
        return 0;
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
                    TableSlot newTableSlot = newReservation();
                    if(newTableSlot != null) System.out.println("Reservation Made Successfully");
                    else System.out.println("Reservation not made");
                    continue;
                case 2:
                    //reservationMenuEmployee(); //TODO add the search function here AND call on menu to manage reservation
                    continue;

                case 3:
                    ArrayList<TableSlot> tableSLots = TableManagement.readTableSlotFromFile();
                    System.out.println(tableSLots);
                    continue;

                case 4: break;

                default:
                    System.out.println("Please type in an integer from 1-4");

            }


        }
    }

    public static void reservationMenuEmployee(User user) {
        if (user.getUserType().equals(UserType.EMPLOYEE)) {
            int option = 0;
            while (true) {

                System.out.println("What do you want to do? \n" +
                        "1. Edit Reservation\n" +
                        "2. Delete Reservation\n" +
                        "3. Get Reservation \n" +
                        "4. BACK \n");

                switch (option) {
                    case 1:
                        System.out.println("Edit reservation");
                        break;
                    case 2:
                        System.out.println("Delete Reservation");
                        break;
                    case 3:
                        System.out.println("Get Reservation");
                        break;
                    case 4:
                        break;

                    default:
                        System.out.println("Please type in an integer from 1-4");
                }
            }
        } else if (user.getUserType().equals(UserType.CUSTOMER)) {
            customerMenu();
        }
    }

    public static void adminMenu() {
        int option = 0;
        while (true) {

            System.out.println("What do you want to do? \n" +
                    "1. Manage Tables\n" +
                    "2. Manage Reservations\n" +
                    "3. Manage Drinks \n" +
                    "4. Manage Users \n" +
                    "5. Sign Out \n");

            switch (option) {
                case 1:
                    System.out.println(" Manage Tables");
                    adminManageTables();
                    break;
                case 2:
                    System.out.println("Manage Reservations");
                    adminManageReservations();
                    break;
                case 3:
                    System.out.println("Manage Drinks");
                    adminManageDrinks();
                    break;
                case 4:
                    System.out.println("Manage Users");
                    adminManageUsers();
                    break;
                case 5:
                    System.out.println("Sign out");
                    break;
                default:
                    System.out.println("Please type in an integer from 1-5");
            }
        }
    }


    public static void adminManageTables() {
        int option = 0;
        while (true) {

            System.out.println("What do you want to do? \n" +
                    "1. Add Table\n" +
                    "2. Edit Table\n" +
                    "3. Delete Table \n" +
                    "4. Get Table \n" +
                    "5. BACK \n");

            switch (option) {
                case 1:
                    System.out.println("Add Table");
                    break;
                case 2:
                    System.out.println("Edit Table");
                    break;
                case 3:
                    System.out.println("Delete Table");
                    break;
                case 4:
                    System.out.println("Get Table");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-5");
            }
        }
    }

    public static void adminManageReservations() {
        int option = 0;
        while (true) {

            System.out.println("What do you want to do? \n" +
                    "1. Add TableSlot\n" +
                    "2. Edit TableSlot\n" +
                    "3. Delete TableSlot \n" +
                    "4. Get TableSlot \n" +
                    "5. BACK \n");

            switch (option) {
                case 1:
                    System.out.println("Add TableSlot");
                    break;
                case 2:
                    System.out.println("Edit TableSlot");
                    break;
                case 3:
                    System.out.println("Delete TableSlot");
                    break;
                case 4:
                    System.out.println("Get TableSlot");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-5");
            }
        }
    }

    public static void adminManageDrinks() {
        int option = 0;
        while (true) {

            System.out.println("What do you want to do? \n" +
                    "1. Add Drink\n" +
                    "2. Edit Drink\n" +
                    "3. Delete Drink \n" +
                    "4. BACK \n");

            switch (option) {
                case 1:
                    System.out.println("Add Drink");
                    break;
                case 2:
                    System.out.println("Edit Drink");
                    break;
                case 3:
                    System.out.println("Delete Drink");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-4");
            }
        }
    }

    public static void adminManageUsers() {
        int option = 0;
        while (true) {

            System.out.println("What do you want to do? \n" +
                    "1. Add New User\n" +
                    "2. Change Name\n" +
                    "3. Change Info \n" +
                    "4. Delete User \n" +
                    "5. Get User \n" +
                    "6. BACK \n");

            switch (option) {
                case 1:
                    System.out.println("Add New User");
                    break;
                case 2:
                    System.out.println("Change Name");
                    break;
                case 3:
                    System.out.println("Change Info");
                    break;
                case 4:
                    System.out.println("Delete User");
                    break;
                case 5:
                    System.out.println("Get User");
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-6");
            }
        }
    }
}

