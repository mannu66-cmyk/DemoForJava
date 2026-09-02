package com.example.demo;

public class SharedFlag implements Runnable{
    int x=0;
    SharedFlag(int x){
        this.x=x;
    }
    // Correct usage: visibility is all we need here
    private  boolean keepRunning = true;

    public void stop() { keepRunning = false; }
    public void run() {

        while (keepRunning) {

        }
        System.out.println(Thread.currentThread().getName()+"stoops");
    }

    public static void main(String[] args) throws InterruptedException {
        SharedFlag s = new SharedFlag(15);

        new Thread(s,"run1").start();
        new Thread(s,"run32").start();
        for (int i = 0; i < 200; i++) {
            System.out.println();
        }
        //Thread.sleep(1000);
        s.stop();
    }
}