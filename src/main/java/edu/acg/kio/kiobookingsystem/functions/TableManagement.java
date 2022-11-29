package edu.acg.kio.kiobookingsystem.functions;

import edu.acg.kio.kiobookingsystem.classes.Drink;
import edu.acg.kio.kiobookingsystem.classes.Table;
import edu.acg.kio.kiobookingsystem.classes.TableSlot;
import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TableManagement {

    public static ArrayList<Table> readFromFiles() throws FileNotFoundException {
        ArrayList<Table> tablesArray = new ArrayList<>();
        String tableName;
        TableType type;
        int minDrinks;
        int maxPeople;
        TableSlot tableSlot1;
        TableSlot tableSlot2;

        Scanner input = new Scanner(new File("tables.csv"));

        while (input.hasNext()) {
            String[] Tables = input.nextLine().split(",");

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
