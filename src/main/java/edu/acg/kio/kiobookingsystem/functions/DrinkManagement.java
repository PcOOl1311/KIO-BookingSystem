package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DrinkManagement {

    public static ArrayList<Drink> readFromFiles() throws FileNotFoundException {
        ArrayList<Drink> drinksArray = new ArrayList<>();
        String name;
        String price;

        Scanner input = new Scanner(new File("files/drinks.csv"));

        while (input.hasNext()) {
            String[] Drinks = input.nextLine().split(",");
            Drink drink = new Drink(Drinks[0],Integer.parseInt(Drinks[1]));
            drinksArray.add(drink);
        }

    return drinksArray;
    }
}
