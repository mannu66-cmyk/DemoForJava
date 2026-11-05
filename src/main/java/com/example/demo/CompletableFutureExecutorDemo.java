package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureExecutorDemo {

    /*
     * ============================================================
     * 1. CONFIGURE CUSTOM EXECUTOR
     * ============================================================
     *
     * ThreadPoolExecutor gives us control over:
     *
     * corePoolSize     -> minimum number of threads
     * maximumPoolSize  -> maximum number of threads
     * keepAliveTime    -> how long extra threads stay alive
     * workQueue        -> tasks waiting for a thread
     * threadFactory    -> controls how threads are created
     * rejectionHandler -> what happens when executor is full
     */

    private static final ExecutorService executor =
            new ThreadPoolExecutor(
                    2,                          // corePoolSize
                    5,                          // maximumPoolSize
                    60,                         // keepAliveTime
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10), // queue capacity
                    Executors.defaultThreadFactory(),
                    /*
                     * If all threads are busy AND queue is full,
                     * this policy throws RejectedExecutionException.
                     */
                    new ThreadPoolExecutor.AbortPolicy()
            );
    /*
     * ============================================================
     * 2. thenRun()
     * ============================================================
     *
     * thenRun() is used when:
     *
     * - Previous task is completed
     * - We don't need its result
     * - We simply want to execute another task
     *
     * Example:
     *
     * Task A -> Task B
     *
     * Task B does NOT receive the result of Task A.
     */

    public static void demonstrateThenRun() {

        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println(
                            "Task 1 running on: "
                                    + Thread.currentThread().getName());
                    return "Order Created";
                }, executor)
                .thenRun(() -> {
                    System.out.println(
                            "thenRun: Sending notification"
                    );
                })
                .join();
    }
    /*
     * ============================================================
     * 3. thenApply()
     * ============================================================
     *
     * Used when we want to TRANSFORM the result.
     *
     * Example:
     *
     * String -> Integer
     *
     * Task A returns "Java"
     * Task B converts it into 4
     */

    public static void demonstrateThenApply() {
        CompletableFuture<Integer> future =
                CompletableFuture
                        .supplyAsync(() -> {
                            return "Java";
                        }, executor)
                        .thenApply(value -> {
                            System.out.println(
                                    "Received value: " + value   );
                            return value.length();
                        });
        System.out.println(
                "Length = " + future.join());
    }
    /*
     * ============================================================
     * 4. thenAccept()
     * ============================================================
     *
     * Used when:
     *
     * - We receive the result
     * - We want to consume/use it
     * - We DON'T return another result
     *
     * Example:
     *
     * getUser() -> printUser()
     */
    public static void demonstrateThenAccept() {
        CompletableFuture
                .supplyAsync(() -> {
                    return "Manoj";
                }, executor)
                .thenAccept(name -> {
                    System.out.println(
                            "User name = " + name);
                })
                .join();
    }
    /*
     * ============================================================
     * 5. thenCompose()
     * ============================================================
     *
     * Used when the SECOND asynchronous operation depends
     * on the result of the FIRST operation.
     *
     * Think:
     *
     * getUser()
     *      |
     *      v
     * getOrders(user)
     *
     * The second call needs the result of the first call.
     *
     * IMPORTANT:
     *
     * thenCompose() prevents nested CompletableFuture:
     *
     * CompletableFuture<CompletableFuture<String>>
     *
     * and gives:
     *
     * CompletableFuture<String>
     */
    public static void demonstrateThenCompose() {
        CompletableFuture<String> future =
                CompletableFuture
                        .supplyAsync(() -> {
                            return "USER-101";
                        }, executor)
                        .thenCompose(userId -> {
                            // Second async operation depends
                            // on userId from the first operation.
                            return CompletableFuture.supplyAsync(() -> {
                                return "Orders for " + userId;
                            }, executor);
                        });
        System.out.println(
                future.join()
        );
    }
    /*
     * ============================================================
     * 6. thenCombine()
     * ============================================================
     *
     * Used when we have TWO independent asynchronous operations
     * and want to combine their results.
     *
     * Example:
     *
     * getUser()  --------\
     *                     -> combine()
     * getOrders() --------/
     *
     * Both operations can execute independently.
     */
    public static void demonstrateThenCombine() {
        CompletableFuture<String> userFuture =
                CompletableFuture.supplyAsync(() -> {
                    sleep(1000);
                    return "Manoj";
                }, executor);
        CompletableFuture<Integer> orderFuture =
                CompletableFuture.supplyAsync(() -> {
                    sleep(1000);
                    return 5;
                }, executor);
        CompletableFuture<String> result =
                userFuture.thenCombine(
                        orderFuture,
                        (user, orders) ->
                                user + " has " + orders + " orders"
                );
        System.out.println(
                result.join()
        );
    }
    /*
     * ============================================================
     * 7. allOf()
     * ============================================================
     *
     * Used when we want to wait until ALL CompletableFutures
     * are completed.
     *
     * Example:
     *
     * API 1
     * API 2
     * API 3
     * API 4
     *
     *      |
     *      v
     *
     * allOf()
     *
     * Continue only after ALL are completed.
     */

    public static void demonstrateAllOf() {
        CompletableFuture<String> api1 =
                CompletableFuture.supplyAsync(
                        () -> "User API",
                        executor
                );
        CompletableFuture<String> api2 =
                CompletableFuture.supplyAsync(
                        () -> "Order API",
                        executor
                );
        CompletableFuture<String> api3 =
                CompletableFuture.supplyAsync(
                        () -> "Payment API",
                        executor
                );
        CompletableFuture<Void> all =
                CompletableFuture.allOf(
                        api1,
                        api2,
                        api3
                );
        /*
         * join() waits until ALL three operations finish.
         */
        all.join();
        /*
         * Now it is safe to collect their results.
         */
        List<String> results = Arrays.asList(
                api1.join(),
                api2.join(),
                api3.join()
        );
        System.out.println(
                "All results = " + results
        );
    }
    /*
     * ============================================================
     * 8. anyOf()
     * ============================================================
     *
     * Used when we need the FIRST completed task.
     *
     * Example:
     *
     * Server A ----\
     * Server B ----- > anyOf()
     * Server C ----/
     *
     * Whichever server responds first wins.
     */

    public static void demonstrateAnyOf() {
        CompletableFuture<String> serverA =
                CompletableFuture.supplyAsync(() -> {
                    sleep(3000);
                    return "Response from Server A";
                }, executor);
        CompletableFuture<String> serverB =
                CompletableFuture.supplyAsync(() -> {
                    sleep(1000);
                    return "Response from Server B";
                }, executor);
        CompletableFuture<String> serverC =
                CompletableFuture.supplyAsync(() -> {
                    sleep(2000);
                    return "Response from Server C";
                }, executor);
        CompletableFuture<Object> fastest =
                CompletableFuture.anyOf(
                        serverA,
                        serverB,
                        serverC
                );
        /*
         * Server B should normally finish first.
         */

        System.out.println(
                "Fastest response = " + fastest.join()
        );
    }
    /*
     * ============================================================
     * 9. RUN CompletableFuture USING EXECUTOR
     * ============================================================
     *
     * By default:
     *
     * CompletableFuture.supplyAsync(task)
     *
     * uses ForkJoinPool.commonPool().
     *
     * If we want our own thread pool:
     *
     * CompletableFuture.supplyAsync(task, executor)
     *
     */

    public static void demonstrateExecutor() {
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(
                            "Running on: "
                                    + Thread.currentThread().getName()
                    );
                    return "Task completed";
                }, executor);
        System.out.println(
                future.join()
        );
    }
    /*
     * ============================================================
     * 10. EXECUTOR CONFIGURATION / POLICIES
     * ============================================================
     *
     * Example configuration:
     *
     * corePoolSize = 2
     * maxPoolSize = 5
     * queue = 10
     *
     * Meaning:
     *
     * First 2 tasks
     *       |
     *       v
     * Run immediately
     *
     * Additional tasks
     *       |
     *       v
     * Go into queue
     *
     * If queue becomes full
     *       |
     *       v
     * Executor can create additional threads
     * until maxPoolSize = 5
     *
     * If everything is full:
     *
     * rejectionPolicy decides what happens.
     */


    /*
     * ============================================================
     * REJECTION POLICIES
     * ============================================================
     *
     * 1. AbortPolicy
     *    -> throws RejectedExecutionException
     *
     * 2. CallerRunsPolicy
     *    -> submitting thread executes the task
     *
     * 3. DiscardPolicy
     *    -> silently discards the task
     *
     * 4. DiscardOldestPolicy
     *    -> removes oldest queued task
     *       and tries submitting new task
     *
     * For production systems, choose carefully based on
     * whether losing requests is acceptable.
     */


    /*
     * ============================================================
     * 11. HELPER METHOD
     * ============================================================
     */

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {
            /*
             * Restore interrupted status.
             */
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
    /*
     * ============================================================
     * MAIN METHOD
     * ============================================================
     */

    public static void main(String[] args) {

        System.out.println("\n===== thenRun =====");
        demonstrateThenRun();


        System.out.println("\n===== thenApply =====");
        demonstrateThenApply();


        System.out.println("\n===== thenAccept =====");
        demonstrateThenAccept();


        System.out.println("\n===== thenCompose =====");
        demonstrateThenCompose();


        System.out.println("\n===== thenCombine =====");
        demonstrateThenCombine();


        System.out.println("\n===== allOf =====");
        demonstrateAllOf();


        System.out.println("\n===== anyOf =====");
        demonstrateAnyOf();


        System.out.println("\n===== Custom Executor =====");
        demonstrateExecutor();

        /*
         * Always shut down your custom executor when the
         * application is finished with it.
         */
        executor.shutdown();
    }
}