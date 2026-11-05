package com.example.demo;

import java.util.concurrent.*;

public class SharedFlagThroughVolatile implements Runnable {
    int x = 0;

    SharedFlagThroughVolatile(int x) {
        this.x = x;
    }

    // Correct usage: visibility is all we need here
    private volatile boolean keepRunning = true;

    public void stop() {
        keepRunning = false;
    }

    public void run() {

        while (keepRunning) {

        }
        System.out.println(Thread.currentThread().getName() + "stoops");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SharedFlagThroughVolatile s = new SharedFlagThroughVolatile(15);

        new Thread(s, "run1").start();
        new Thread(s, "run32").start();
        for (int i = 0; i < 200; i++) {
//            System.out.println();
        }
        //Thread.sleep(1000);
        s.stop();

        String ss = getDetails().join();
        System.out.println(ss);


    }

    public static String callApi() throws InterruptedException {
        Thread.sleep(3000);
        return "sttrnig";
    }

    public static CompletableFuture<String> getDetails() {
        ExecutorService ioExecutor = Executors.newFixedThreadPool(5);


        CompletableFuture<String> customerFuture =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                return callApi();
                            } catch (InterruptedException e) {
                            }
                            return "s";
                        },
                        ioExecutor
                ).orTimeout(4, TimeUnit.SECONDS);
        CompletableFuture<String> orderFuture =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                return callApi();
                            } catch (InterruptedException e) {
                            }
                            return "s";
                        },
                        ioExecutor
                ).orTimeout(4, TimeUnit.SECONDS);
        CompletableFuture<String> paymentFuture =
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                return callApi();
                            } catch (InterruptedException e) {
                            }
                            return "s";
                        },
                        ioExecutor
                ).orTimeout(2, TimeUnit.SECONDS);
        return CompletableFuture.allOf(
                        customerFuture,
                        orderFuture,
                        paymentFuture
                )
                .thenApply(v -> {
                    String customer = customerFuture.join();
                    String order = orderFuture.join();
                    String payment = paymentFuture.join();
                    return new String(
                            customer +
                                    order +
                                    payment
                    );
                });
    }
}