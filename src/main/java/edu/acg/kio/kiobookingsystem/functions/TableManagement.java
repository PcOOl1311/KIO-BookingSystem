package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;
import edu.acg.kio.kiobookingsystem.classes.Table;
import edu.acg.kio.kiobookingsystem.classes.TableSlot;
import edu.acg.kio.kiobookingsystem.classes.User;
import edu.acg.kio.kiobookingsystem.enumerators.Days;
import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import static edu.acg.kio.kiobookingsystem.enumerators.TimeSlot.EARLY;
import static edu.acg.kio.kiobookingsystem.enumerators.TimeSlot.LATE;
import static edu.acg.kio.kiobookingsystem.functions.DrinkManagement.*;
import static edu.acg.kio.kiobookingsystem.functions.UserManagement.*;

public class TableManagement {

    //Search function that return 1 Table from the Array;
    public static Table searchTable(String searchTerm,ArrayList<Table> array){
        Table temp = null; //initialize
        for(Table tb: array){ //for loop, go in the array and for every table
            if(tb.getTableName().equals(searchTerm)) temp = tb; //search if the name matches the search term
        }
        return temp;
    }

    //Check if a Table slot with that specific timeSlots Day and Name already exists
    public static TableSlot searchIfTableSlotExists(TableSlot ts,ArrayList<TableSlot> array){
        TableSlot temp = null; //initialize
        for(TableSlot tableSlot: array){ //for every tableSlot in the array
            if (Objects.equals(tableSlot, ts)) { //if tableSlot matches with ts
                temp = ts; //put ts in temp
                break;
            }
            else {
                TableSlot ts1 = new TableSlot(tableSlot.getTableName(), tableSlot.getTimeSlot(), tableSlot.getDay());
                TableSlot ts2 = new TableSlot(ts.getTableName(), ts.getTimeSlot(), ts.getDay());
                if (Objects.equals(ts1, ts2)) {
                    temp = ts;
                    break;
                }
            }
        }
        return temp;
    }

        //Function to write the Arraylist from the parameter to the Files
    public static void writeTablesToFile(ArrayList<Table> tables) throws IOException {
        File file = new File("files/tables.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Table t : tables){
            bw.write(t.toFile());
        }
        bw.close();
        fw.close();
    }
        //Function to Write the Reservations to a file
    public static void writeTableSlotsToFile(ArrayList<TableSlot> tableSlots) throws IOException {
        File file = new File("files/tableSlots.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (TableSlot ts : tableSlots){
            bw.write(ts.toFile());
        }
        bw.close();
        fw.close();
    }
        //Search Function for one Reservation Search
    public static TableSlot searchTableSlot(String searchTerm,ArrayList<TableSlot> array){
        TableSlot temp = null;
        for (TableSlot tb : array) {
            if (tb.getTableName().equals(searchTerm)) temp = tb;
        }
        return temp;
    }
            //Search Function for Array List search
    public static ArrayList<TableSlot> searchTableSlots(String searchTerm,ArrayList<TableSlot> array){
        ArrayList<TableSlot> temp = null;
        searchTerm = searchTerm.toLowerCase(Locale.ROOT);
        if(searchTerm == null || searchTerm.equals(" ") || searchTerm.equals("")){
            return temp;
        }
        else if(searchTerm.equals("all")){
            for(TableSlot t : array){
                assert false;
                temp.add(t);
            }
        }
        else if(searchTerm.equals("empty")){
            for(TableSlot t : array){
                if(t ==null || t.getCustomer()==null){
                    temp.add(t);
                }
                if(t==null || t.getCustomer()==null){
                    temp.add(t);
                }
            }
        }
        else if(searchTerm.equals("full")){  // We check if the Customer Object Exists in the Table
            for(TableSlot t : array){
                if(t.getCustomer()!=null){
                    temp.add(t);
                }
                if(t.getCustomer()!=null){
                    temp.add(t);
                }
            }
        }
        else{
            for(TableSlot t: array){
                if(t.getTableName().equals(searchTerm)) {
                    assert false;
                    temp.add(t);
                }
            }
        }
        return temp;
    }
        //Reading Function from csv file to ARRAYLIST
    public static ArrayList<TableSlot> readTableSlotFromFile() throws FileNotFoundException{
        ArrayList<TableSlot> tablesSlotArray = new ArrayList<>();
        ArrayList<User> usersArray = readUsersFromFile();
        ArrayList<Drink> drinks = readDrinksFromFile();

        String tableName;
        TimeSlot timeSlot = null;
        User customer;
        Drink drink;
        int amountOfPeople;
        Days day = null;

        Scanner inputTS = new Scanner(new File("files/tableSlots.csv"));

        while ((inputTS.hasNext())){
            String[] TableSlots = inputTS.nextLine().split(",");
            tableName = TableSlots[0];
            if(Objects.equals(TableSlots[1],"EARLY")) timeSlot = EARLY;
            else if(Objects.equals(TableSlots[1],"LATE")) timeSlot = TimeSlot.LATE;
            customer = searchUser(TableSlots[2],usersArray);
            drink = searchDrink(TableSlots[3],drinks);
            amountOfPeople = Integer.parseInt(TableSlots[4]);
            if(Objects.equals(TableSlots[5],"MONDAY")) day = Days.MONDAY;
            else if(Objects.equals(TableSlots[5],"TUESDAY")) day = Days.TUESDAY;
            else if(Objects.equals(TableSlots[5],"WEDNESDAY")) day = Days.WEDNESDAY;
            else if(Objects.equals(TableSlots[5],"THURSDAY")) day = Days.THURSDAY;
            else if(Objects.equals(TableSlots[5],"FRIDAY")) day = Days.FRIDAY;
            else if(Objects.equals(TableSlots[5],"SATURDAY")) day = Days.SATURDAY;
            else if(Objects.equals(TableSlots[5],"SUNDAY")) day = Days.SUNDAY;
            else if(Objects.equals(TableSlots[5],"UNASSIGNED")) day = Days.UNASSIGNED;
            TableSlot ts = new TableSlot(tableName,timeSlot,customer,drink,amountOfPeople,day);
            tablesSlotArray.add(ts);
        }



        return tablesSlotArray;
    }
        // READING function from csv file to arraylist
    public static ArrayList<Table> readTableFromFile(String pathName) throws FileNotFoundException {
        ArrayList<Table> tablesArray = new ArrayList<>();
        ArrayList<TableSlot> tableSlots = readTableSlotFromFile();

        String tableName;
        TableType type;
        int minDrinks;
        int maxPeople;
        TableSlot tableSlot1;
        TableSlot tableSlot2;

        Scanner inputT = new Scanner(new File(pathName));

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

            tableSlot1 = searchTableSlot("emptyE",tableSlots);
            tableSlot2 = searchTableSlot("emptyL",tableSlots);


            Table table = new Table(tableName,type,minDrinks,maxPeople,tableSlot1,tableSlot2);
            //System.out.println(table);
            tablesArray.add(table);
        }

        return tablesArray;
    }

        //Replace Function for all the workday and availability csv files
    public static void copyFile() throws IOException {
        String[] days ={"M","T","W","R","F","ST","SU"};

        File directory =new File("files/tablesPerWeek"); //Deletes all the directory files
        FileUtils.cleanDirectory(directory);


        File originalFile = new File("files/tables.csv"); //Original copy of the tables

        for(String d:days){  // For everyday copy the tables file and format the everyday of the week
            String pathName = "files/tablesPerWeek/tables" + d + ".csv";
            File newFile = new File(pathName);
            try{
                Files.copy(originalFile.toPath(),newFile.toPath());
            }
            catch (Exception e){
                System.out.println("Error");

            }

        }

    }

    //Function that allocates the Reservations Made to each day of the week and replaces the ones that have a pending day
    public static void insertReservations(ArrayList<TableSlot> tableSlots,ArrayList<Table> tableDay,Days day) {
        Table temp = null;
        for(Table tb:tableDay){
            tb.getTableSlot1().setDay(day);
            //System.out.println(tb);
            tb.getTableSlot2().setDay(day);
        }
        for (TableSlot ts : tableSlots) {
            if (ts.getDay().equals(day)) {
                temp = searchTable(ts.getTableName(), tableDay);
                //System.out.println(temp.getTableSlot1().getTimeSlot());
                if (ts.getTimeSlot().equals(EARLY)) {
                    temp.setTableSlot1(ts);
                } else if (ts.getTimeSlot().equals(LATE)) {
                    temp.setTableSlot2(ts);
                }
            }
        }
    }
}
