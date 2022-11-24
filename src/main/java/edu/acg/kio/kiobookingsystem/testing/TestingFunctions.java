package edu.acg.kio.kiobookingsystem.testing;

import edu.acg.kio.kiobookingsystem.classes.*;
import edu.acg.kio.kiobookingsystem.enumerators.TableType;
import edu.acg.kio.kiobookingsystem.enumerators.TimeSlot;
import edu.acg.kio.kiobookingsystem.enumerators.UserType;


public class TestingFunctions {
    public static void main(String[] args) {


        //TESTING ALL CLASSES
        Drink d1 = new Drink("vodka",90.2 );
        System.out.println(d1);

        User u1 = new User("babis","iambabis2000","6923573621", UserType.CUSTOMER);
        System.out.println(u1);

        TableSlot ts1 = new TableSlot(TimeSlot.LATE,u1,d1,5);
        System.out.println(ts1);

        Table t1 = new Table("v1",TableType.VIP,3,8,ts1,ts1);
        System.out.println(t1);

        Reservation r1= new Reservation(u1,t1,TimeSlot.LATE,"Monday");
        System.out.println(r1);
    }


}
