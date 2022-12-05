package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.*;
import edu.acg.kio.kiobookingsystem.enumerators.UserType;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserManagement {

    public static User searchUser(String searchTerm,ArrayList<User> array){
        User temp = null;
        //System.out.println(searchTerm);
        for(User u: array){ //for every user in the array
            if(u.getName().equals(searchTerm)) temp = u; //if the user name matches the search term, then put u in temp
        }
        return temp; //print the temp
    }

    public static User searchPassword(String searchTerm, ArrayList<User> array){
        User temp = null;
        //System.out.println(searchTerm);
        for(User u: array){ //for every user in array
            if(u.getPassword().equals(searchTerm)) temp = u; //if password matches, put it in temp
        }
        return temp;
    }

    public static ArrayList<User> searchUsers(String searchTerm,ArrayList<User> array){
        ArrayList<User> temp = null; //initialize array list
        if(searchTerm == null){
            return temp;
        }
        for(User u: array){ //for every user in the array
            if(u.getName().equals(searchTerm)) { //if the name matches the search term
                temp.add(u); //add it to array list
            }
        }
        return temp;
    }

    public static void writeUsersToFile(ArrayList<User> users) throws IOException {
        File file = new File("files/users.csv"); //open file
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (User u : users){ //for all users in array list
            bw.write(u.toFile());
        }
        bw.close(); //closing files
        fw.close();
    }

    public static ArrayList<User> readUsersFromFile() throws FileNotFoundException {
        ArrayList<User> usersArray = new ArrayList<>();
        String name; //initialize
        String password;
        String contactInfo;
        UserType userType;

        Scanner input = new Scanner(new File("files/users.csv")); //new scanner

        while (input.hasNext()) { //while there is another line to read
            String[] Users = input.nextLine().split(","); //split using commas and put it in array list
            name = Users[0];
            password = Users[1];
            contactInfo = Users[2];

            //what user type do we have?
            if (Objects.equals(Users[3], "GUEST")) userType = UserType.GUEST;
            else if (Objects.equals(Users[3], "CUSTOMER")) userType = UserType.CUSTOMER;
            else if (Objects.equals(Users[3], "EMPLOYEE")) userType = UserType.EMPLOYEE;
            else if (Objects.equals(Users[3], "ADMIN")) userType = UserType.ADMIN;
            else userType = null;
            User user = new User(name, password, contactInfo, userType); //create new user
            usersArray.add(user); //add new user to array list
        }

        return usersArray;


    }
}
