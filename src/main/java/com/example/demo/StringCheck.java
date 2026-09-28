package com.example.demo;

public class StringCheck {

    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("java");
        StringBuilder sb2 = new StringBuilder("java");
        System.out.println(sb1.equals(sb2));// it gives you false since JVM didn't override
        // equals method in Stringbuilder class and to check it change it to String class
        String s2 = "java";
        String s1 = new String("java");
        // you can use string contentequals method to check the string and stringbuilder calss
        System.out.println(s2.contentEquals(sb1));
    }

}