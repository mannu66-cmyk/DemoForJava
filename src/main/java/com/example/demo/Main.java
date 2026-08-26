package com.example.demo;
class Base{
    Base(){
        return;
    }
   int x=6;
}
class Parent extends Base{
   int x=5;
}
class Child extends Parent{
    int x=4;
    int getx(){
        return ((Base) this).x;
    }
}
public class Main {
    public static void main(String[] args) {
        Child n = new Child();
        System.out.println(n.getx());
        try {
            Class.forName("com.example.demo.Demopplication");

        } catch (ClassNotFoundException e) {

        }
        System.out.println(n.getx());
    }
}
