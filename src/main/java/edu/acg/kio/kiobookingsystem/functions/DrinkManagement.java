package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;
import edu.acg.kio.kiobookingsystem.classes.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class DrinkManagement {
    public static Drink searchDrink(String searchTerm, ArrayList<Drink> array){
        Drink temp = null;
        for(Drink d: array){
            if(d.getName().equals(searchTerm)) temp = d;
        }
        return temp;
    }
    public static ArrayList<Drink> searchDrinks(String searchTerm, ArrayList<Drink> array){
        ArrayList<Drink> temp = null;
        searchTerm = searchTerm.toLowerCase(Locale.ROOT);
        if(searchTerm == null || searchTerm.equals(" ") || searchTerm.equals("")){
            return temp;
        }
        else if(searchTerm.equals("all")){
            for(Drink d: array){
                assert false;
                temp.add(d);
            }
        }
        else{
            for(Drink d: array){
                if(d.getName().equals(searchTerm)) {
                    assert false;
                    temp.add(d);
                }
            }
        }
        return temp;
    }

    public static void writeDrinksToFile(ArrayList<Drink> drinks) throws IOException {

        File file = new File("files/drinks.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Drink d : drinks){
            bw.write(d.toFile());
        }
        bw.close();
        fw.close();
        }


    public static ArrayList<Drink> readDrinksFromFile() throws FileNotFoundException {
        ArrayList<Drink> drinksArray = new ArrayList<>();
        String name;
        String price;

        Scanner input = new Scanner(new File("files/drinks.csv"));

        while (input.hasNext()) {
            String[] Drinks = input.nextLine().split(",");
            Drink drink = new Drink(Drinks[0],Double.parseDouble(Drinks[1]));
            drinksArray.add(drink);
        }

    return drinksArray;
    }
}
