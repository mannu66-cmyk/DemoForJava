package com.example.demo;

public class Employee {
    int sal;
    String name;
    String dep;
    Employee(String name, int sal, String dep){
        this.sal=sal;
        this.dep=dep;
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
}
