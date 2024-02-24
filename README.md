# README: 

## Labyrinth Problem Solution Overview 

This program simulates a scenario where a group of guests visit a labyrinth one by one. Inside, they take turns eating and replacing a cupcake. The goal is to track when all guests have visited at least once. 

In the code, each guest is represented by a separate thread. To simulate the guests visiting the labyrinth one by one, we use something called a `ReentrantLock`. 

There's also a 'leader' guest who is the first one to eat the cupcake. After that, the leader and the other guests take turns observing or consuming the cupcake based on certain rules, until all guests have visited the labyrinth at least once.

## Concepts Used

1. **`AtomicInteger`**: This is a special type of integer which lets multiple threads change its value safely. 

2. **`ReentrantLock`**: This helps us make sure that only one guest (thread) can visit the labyrinth at a time.

3. **`ExecutorService`**: This plays a role in managing and controlling the guest threads.

## Making of the Solution

First, I tried to understand the problem. Then, I decided to use different threads for each guest and a lock to ensure that only one guest visits the labyrinth at a time. 

## Showroom Problem Solution Overview 
This program manages the viewing of a vase in a showroom by multiple party guests. Because the vase is delicate, only one guest can view it at a time. To accommodate this, we have guests line up and wait their turn. 

In this code, each guest is represented as a separate thread. Just as guests wait their turn to view the vase at a party, threads in this program wait their turn to access the 'viewing room'. 

Each guest (thread) views the vase a number of times. When the total views reach the maximum (1000 in this case), the viewing ends and the party is over. 

## Concepts Used

1. **`Semaphore`**: This is used to permit only one guest thread at a time to access the viewing room.
2. **`AtomicInteger` and `CountDownLatch`**: These are used to track the total number of viewings and to signal when the maximum number of viewings have been reached.
3. **`Thread.sleep()`**: This is used to simulate the time each guest spends viewing the vase.

## Making of the Solution

Understanding the problem was the first task. We needed to create a situation where threads (guests) could only access a resource (the viewing room) one by one, so I decided to use a `Semaphore`. 

I used an `AtomicInteger` to keep track of viewings, and a `CountDownLatch` to signal when the viewing limit was reached. 

Once the maximum number of viewings was reached, the program ends and all guests’ threads finish. I tested the solution to ensure it works as expected. 

This problem was about simulating a real-world scenario using threads and synchronization in Java, and demonstrates how these concepts can be applied to manage shared resources effectively.


A big part of the solution was figuring out how the 'leader' guest and the other guests would interact with the cupcake. For this, I used a special atomic integer count to keep track.

I tested the code to make sure it behaves the right way. The built-in features of the Java language made solving this problem simpler.
