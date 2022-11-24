package edu.acg.kio.kiobookingsystem.classes;

public class Drink {
    private String name;
    private double price;

    // CONSTRUCTOR //
    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // GETTERS //
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // SETTERS //
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // RETURN VALUES  //
    @Override
    public String toString() {
        return "------------------------------------" + " \n\n" +
                " Name   = " + name + " \n" +
                " Price  = " + price + " \n" ;
    }

    public  String toFile(){
        return name + "," + price + "\n";
    }
}
