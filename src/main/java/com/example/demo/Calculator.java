package com.example.demo;

class Calculator {
 int add(int a, long b) {
  System.out.println("run 1st");
  return Math.toIntExact(a + b);
 }
 int add(long a, int b) {
  System.out.println("run 2nd.0");
  return Math.toIntExact(a + b);
 }
// int add(int a, int b, int c) {
// return a + b + c;
// }
// double add(double a, double b) {
//  System.out.println("double");
//  return a + b;
// }

 public static void main(String[] args) {
  Calculator c= new Calculator();
  c.add(1,2l);//it will give ambigious error on compile time
 }
}