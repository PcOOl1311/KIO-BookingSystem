package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.*;
import edu.acg.kio.kiobookingsystem.enumerators.UserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserManagement {

    public static ArrayList<User> readFromFiles() throws FileNotFoundException {
        ArrayList<User> usersArray = new ArrayList<>();
        String name;
        String password;
        String contactInfo;
        UserType userType;

        Scanner input = new Scanner(new File("files/users.csv"));

        while (input.hasNext()) {
            String[] Users = input.nextLine().split(",");
            name = Users[0];
            password = Users[1];
            contactInfo = Users[2];

            if (Objects.equals(Users[3], "GUEST")) userType = UserType.GUEST;
            else if (Objects.equals(Users[3], "CUSTOMER")) userType = UserType.CUSTOMER;
            else if (Objects.equals(Users[3], "EMPLOYEE")) userType = UserType.EMPLOYEE;
            else if (Objects.equals(Users[3], "ADMIN")) userType = UserType.ADMIN;
            else {
                userType = null;
            }
            User user = new User(name, password, contactInfo, userType);
            usersArray.add(user);
        }

        return usersArray;


    }
}
