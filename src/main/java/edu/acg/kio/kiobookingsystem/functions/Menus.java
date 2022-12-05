package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;
import edu.acg.kio.kiobookingsystem.classes.Table;
import edu.acg.kio.kiobookingsystem.classes.TableSlot;
import edu.acg.kio.kiobookingsystem.classes.User;
import edu.acg.kio.kiobookingsystem.enumerators.UserType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.acg.kio.kiobookingsystem.functions.DrinkManagement.searchDrink;
import static edu.acg.kio.kiobookingsystem.functions.DrinkManagement.writeDrinksToFile;
import static edu.acg.kio.kiobookingsystem.functions.SubSystems.*;
import static edu.acg.kio.kiobookingsystem.functions.TableManagement.*;

public class Menus {
    public static void mainMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        User loggedInUser = null;
        User Guest = new User("Guest", "-", "None", UserType.GUEST);
        while (true) {
            while (true) {
                    int option=0;
                while (option == 0) {
                    System.out.println(
                        "Choose an option: \n" +
                                "1.Login/Registration\n" +
                                "2.Continue as customer\n" +
                                "3.EXIT\n");
                    option = userValidation(1,3);
                }

                switch (option) {
                    case 1: {
                        loggedInUser = loginRegisterMenu();
                        break;
                    }
                    case 2: {
                        loggedInUser = Guest;
                        break;
                    }
                    case 3: {
                        System.out.println("Goodbye\n");
                        System.exit(0);
                    }
                    default:
                        System.out.println("Please enter an integer from 1-3\n");
                }
            try{
            if (loggedInUser.getUserType().equals(UserType.CUSTOMER)) employeeMenu(loggedInUser);
            else if (loggedInUser.getUserType().equals(UserType.EMPLOYEE)) employeeMenu(loggedInUser);
            else if (loggedInUser.getUserType().equals(UserType.ADMIN)) adminMenu(loggedInUser);
            else if (loggedInUser.getUserType().equals(UserType.GUEST)) customerMenu(loggedInUser);
            }catch (Exception ignored){
            }
            }
        }

    }




    public static User loginRegisterMenu() throws FileNotFoundException {
        User activeUser = null;
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;
            while (option == 0) {
                System.out.println("Choose an option: \n" +
                        "1.Login\n" +
                        "2.Register\n" +
                        "3.BACK\n");
                option = userValidation(1,3);
            }

            switch (option) {
                case 1: {
                    activeUser = login();
                    if (activeUser == null) continue;
                    else if (activeUser != null) break;
                }
                case 2: {
                    activeUser = register();
                    if(activeUser==null)continue;
                    else if(activeUser!=null)continue;
                }
                case 3: {
                    break;
                }
                default:

                    System.out.println("Please enter an integer from 1-3\n");
            }
        return activeUser;
        }
    }


    public static void customerMenu(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;
            while (option == 0) {
                System.out.println("What do you want to do? \n");
                System.out.println(
                        "1. Make a reservation\n" +
                                "2. Check availability\n" +
                                "3. BACK");
                option = userValidation(1, 3);
            }
            switch (option) {
                case 1:
                    newReservation(loggedInUser);
                    continue;

                case 2:
                    showAvailability("empty");
                    continue;
                case 3:
                    break;

                default:
                    System.out.println("Please enter an integer from 1-3\n");
            }
            break;
        }
    }

    public static void employeeMenu(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;
            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Add Reservation\n" +
                        "2. Search Reservation\n" +
                        "3. View Reservations \n" +
                        "4. BACK \n");
                option = userValidation(1, 4);
            }

            switch (option) {
                case 1:
                    TableSlot newTableSlot = newReservation(loggedInUser);
                    if(newTableSlot != null) System.out.println("Reservation Made Successfully");
                    else System.out.println("Reservation not made");
                    continue;
                case 2:
                    reservationMenuEmployee(loggedInUser);
                    continue;

                case 3:
                    showAvailability("full");
                    continue;

                case 4: break;

                default:
                    System.out.println("Please type in an integer from 1-4");

            }
        }
    }

    public static void reservationMenuEmployee(User user) throws IOException {
        if (user.getUserType().equals(UserType.EMPLOYEE)) {
            int option = 0;
            ArrayList<TableSlot> tableSlots = readTableSlotFromFile();
            Scanner input = new Scanner(System.in);
            System.out.println("Search for Reservation");

            TableSlot reservation = searchTableSlot(input.nextLine(),tableSlots);
            while (true) {
                while (option == 0) {
                    System.out.println("What do you want to do? \n" +
                            "1. Edit Reservation\n" +
                            "2. Delete Reservation\n" +
                            "3. Get Reservation \n" +
                            "4. BACK \n");
                    option = userValidation(1, 4);
                }

                switch (option) {
                    case 1:
                        System.out.println("What do you want to edit?(tableName timeSlot drink amountOfPeople day");
                        String userInput = input.nextLine();
                        reservation = editTableSlot(reservation,userInput);
                        continue;
                    case 2:

                        tableSlots.remove(reservation);
                        copyFile();

                        System.out.println("Reservation Deleted");
                        continue;
                    case 3:
                        System.out.println(reservation);
                        System.out.println("Get Reservation");
                        continue;
                    case 4:
                        break;
                    default:
                        System.out.println("Please type in an integer from 1-4");
                }
                break;
            }
        }
    }

    public static void adminMenu(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;
            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Manage Tables\n" +
                        "2. Manage Reservations\n" +
                        "3. Manage Drinks \n" +
                        "4. Sign Out \n");
                option = userValidation(1, 4);
            }
            switch (option) {
                case 1:
                    System.out.println(" Manage Tables");
                    adminManageTables(loggedInUser);
                    continue;
                case 2:
                    System.out.println("Manage Reservations");
                    adminManageReservations(loggedInUser);
                    continue;
                case 3:
                    System.out.println("Manage Drinks");
                    adminManageDrinks(loggedInUser);
                    continue;

                case 4:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-4");
            }
            break;
        }
    }


    public static void adminManageTables(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in);
        ArrayList<Table> tables = readTableFromFile("files/tables.csv");
        int option = 0;
        while (true) {

            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Add Table\n" +
                        "2. Edit Table\n" +
                        "3. Delete Table \n" +
                        "4. Get Table \n" +
                        "5. BACK \n");
                option = userValidation(1, 5);
            }
            option = input.nextInt(); ;
            switch (option) {
                case 1:
                    tables = newTable();
                    System.out.println("Add Table");
                    break;
                case 2:
                    System.out.println("Edit Table");
                    break;
                case 3:
                    System.out.println("Search Table Name to be Deleted");
                    Table tempTable =  searchTable(input.nextLine(),tables);
                    tables.remove(tempTable);
                    writeTablesToFile(tables);
                    copyFile();
                    break;
                case 4:
                    System.out.println(tables);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-5");
            }
            break;
        }
    }

    public static void adminManageReservations(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in);
        ArrayList<TableSlot> tableSlots = readTableSlotFromFile();
        TableSlot tempTableSlot = null;
        int option = 0;
        while (true) {

            while (option == 0) {
                option = userValidation(1, 5);
                System.out.println("What do you want to do? \n" +
                        "1. Add TableSlot\n" +
                        "2. Edit TableSlot\n" +
                        "3. Delete TableSlot \n" +
                        "4. Get TableSlot \n" +
                        "5. BACK \n");
            }
            switch (option) {
                case 1:
                    newReservation(loggedInUser);
                    break;
                case 2:
                    System.out.println("What Reservation do you want to edit?");
                    tempTableSlot = searchTableSlot(input.nextLine(),tableSlots);
                    System.out.println("What do you want to edit(tableName timeSlot drink amountOfPeople day)");
                    String searchTerm = input.nextLine();
                    editTableSlot(tempTableSlot,searchTerm);
                    break;
                case 3:
                    System.out.println("What Reservation do you want to Delete?");
                    tempTableSlot = searchTableSlot(input.nextLine(),tableSlots);
                    tableSlots.remove(tempTableSlot);
                    continue;
                case 4:
                    System.out.println(tableSlots);
                    continue;
                case 5:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-5");
            }
        break;
        }
    }

    public static void adminManageDrinks(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;
            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Add Drink\n" +
                        "2. Delete Drink \n" +
                        "3. BACK \n");
                option = userValidation(1, 3);
            }
            switch (option) {
                case 1:
                    System.out.println("Add Drink");
                    addDrink(drinks);
                    break;
                case 2:
                    System.out.println("Select Drink to delete");
                    Drink tempDrink = searchDrink(input.nextLine(),drinks);
                    drinks.remove(tempDrink);
                    writeDrinksToFile(drinks);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-3");
            }
            break;
        }
    }


}

