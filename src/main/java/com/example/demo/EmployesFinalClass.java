package com.example.demo;

import java.util.*;

public final class EmployesFinalClass {
    private final List<String> skills;
    public EmployesFinalClass(List<String> skills) {
//        this.skills = new ArrayList<>(skills);

        this.skills=List.copyOf(skills);
    }
    public List<String> getSkills() {
        return skills;
    }

    public static void main(String[] args) {
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        EmployesFinalClass e = new EmployesFinalClass(skills);
        skills.add("Spring");
//        e.getSkills().add("new");
        System.out.println(e.getSkills().toString());
        Integer i=100;
        Integer j=300;
        System.out.println(i==j);
        Integer k=Integer.valueOf(300);
        System.out.println(j==k);System.out.println(j.equals(k));
        HashSet<String> s=new HashSet();
        s.add("String");
        s.add("new");
        HashMap map= new HashMap();
        Hashtable hashtable= new Hashtable();
        LinkedHashMap lmap= new LinkedHashMap();
        for (String s4: s){
            System.out.println(s4);
        }
    }
}


