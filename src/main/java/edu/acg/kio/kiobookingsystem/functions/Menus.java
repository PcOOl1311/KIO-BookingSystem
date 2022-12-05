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
        Scanner input = new Scanner(System.in); //New Scanner
        User loggedInUser = null; //Initialize value
        User Guest = new User("Guest", "-", "None", UserType.GUEST); //New object creation
        while (true) {
            while (true) { //While loop for menu
                    int option=0;
                while (option == 0) {
                    System.out.println(
                        "Choose an option: \n" +
                                "1.Login/Registration\n" +
                                "2.Continue as customer\n" +
                                "3.EXIT\n");
                    option = userValidation(1,3); //Check for valid input
                }

                switch (option) { //Switch for each menu option
                    case 1: {
                        loggedInUser = loginRegisterMenu(); //this function returns active user, so put that in loggedinuser
                        break;
                    }
                    case 2: {
                        loggedInUser = Guest; //if option 2 --> then customer is guest
                        break;
                    }
                    case 3: {
                        System.out.println("Goodbye\n"); //Option 3 --> exit the program
                        System.exit(0);
                    }
                    default:
                        System.out.println("Please enter an integer from 1-3\n");
                }
            try{
            if (loggedInUser.getUserType().equals(UserType.CUSTOMER)) employeeMenu(loggedInUser); //Depending on user type
            else if (loggedInUser.getUserType().equals(UserType.EMPLOYEE)) employeeMenu(loggedInUser); //Display the appropriate menu
            else if (loggedInUser.getUserType().equals(UserType.ADMIN)) adminMenu(loggedInUser);
            else if (loggedInUser.getUserType().equals(UserType.GUEST)) customerMenu(loggedInUser);
            }catch (Exception ignored){
            }
            }
        }

    }




    public static User loginRegisterMenu() throws FileNotFoundException {
        User activeUser = null; //Initialize value
        Scanner input = new Scanner(System.in); //New scanner
        while (true) { //Loop for menu
            int option = 0; //Option used for switch, so initialize it
            while (option == 0) {
                System.out.println("Choose an option: \n" +
                        "1.Login\n" +
                        "2.Register\n" +
                        "3.BACK\n");
                option = userValidation(1,3); //validate the input
            }

            switch (option) {
                case 1: {
                    activeUser = login(); //Login returns tempUser, put that in activeUser
                    if (activeUser == null) continue; //if there is no activeUser then repeat
                    else if (activeUser != null) break; //If activeUser exists then exit the loop and continue
                }
                case 2: {
                    activeUser = register(); //Similar to above
                    if(activeUser==null)continue;
                    else if(activeUser!=null)continue;
                }
                case 3: {
                    break; //Exit loop
                }
                default:

                    System.out.println("Please enter an integer from 1-3\n");
            }
        return activeUser;
        }
    }


    public static void customerMenu(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in); //New Scanner
        while (true) {
            int option = 0; //Initialize option for switch
            while (option == 0) { //Loop for menu
                System.out.println("What do you want to do? \n");
                System.out.println(
                        "1. Make a reservation\n" +
                                "2. Check availability\n" +
                                "3. BACK");
                option = userValidation(1, 3); //Validate the input
            }
            switch (option) {
                case 1:
                    newReservation(loggedInUser); //Call the newReservation function with loggedInUser as parameter
                    continue;//Repeat loop

                case 2:
                    showAvailability("empty"); //Calling the showAvailability function, empty is in the parameter for all available days and hours
                    continue; //Repeat loop
                case 3:
                    break; //Exit loop

                default:
                    System.out.println("Please enter an integer from 1-3\n");
            }
            break; //Exit loop
        }
    }

    public static void employeeMenu(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in); //New Scanner
        while (true) {
            int option = 0; //Initialize option for switch
            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Add Reservation\n" +
                        "2. Search Reservation\n" +
                        "3. View Reservations \n" +
                        "4. BACK \n");
                option = userValidation(1, 4); //validate input
            }

            switch (option) {
                case 1:
                    TableSlot newTableSlot = newReservation(loggedInUser); //Create new object based on reservation made in function
                    if(newTableSlot != null) System.out.println("Reservation Made Successfully"); //If there is no reservation that matches, create new reservation
                    else System.out.println("Reservation not made"); //If found, don't make new reservation
                    continue;
                case 2:
                    reservationMenuEmployee(loggedInUser);
                    continue;

                case 3:
                    showAvailability("full"); //Show all reservations made
                    continue;

                case 4: break;

                default:
                    System.out.println("Please type in an integer from 1-4");

            }
        }
    }

    public static void reservationMenuEmployee(User user) throws IOException {
        if (user.getUserType().equals(UserType.EMPLOYEE)) { //if the user is employee
            int option = 0; //initialize option for switch
            ArrayList<TableSlot> tableSlots = readTableSlotFromFile(); //made array list by reading from tableSlots csv file
            Scanner input = new Scanner(System.in); //New scanner
            System.out.println("Search for Reservation");

            TableSlot reservation = searchTableSlot(input.nextLine(),tableSlots); //based on user's input search the arraylist for that name and return it
            while (true) {
                while (option == 0) {
                    System.out.println("What do you want to do? \n" +
                            "1. Edit Reservation\n" +
                            "2. Delete Reservation\n" +
                            "3. Get Reservation \n" +
                            "4. BACK \n");
                    option = userValidation(1, 4); //validate input
                }

                switch (option) {
                    case 1:
                        System.out.println("What do you want to edit?(tableName timeSlot drink amountOfPeople day");
                        String userInput = input.nextLine(); //accept input
                        reservation = editTableSlot(reservation,userInput);
                        continue;
                    case 2:

                        tableSlots.remove(reservation); //if second option delete the reservation from tableSlots
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
        Scanner input = new Scanner(System.in); //New scanner
        while (true) {
            int option = 0; //initiliaze option for switch
            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Manage Tables\n" +
                        "2. Manage Reservations\n" +
                        "3. Manage Drinks \n" +
                        "4. Sign Out \n");
                option = userValidation(1, 4); //validate input
            }
            switch (option) {
                case 1:
                    System.out.println(" Manage Tables");
                    adminManageTables(loggedInUser); //Show menu for tables
                    continue;
                case 2:
                    System.out.println("Manage Reservations");
                    adminManageReservations(loggedInUser); //Show menu for reservations
                    continue;
                case 3:
                    System.out.println("Manage Drinks");
                    adminManageDrinks(loggedInUser); //Show menu for drinks
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
        Scanner input = new Scanner(System.in); //New scanner
        ArrayList<Table> tables = readTableFromFile("files/tables.csv"); //Make array list for tables.csv
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
            switch (option) {
                case 1:
                    tables = newTable();
                    copyFile();
                    writeTablesToFile(tables);
                    continue;
                case 2:
                    System.out.println("Edit Table");
                    continue;
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
        ArrayList<TableSlot> tableSlots = readTableSlotFromFile(); //New array list for tableSlots
        TableSlot tempTableSlot = null; //initialize values
        int option = 0;
        while (true) {

            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Add TableSlot\n" +
                        "2. Edit TableSlot\n" +
                        "3. Delete TableSlot \n" +
                        "4. Get TableSlot \n" +
                        "5. BACK \n");
                option = userValidation(1, 5);
            }
            switch (option) {
                case 1:
                    newReservation(loggedInUser); //make new reservation for admin
                    break;
                case 2:
                    System.out.println("What Reservation do you want to edit?");
                    tempTableSlot = searchTableSlot(input.nextLine(),tableSlots); //search if this reservation exists
                    System.out.println("What do you want to edit(tableName timeSlot drink amountOfPeople day)");
                    String searchTerm = input.nextLine();
                    editTableSlot(tempTableSlot,searchTerm); //edit property based on user input
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
                    Drink tempDrink = searchDrink(input.nextLine(),drinks); //search for drink, if found
                    drinks.remove(tempDrink); //then delete
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

