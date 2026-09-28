package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class LRUCache<K, V> extends LinkedHashMap<K, V> {

private final int capacity;
public LRUCache(int capacity) {
super(capacity, 0.75f, false);
this.capacity = capacity;
}
@Override
protected boolean removeEldestEntry(
Map.Entry<K, V> eldest) {
return size() > capacity;
}
public static <K,V> Map<K,V> createSynchronisationLRU(int capacity){
    return Collections.synchronizedMap(new LRUCache<K,V>(capacity));
}

    public static void main(String[] args) throws InterruptedException {
        LRUCache<Integer, String> s= new LRUCache<>(3);
        s.put(1,"A");
        s.put(2,"B");
        s.put(3,"C");

//        System.out.println(s.get(2));
        s.get(1);
        s.put(4,"D");
        for (Map.Entry<Integer, String> s1:s.entrySet()){
//            System.out.println(s1.getKey()+" "+s1.getValue());
        }
        Deque<Integer> q= new ArrayDeque<>();
        Queue<Integer> pq= new PriorityQueue<>();
        pq.offer(50);
        pq.offer(40);
        pq.offer(10);
        Iterator<Integer> x = pq.iterator();


        List<Integer> cp= new ArrayList<>();
        cp.add(2);
        cp.add(1);
        cp.add(3);
        cp.add(6);
        cp.add(7);
        cp.add(8);
        for(Integer i:cp){
            if(i==6)cp.add(9);
            System.out.println(i);
        }
        for(Integer i:cp){
            System.out.println(i);
        }


    }
}