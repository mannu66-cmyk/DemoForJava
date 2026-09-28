package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaQuestion {
    private static final Logger log = LoggerFactory.getLogger(LambdaQuestion.class);

    public static void main(String[] args) {
//        int[] nums= {10,20,30,50,40,32,32};
//        List<Integer> s=Arrays.stream(nums).boxed().collect(Collectors.toList());
        ;
        String s1 = "ccccbbbbnsjbdn";
        Set<Character> set = new HashSet<>();
        Optional<Map.Entry<Character, Long>> news = s1.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(x -> x, () -> new HashMap<>(), Collectors.counting()))
                .entrySet().stream()
//                .reduce((a,b)->{
//                    if(a.getValue()==b.getValue()){
//                        if( a.getKey().compareTo(b.getKey()))
//                            else;
//                    }
//                    if(a.getValue()>b.getValue())return a;
//                    else return b;});
                //it gives b as it is in asceding order
                .max(Map.Entry.<Character, Long>comparingByValue()
                        .thenComparing((e1, e2) -> e2.getKey().compareTo(e1.getKey())));
//
        System.out.println(news.get().getKey());

        Employee e = new Employee(10,"chitiya", 20000, "IT");
        Employee e1 = new Employee(10,"chitiya", 60000, "IT");
        Employee e2 = new Employee(90,"chota chitiya", 40000, "IT");
        Employee e3 = new Employee(80,"random chitiya", 60000, "CS");
        Employee e4 = new Employee(70,"kala chitiya", 70000, "IT");
        Employee e5 = new Employee(60,"andha chitiya", 20000, "CS");
        Employee e6 = new Employee(10,"behra chitiya", 30000, "IT");
        Employee e7 = new Employee(20,"nata chitiya", 40000, "CS");
        Employee e8 = new Employee(40,"lamba chitiya", 60023, "IT");
        Employee e9 = new Employee(50,"gora chitiya", 70001, "CS");
        List<Employee> list = List.of(e, e1, e2, e3, e4, e5, e6, e7, e8, e9);
        Map<String, Integer> nt=list.stream()
                .filter(n->n.getSal()>=list.stream().map(Employee::getSal).collect(Collectors.averagingDouble(x->x))).
                collect(Collectors.groupingBy(Employee::getName,Collectors.summingInt(Employee::getSal)));
        log.info("double {}",nt);
        Map<String, String> result = list.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDep,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                deptList -> deptList.stream()
                                        .sorted(Comparator.comparingDouble(Employee::getSal).reversed())
                                        .skip(3)
                                        .map(Employee::getName)
                                        .findFirst()
                                        .orElse("N/A")
                        )
                ));


        System.out.println(result);
        List<String> s4 = Arrays.asList("rahul", "shumbham", "pankaj", "rahul", "shubham", "kirti");
        System.out.println(s4.stream().collect(Collectors.toMap(x -> x, y -> 1, Integer::sum)
        ));

        List<Integer> list2 = List.of(123, 456, 789);
        //flatmaptoint takes intstream that why char can return intsream
        log.info(String.valueOf(list2.stream().flatMapToInt(n -> String.valueOf(n).chars()).map(c -> c - '0').sum()));
        //flatmap takes stream only that's why maptoobj has to return stream and maptoint
        // again change it to intstream so that we can use sum() otherwise reduce is the only option
        log.info(String.valueOf(list2.stream().flatMap(n -> String.valueOf(n).chars().mapToObj(c -> c - '0'))
                .mapToInt(Integer::intValue).sum()));
//                .reduce(Integer::sum)));
//reverse a string using lambda
        String s3 = "java is a powerfull tool";
        String[] d = s3.split("");
        System.out.println(s3.chars().mapToObj(s -> String.valueOf((char) s)).reduce("", (a, b) -> b + a));
        System.out.println(IntStream.iterate(s3.length() - 1, i -> i >= 0, i -> i - 1)
                .mapToObj(s3::charAt) // Extract character at index
                .map(String::valueOf)       // Convert char to String
                .reduce("", (a, b) -> a + b));

        List<Integer> list11 = List.of(11,21,31,61,51);
        List<Integer> list22 = List.of(21,71,51);
        list11.stream().filter(n->list22.contains(n)).forEach(System.out::println);
        Map<Boolean, List<Integer>> l = list11.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println(l.get(true));

        Set en= new HashSet();
        //it will treat every object as unique if your class doesn't override the equals and hashcode method
        // if your based on the id the hashcode is same and equal giving true then it will treat them as similar object
        // if equals return false it will treat them unique
        en.addAll(Arrays.asList(e,e1,e2,e3,e4,e5,e6,e7,e8,e9));
        System.out.println("Ssize"+" "+en.size());
        Map<Employee, String> s= new HashMap<>();
        s.put(e,"developer");
        s.put(e1,"new dev");
        System.out.println(s.get(e4));
        for( Map.Entry<Employee, String> sr: s.entrySet()){
            System.out.println(sr.getKey().getName()+" "+ sr.getKey().id+" "+ sr.getValue() );
        }
    }
}

