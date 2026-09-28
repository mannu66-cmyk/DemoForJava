package com.example.demo;

import java.util.Hashtable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Employee {
    int id;
    int sal;
    String name;
    String dep;
    Employee(int id,String name, int sal, String dep){
        this.id= id;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee employee)) return false;
        return id == employee.id && sal == employee.sal && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sal, name);
    }
}
