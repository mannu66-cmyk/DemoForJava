package com.example.demo;

interface A {
 default void show() {
 System.out.println("A");
 }
}
interface B {
 default void show() {
  System.out.println("B");
 }
}

public class DefaultAmbiguityInterface implements A,B{
 public void show(){
//  B.super.show();
  System.out.println("C");
 }

 public static void main(String[] args) {
  DefaultAmbiguityInterface c= new DefaultAmbiguityInterface();
  c.show();
 }
}
