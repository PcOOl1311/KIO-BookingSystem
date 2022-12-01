package edu.acg.kio.kiobookingsystem.functions;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Menus {
    public static void mainMenu() {
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
        public static void loginRegisterMenu(){
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
                        if (username.toUpperCase(Locale.ROOT).equals("BACK")){
                            break;
                        }
                        System.out.println("Insert your password: ");
                        String password = input.nextLine();
                        ;}break;
                    case 2: {System.out.println("Registering, type BACK in username if you want to return\n");
                        System.out.println("Insert a username: ");
                        String username = input.nextLine();
                        while(true) {
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
                    case 3: { break;}
                    default: System.out.println("Please enter an integer from 1-3\n");
                }
            }
        }
    public static void customerMenu(){
        System.out.println("What do you want to do? \n");
        System.out.println("1. Make a reservation\n" +
                            "2. Check availability\n"+
                            "3. Check your reservations\n" +
                            "4. BACK");
    }
}
