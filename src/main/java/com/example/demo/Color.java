package com.example.demo;

import java.util.Arrays;

public enum Color {
    Red("red"), Orange("orange");
    String color;
    Color(String c){
        this.color= c;
    }
    String getValue(){
        return color;
    }
    public static void main(String[] args) {

        System.out.println("run enum " + Arrays.toString(Color.values()));
    }
}
