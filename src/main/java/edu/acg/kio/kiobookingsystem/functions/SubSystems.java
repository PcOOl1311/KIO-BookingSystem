package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;
import edu.acg.kio.kiobookingsystem.classes.Table;
import edu.acg.kio.kiobookingsystem.classes.TableSlot;
import edu.acg.kio.kiobookingsystem.classes.User;
import edu.acg.kio.kiobookingsystem.enumerators.Days;
import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;
import edu.acg.kio.kiobookingsystem.enumerators.UserType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static edu.acg.kio.kiobookingsystem.enumerators.UserType.GUEST;
import static edu.acg.kio.kiobookingsystem.functions.DrinkManagement.searchDrink;
import static edu.acg.kio.kiobookingsystem.functions.DrinkManagement.writeDrinksToFile;
import static edu.acg.kio.kiobookingsystem.functions.TableManagement.*;
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
//Validation of the input of the User for the Menu options
    public static int userValidation(int minRange,int maxRange){
        Scanner input = new Scanner(System.in);
        int returnValue = 0;
        try {
            while (true) {
                System.out.println("Insert a Value Between " + minRange + " and " + maxRange);

                returnValue = input.nextInt();
                if (returnValue >= minRange && returnValue <= maxRange) return returnValue;
                else System.out.println("Invalid Input");


            }
        }catch (Exception e){
            System.out.println("Invalid Input");
        }
        return returnValue;
    }

        //Edit Function the Reservations
    public static TableSlot editTableSlot(TableSlot tableSlot,String searchTerm) throws IOException {
        Scanner input = new Scanner(System.in);
        ArrayList<TableSlot> tableSlots = readTableSlotFromFile();
        TableSlot tempTableSlot = tableSlot;
        String tableName = tableSlot.getTableName();
        TimeSlot timeSlot = tableSlot.getTimeSlot();
        User customer = tableSlot.getCustomer();
        Drink drink = tableSlot.getDrink();
        int amountOfPeople = tableSlot.getAmountOfPeople();
        Days day = tableSlot.getDay();
        while (true) {

            switch (searchTerm.toLowerCase(Locale.ROOT)) {
                case "tablename":
                    System.out.println("input the table you want ");
                    tableName = input.nextLine();
                    break;
                case "tableslot":
                    while (true) {
                        System.out.println("Input EARLY or LATE");
                        String userInput = input.nextLine();
                        try {
                            timeSlot = TimeSlot.valueOf(userInput);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Input");
                        }
                    }
                case "drink":
                    System.out.println("Input new Drink name");
                    drink = searchDrink(input.nextLine(), drinks);
                    break;
                case "amountofpeople":
                    System.out.println("input new amount of people");
                    amountOfPeople = input.nextInt();
                    String Junk = input.nextLine();
                    break;
                case "day":
                    System.out.println("What day you want");
                    day = Days.valueOf(input.nextLine());
                    break;
                default:
                    System.out.println("Invalid Search term");
                    continue;
            }

        tempTableSlot= new TableSlot(tableName,timeSlot,customer,drink,amountOfPeople,day);
            if(searchIfTableSlotExists(tempTableSlot,tableSlots)==null){
                writeTableSlotsToFile(tableSlots);
                return tempTableSlot;
            }
            else {
                System.out.println("This reservation is the same as something else");
            }

        }
    }
        //Show available tables either free or occupied
    public static void showAvailability(String availability) throws FileNotFoundException {
        ArrayList<TableSlot> tableSlots = readTableSlotFromFile();
        ArrayList<Table> tablesM = readTableFromFile("files/tablesPerWeek/tablesM.csv");
        ArrayList<Table> tablesT = readTableFromFile("files/tablesPerWeek/tablesT.csv");
        ArrayList<Table> tablesW = readTableFromFile("files/tablesPerWeek/tablesW.csv");
        ArrayList<Table> tablesR = readTableFromFile("files/tablesPerWeek/tablesR.csv");
        ArrayList<Table> tablesF = readTableFromFile("files/tablesPerWeek/tablesF.csv");
        ArrayList<Table> tablesST = readTableFromFile("files/tablesPerWeek/tablesST.csv");
        ArrayList<Table> tablesSU = readTableFromFile("files/tablesPerWeek/tablesSU.csv");
        insertReservations(tableSlots,tablesM,Days.MONDAY);
        insertReservations(tableSlots,tablesT,Days.TUESDAY);
        insertReservations(tableSlots,tablesW,Days.WEDNESDAY);
        insertReservations(tableSlots,tablesR,Days.THURSDAY);
        insertReservations(tableSlots,tablesF,Days.FRIDAY);
        insertReservations(tableSlots,tablesST,Days.SATURDAY);
        insertReservations(tableSlots,tablesSU,Days.SUNDAY);
        System.out.println("For Monday:");
        weeklyAvailability(tablesM,availability) ;
        System.out.println("---------------------------------");
        System.out.println("For Tuesday:");
        weeklyAvailability(tablesT,availability);
        System.out.println("---------------------------------");
        System.out.println("For WednesdayY:");
        weeklyAvailability(tablesW,availability);
        System.out.println("---------------------------------");
        System.out.println("For Thursday:");
        weeklyAvailability(tablesR,availability);
        System.out.println("---------------------------------");
        System.out.println("For Friday");
        weeklyAvailability(tablesF,availability);
        System.out.println("---------------------------------");
        System.out.println("For Saturday:");
        weeklyAvailability(tablesST,availability);
        System.out.println("---------------------------------");
        System.out.println("For Sunday:");
        weeklyAvailability(tablesSU,availability);
        System.out.println("---------------------------------");
    }


    public static void weeklyAvailability(ArrayList<Table> table,String availability) {
        //sample users that show the availability
        User u1 = new User("-","emptyObject","888-444-3333",GUEST);
        User u2 = new User("--","emptyObject","888-444-3333",GUEST);
        //Check if we want to see empty of full reservations
        if(Objects.equals(availability, "empty")) {
            for (Table t : table) {
                if (t.getTableSlot1().getCustomer().getName().equals("-")) {
                    System.out.println(t.getTableName() + " " + t.getTableSlot1().getTimeSlot().toString() + " " + t.getTableSlot1().getDay().toString());
                    ;
                }
                if (t.getTableSlot2().getCustomer().getName().equals("--")) {
                    System.out.println(t.getTableName() + " " + t.getTableSlot2().getTimeSlot().toString() + " " + t.getTableSlot2().getDay().toString());
                }
            }
        }else if(Objects.equals(availability, "full")) {
            for (Table t : table) {
                if (!t.getTableSlot1().getCustomer().getName().equals("-")) {
                    System.out.println(t.getTableName() + " " + t.getTableSlot1().getTimeSlot().toString()
                            + " " + t.getTableSlot1().getDay().toString()
                            + " " +t.getTableSlot1().getCustomer().getName());
                    ;
                }
                if (!t.getTableSlot2().getCustomer().getName().equals("--")) {
                    System.out.println(t.getTableName() + " " + t.getTableSlot2().getTimeSlot().toString()
                            + " " + t.getTableSlot2().getDay().toString()
                            + " " + t.getTableSlot2().getCustomer().getName());
                }
            }
        }
    }

        // login function that returns the logged-in user
    public static User login() {
        Scanner input = new Scanner(System.in);
        User tempUser = null;
        while (true) {
            CLS();
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
                        break;
                    } else if (!Objects.equals(tempUser.getPassword(), password)) {
                        System.out.println("Wrong password, try again");
                    }
                }
            }
            if (tempUser != null) break;
        }
        return tempUser;

    }
        // Creation of new user and inserting into the files
    public static User register() {
        User tempUser = null;
        Scanner input = new Scanner(System.in);
        while (true) {
            CLS();
            System.out.println("Registering, type BACK in username if you want to return\n");
            System.out.println("Insert a username: ");
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
                } else {
                    System.out.println("Passwords don't match try again");
                }

            }
            System.out.println("Insert phone number");
            String number = input.nextLine();

            if (searchUser(username, users) == null) {
                tempUser = new User(username, password, number, UserType.CUSTOMER);
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
        users.add(tempUser);
        return tempUser;
    }

    public static void CLS() {
        // CLEARING SCREEN COMMAND (START)
        System.out.println("\033[H\033[2J");
        System.out.flush();
        // CLEARING SCREEN COMMAND (END)
    }
    //Validating the type of table VIP , BACKSTAGE, NORMAL
    public static TableType tableTypeValidation(){
        TableType tableType = null;
        Scanner input = new Scanner(System.in);
        while (tableType== null) {
            System.out.println("What type of Table do you want to add?");
            String userInput = input.nextLine();
            try {
                if (TableType.valueOf(userInput) == TableType.NORMAL) tableType = TableType.NORMAL;
                else if (TableType.valueOf(userInput) == TableType.VIP) tableType = TableType.VIP;
                else if (TableType.valueOf(userInput) == TableType.BACKSTAGE) tableType = TableType.BACKSTAGE;
                else
                    System.out.println("Please type NORMAL , VIP or BACKSTAGE");
            }catch (Exception e){
                System.out.println("Invalid Input");
            }
        }
        return tableType;
    }

    //New table function to add a table write on the files and replace all the weeks tables;
    public static ArrayList<Table> newTable() throws IOException, IOException {
        ArrayList<Table> tables = readTableFromFile("files/tables.csv");
        ArrayList<TableSlot> tableSlots =readTableSlotFromFile();
        Table tempTable = null;
        int newNumTables =0;
        Scanner input = new Scanner(System.in);
        TableType tableType = null;
        String tableName;
        int minDrinks = 0;
        int maxPeople = 0;
        TableSlot tableSlotE = searchTableSlot("emptyE",tableSlots);
        TableSlot tableSlotL = searchTableSlot("emptyL",tableSlots);
        tableType = tableTypeValidation();
        if(tableType.equals(TableType.NORMAL)) {
            while (newNumTables == 0) {
                System.out.println("How many new Normal Tables do you want?");
                newNumTables = userValidation(1, 10);
            }
        }

        else if(tableType.equals(TableType.VIP)) {
            while (newNumTables == 0) {
                System.out.println("How many new VIP Tables do you want?");
                newNumTables = userValidation(1, 10);
            }
        }

        else if(tableType.equals(TableType.BACKSTAGE)) {
            while (newNumTables == 0) {
                System.out.println("How many new BackStage Tables do you want?");
                newNumTables = userValidation(1, 10);
            }
        }
        System.out.println("How many are the Minimum Drinks? ");
        while (minDrinks == 0) {
            System.out.println("Set minimum Drinks ");
            minDrinks = userValidation(1,10);
        }
        while (maxPeople == 0) {
            System.out.println("Set maximum Amount People");
            maxPeople = userValidation(6,20);
        }
        for(int i = 1;i<=newNumTables;i++){

            while (true){
                System.out.println("Give me a table name");
                tableName = input.nextLine();
                if (searchTable(tableName, tables) != null) {
                    System.out.println("This table Name Already Exists Enter Another One");
                } else System.out.println("Table Name Set");
                break;
            }
            tempTable = new Table(tableName,tableType,minDrinks,maxPeople,tableSlotE,tableSlotL);
            tables.add(tempTable);
        }
        updateTables(tables);
        return tables;
    }

        //Function to update the availability of the Tables
    public static void updateTables(ArrayList<Table> tables) throws IOException {
        ArrayList<TableSlot> tableSlots =readTableSlotFromFile();
        writeTablesToFile(tables);
        copyFile();
        ArrayList<Table> tablesM = TableManagement.readTableFromFile("files/tablesPerWeek/tablesM.csv");
        ArrayList<Table> tablesT = TableManagement.readTableFromFile("files/tablesPerWeek/tablesT.csv");
        ArrayList<Table> tablesW = TableManagement.readTableFromFile("files/tablesPerWeek/tablesW.csv");
        ArrayList<Table> tablesR = TableManagement.readTableFromFile("files/tablesPerWeek/tablesR.csv");
        ArrayList<Table> tablesF = TableManagement.readTableFromFile("files/tablesPerWeek/tablesF.csv");
        ArrayList<Table> tablesST = TableManagement.readTableFromFile("files/tablesPerWeek/tablesST.csv");
        ArrayList<Table> tablesSU = TableManagement.readTableFromFile("files/tablesPerWeek/tablesSU.csv");
        insertReservations(tableSlots,tablesM,Days.MONDAY);
        insertReservations(tableSlots,tablesT,Days.TUESDAY);
        insertReservations(tableSlots,tablesW,Days.WEDNESDAY);
        insertReservations(tableSlots,tablesR,Days.THURSDAY);
        insertReservations(tableSlots,tablesF,Days.FRIDAY);
        insertReservations(tableSlots,tablesST,Days.SATURDAY);
        insertReservations(tableSlots,tablesSU,Days.SUNDAY);

    }
        //Function of add a new drink option
    public static Drink addDrink(ArrayList<Drink> drinks) throws IOException {
        Scanner input = new Scanner(System.in);
        Drink tempDrink = null;
        Double price = 0.0;
        System.out.println("Input new Drink's Name");
        String name = input.nextLine();
       while (true) {
           System.out.println("insert a price");
           String userInput = input.nextLine();
           try {
               price = Double.parseDouble(userInput);
               break;
           }catch (Exception e){
               System.out.println("Invalid Input please enter a double");
           }
       }
       tempDrink= new Drink(name,price);
       drinks.add(tempDrink);
       writeDrinksToFile(drinks);
        return tempDrink;
    }
        //New reservation function
    public static TableSlot newReservation(User loggedInUser) throws IOException {
        ArrayList<TableSlot> tableSlots = readTableSlotFromFile();
        ArrayList<Table> tables = readTableFromFile("Files/tables.csv");
        Scanner input = new Scanner(System.in);
        Days tempDay = null;
        TimeSlot tempTimeSlot = null;

        while (true) {
            System.out.println("Insert Table Name: \n");
            String tableName = input.nextLine();
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
            TableSlot tempTableSlot = new TableSlot(tableName, tempTimeSlot, tempDay);

            //With the tempTableSlot we will browse through the tableSlots.csv file and check based on reservation name, timeslot and dau to see what's available
            //if those three getters are equal to the same values of one object then that reservation time is taken
            //if all three getters dont match with the values of one object then the reservation time is available and can be set

                System.out.println("Insert Phone Number: \n");
                String phoneNumber = input.nextLine();
                TableType tableType = searchTable(tableName,tables).getType();
                System.out.println("Insert Drink Type: \n");
                Drink drink = searchDrink(input.nextLine(), drinks);
                System.out.println("Insert Amount of People: \n");
                int amountOfPeople = input.nextInt();
                TableSlot newTableSlot = new TableSlot(tableName, tempTimeSlot, loggedInUser, drink, amountOfPeople, tempDay);
                tableSlots.add(newTableSlot);
                System.out.println("New Reservation Created Successfully!");
                System.out.println(newTableSlot);
                System.out.println(tableSlots);
                writeTableSlotsToFile(tableSlots);
             /*else if (searchTableSlot(String.valueOf(tempTableSlot), tableSlots) != null) {
                System.out.println("This Reservation is Unavailable");
                continue;
            } */
            return tempTableSlot;
        }
    }
}
