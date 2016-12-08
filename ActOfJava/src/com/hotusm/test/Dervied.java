package com.hotusm.test;



public class Dervied extends Base {

    private String name = "dervied";

    public Dervied() {
        tellName();
        printName();
    }
    
    public void tellName() {
        System.out.println("Dervied tell name: " + name);
    }
    
    public void printName() {
        System.out.println("Dervied print name: " + name);
    }

    public static void main(String[] args){
        
        new Dervied();    
    }
}

class Base {
    
    private String name = "base";

    public Base() {
        tellName();
        printName();
    }
    
    public void tellName() {
        System.out.println("Base tell name: " + name);
    }
    
    public void printName() {
        System.out.println("Base print name: " + name);
    }
}