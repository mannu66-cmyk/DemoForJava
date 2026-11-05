package com.example.demo;

class AlternateNumberPrinterViaTwoThreads {
    private int number = 1;
    private int limit = 0;

    public synchronized void printOdd() throws InterruptedException {
        while (number <= limit) {
            while (number % 2 == 0 && number <= limit) {
                wait();
            }
            if (number <= limit) {
                System.out.println(Thread.currentThread().getName()
                        + " -> " + number);
                number++;
                notifyAll();
            }
        }
    }
    public void setLimit(int x){
     limit=x;
    }

    public synchronized void printEven() throws InterruptedException {
        while (number <= limit) {
            while (number % 2 != 0 && number <= limit) {
                wait();
            }
            if (number <= limit) {
                System.out.println(Thread.currentThread().getName()
                        + " -> " + number);
                number++;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        AlternateNumberPrinterViaTwoThreads printer =
                new AlternateNumberPrinterViaTwoThreads();
        printer.setLimit(20);
        Thread t1 = new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
            }
        }, "Odd");
        Thread t2 = new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
            }
        }, "Even");
        t1.start();
        t2.start();
    }

}
