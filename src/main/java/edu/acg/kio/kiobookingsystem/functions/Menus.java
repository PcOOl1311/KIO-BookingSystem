package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.TableSlot;
import edu.acg.kio.kiobookingsystem.classes.User;
import edu.acg.kio.kiobookingsystem.enumerators.UserType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.acg.kio.kiobookingsystem.functions.SubSystems.*;

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
        int option = 0;
        while (option == 0) {
            System.out.println("What do you want to do? \n");
            System.out.println(
                    "1. Make a reservation\n" +
                    "2. Check availability\n" +
                    "3. Check your reservations\n" +
                    "4. BACK");
            option = userValidation(1,4);
        }

        switch (option) {
            case 1:

                newReservation(loggedInUser);

            case 2:

            case 3:

            case 4:
                break;

            default:
                System.out.println("Please enter an integer from 1-4\n");
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
            break;
        }
    }

    public static void reservationMenuEmployee(User user) {
        if (user.getUserType().equals(UserType.EMPLOYEE)) {
            int option = 0;
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
                        "4. Manage Users \n" +
                        "5. Sign Out \n");
                option = userValidation(1, 5);
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
                    System.out.println("Manage Users");
                    adminManageUsers(loggedInUser);
                    continue;
                case 5:
                    break;
                default:
                    System.out.println("Please type in an integer from 1-5");
            }
            break;
        }
    }


    public static void adminManageTables(User loggedInUser) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;

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
            break;
        }
    }

    public static void adminManageReservations(User loggedInUser) throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;

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
        break;
        }
    }

    public static void adminManageDrinks(User loggedInUser) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;
            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Add Drink\n" +
                        "2. Edit Drink\n" +
                        "3. Delete Drink \n" +
                        "4. BACK \n");
                option = userValidation(1, 4);
            }
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
            break;
        }
    }

    public static void adminManageUsers(User loggedInUser) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int option = 0;
            while (option == 0) {
                System.out.println("What do you want to do? \n" +
                        "1. Add New User\n" +
                        "2. Change Name\n" +
                        "3. Change Info \n" +
                        "4. Delete User \n" +
                        "5. Get User \n" +
                        "6. BACK \n");
                option = userValidation(1, 6);
            }
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
            break;
        }
    }
}

