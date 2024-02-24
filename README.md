# README: Labyrinth Problem Solution

## Solution Overview 

This program simulates a scenario where a group of guests visit a labyrinth one by one. Inside, they take turns eating and replacing a cupcake. The goal is to track when all guests have visited at least once. 

In the code, each guest is represented by a separate thread. To simulate the guests visiting the labyrinth one by one, we use something called a `ReentrantLock`. 

There's also a 'leader' guest who is the first one to eat the cupcake. After that, the leader and the other guests take turns observing or consuming the cupcake based on certain rules, until all guests have visited the labyrinth at least once.

## Concepts Used

1. **`AtomicInteger`**: This is a special type of integer which lets multiple threads change its value safely. 

2. **`ReentrantLock`**: This helps us make sure that only one guest (thread) can visit the labyrinth at a time.

3. **`ExecutorService`**: This plays a role in managing and controlling the guest threads.

## Making of the Solution

First, I tried to understand the problem. Then, I decided to use different threads for each guest and a lock to ensure that only one guest visits the labyrinth at a time. 

A big part of the solution was figuring out how the 'leader' guest and the other guests would interact with the cupcake. For this, I used a special atomic integer count to keep track.

I tested the code to make sure it behaves the right way. The built-in features of the Java language made solving this problem simpler.
