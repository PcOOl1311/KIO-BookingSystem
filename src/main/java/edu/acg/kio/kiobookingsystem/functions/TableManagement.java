package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;
import edu.acg.kio.kiobookingsystem.classes.Table;
import edu.acg.kio.kiobookingsystem.classes.TableSlot;
import edu.acg.kio.kiobookingsystem.classes.User;
import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static edu.acg.kio.kiobookingsystem.functions.DrinkManagement.*;
import static edu.acg.kio.kiobookingsystem.functions.UserManagement.*;

public class TableManagement {

    public static Table searchTable(String searchTerm,ArrayList<Table> array){
        Table temp = null;
        for(Table tb: array){
            if(tb.getTableName().equals(searchTerm)) temp = tb;
        }
        return temp;
    }
    public static ArrayList<Table> searchTables(String searchTerm,ArrayList<Table> array){
        ArrayList<Table> temp = null;
        searchTerm = searchTerm.toLowerCase(Locale.ROOT);
        if(searchTerm == null || searchTerm.equals(" ") || searchTerm.equals("")){
            return temp;
        }
        else if(searchTerm.equals("all")){
            for(Table t : array){
                assert false;
                temp.add(t);
            }
        }
        else if(searchTerm.equals("empty")){
            for(Table t : array){
                if(t.getTableSlot1()==null || t.getTableSlot1().getCustomer()==null){
                    temp.add(t);
                }
                if(t.getTableSlot2()==null || t.getTableSlot2().getCustomer()==null){
                    temp.add(t);
                }
            }

        }
        else if(searchTerm.equals("full")){  // We check if the Customer Object Exists in the Table
            for(Table t : array){
                if(t.getTableSlot1().getCustomer()!=null){
                    temp.add(t);
                }
                if(t.getTableSlot2().getCustomer()!=null){
                    temp.add(t);
                }
            }

        }
        else{
            for(Table t: array){
                if(t.getTableName().equals(searchTerm)) {
                    assert false;
                    temp.add(t);
                }
            }
        }
        return temp;

    }

    public static ArrayList<TableSlot> readTableSlotFromFile() throws FileNotFoundException{
        ArrayList<TableSlot> tablesSlotArray = new ArrayList<>();
        ArrayList<User> usersArray = readUsersFromFile();
        ArrayList<Drink> drinks = readDrinksFromFile();

        String tableName;
        TimeSlot timeSlot = null;
        User customer;
        Drink drink;
        int amountOfPeople;

        Scanner inputTS = new Scanner(new File("tables.csv"));

        while ((inputTS.hasNext())){
            String[] TableSlots = inputTS.nextLine().split(",");
            tableName = TableSlots[0];
            if(Objects.equals(TableSlots[1],"EARLY")) timeSlot = TimeSlot.EARLY;
            else if(Objects.equals(TableSlots[1],"LATE")) timeSlot = TimeSlot.LATE;
            customer = searchUser(TableSlots[2],usersArray);
            drink = searchDrink(TableSlots[3],drinks);
            amountOfPeople = Integer.parseInt(TableSlots[4]);
            TableSlot ts = new TableSlot(tableName,timeSlot,customer,drink,amountOfPeople);
            tablesSlotArray.add(ts);
        }



        return tablesSlotArray;
    }

    public static ArrayList<Table> readTableFromFile() throws FileNotFoundException {
        ArrayList<Table> tablesArray = new ArrayList<>();
        String tableName;
        TableType type;
        int minDrinks;
        int maxPeople;
        TableSlot tableSlot1;
        TableSlot tableSlot2;

        Scanner inputT = new Scanner(new File("tables.csv"));

        while (inputT.hasNext()) {
            String[] Tables = inputT.nextLine().split(",");

            tableName = Tables[0];
            if(Objects.equals(Tables[1], "NORMAL")){
                type = TableType.NORMAL;
            }else if (Objects.equals(Tables[1], "VIP")){
                type = TableType.VIP;
            }else if (Objects.equals(Tables[1], "BACKSTAGE")){
                type = TableType.BACKSTAGE;
            }
            else type = TableType.UNASSIGNED;
            minDrinks = Integer.parseInt(Tables[2]);
            maxPeople = Integer.parseInt(Tables[3]);

            if (Tables[4] == null) // IF TABLE SLOT IS EMPTY THEN IT IS NULL
            {
                tableSlot1 = new TableSlot(tableName, TimeSlot.EARLY);
            }
            else {
                tableSlot1 = null;
                // TODO tableSlot1 = searchTableSlot(TableName,Tables[4])
            }
            if (Tables[5] == null) // IF TABLE SLOT IS EMPTY THEN IT IS NULL
            {
                tableSlot2 = new TableSlot(tableName, TimeSlot.LATE);
            }
            else {
                tableSlot2 = null;
                //TODO tableSlot2 = searchTableSlot(TableName,Tables[4])
            }
            Table table = new Table(tableName,type,minDrinks,maxPeople,tableSlot1,tableSlot2);
            tablesArray.add(table);
        }

        return tablesArray;
    }




}
