package com.example.demo;

import java.util.UUID;
//In Java, java.lang.ThreadLocal provides thread-local variables, which means each thread that accesses
// the variable has its own, completely isolated and independent copy of that variable.
// It is an alternative approach to achieving thread safety without using expensive synchronization
// mechanisms (like synchronized blocks or Locks)
// ThreadLocal vs. ScopedValues (Modern Java Alternative)
//Starting in modern versions of Java (introduced as preview features in JDK 21+), ScopedValue is being introduced as a modern, lighter alternative to ThreadLocal.
   //Feature	ThreadLocal	                                    ScopedValue (Modern Java)
//Mutability	Mutable (set() can be called anywhere)	        Immutable (Bound to a scope block)
//Inheritance	Heavy memory cost via InheritableThreadLocal	Freely and safely inherited by child/Virtual Threads
//Memory Risk	High risk of leaks if not manually removed   	Zero risk (Automatically bound to the scope lifecycle)
public class ThreadLocalContext {

        // Define a ThreadLocal variable with an initial value
        private static final ThreadLocal<String> transactionId =
                ThreadLocal.withInitial(() -> "NO_TRANSACTION");

        public static void main(String[] args) {
            Runnable task = () -> {
                // Each thread gets its own unique ID
                String threadName = Thread.currentThread().getName();

                System.out.println(threadName + " initial: " + transactionId.get());

                // Modify the value for the current thread
                transactionId.set(UUID.randomUUID().toString());
                System.out.println(threadName + " assigned: " + transactionId.get());

                // Always clean up after execution
                transactionId.remove();
            };
            // Launch two separate threads executing the same Runnable instance
            Thread thread1 = new Thread(task, "Thread-A");
            Thread thread2 = new Thread(task, "Thread-B");

            thread1.start();
            thread2.start();
        }
    }