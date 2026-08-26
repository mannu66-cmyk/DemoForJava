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
        String s1 = "bsbbccccnsjbdn";
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
                .max(Map.Entry.<Character, Long>comparingByValue()
                        .thenComparing((e1, e2) -> e2.getKey().compareTo(e1.getKey())));
//
        System.out.println(news.get().getKey());

        Employee e = new Employee("chitiya", 20000, "IT");
        Employee e1 = new Employee("bada chitiya", 30000, "CS");
        Employee e2 = new Employee("chota chitiya", 40000, "IT");
        Employee e3 = new Employee("random chitiya", 60000, "CS");
        Employee e4 = new Employee("kala chitiya", 70000, "IT");
        Employee e5 = new Employee("andha chitiya", 20000, "CS");
        Employee e6 = new Employee("behra chitiya", 30000, "IT");
        Employee e7 = new Employee("nata chitiya", 40000, "CS");
        Employee e8 = new Employee("lamba chitiya", 60023, "IT");
        Employee e9 = new Employee("gora chitiya", 70001, "CS");
        List<Employee> list = List.of(e, e1, e2, e3, e4, e5, e6, e7, e8, e9);
        Map<String, Integer> nt=list.stream()
                .filter(n->n.getSal()>=list.stream().map(Employee::getSal).collect(Collectors.averagingDouble(x->x))).
                collect(Collectors.groupingBy(x->x.getName(),Collectors.summingInt(Employee::getSal)));
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

    }
}

