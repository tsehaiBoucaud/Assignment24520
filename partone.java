import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class partone {

    public static void main(String[] args) {
        final int totalGuests = 10;
        int leaderId = 0;
        ReentrantLock labyrinth = new ReentrantLock();
        ExecutorService executor = Executors.newFixedThreadPool(totalGuests);
        AtomicInteger cupcakes = new AtomicInteger(1);
        AtomicInteger uniqueGuestsThatVisited = new AtomicInteger(0);
        Future<?>[] futures = new Future<?>[totalGuests];
        for (int i = 0; i < totalGuests; i++) {
            Guest guest = new Guest(i, leaderId, labyrinth, cupcakes, uniqueGuestsThatVisited, totalGuests);
            futures[i] = executor.submit(guest);
        }
        executor.shutdown();
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class Guest implements Runnable {

    private final int id;
    private final int totalGuests;
    private final ReentrantLock labyrinth;
    private final AtomicInteger cupcakes;
    private final AtomicInteger uniqueGuestsThatVisited;
    private final boolean isLeader;
    private boolean hasVisited = false;
    private boolean hasEatenCupcake = false;

    public Guest(int id, int leaderId, ReentrantLock labyrinth, AtomicInteger cupcakes,
                 AtomicInteger uniqueGuestsThatVisited, int totalGuests) {
        this.id = id;
        
        this.isLeader = (id == leaderId);
        this.labyrinth = labyrinth;
        this.cupcakes = cupcakes;
        this.uniqueGuestsThatVisited = uniqueGuestsThatVisited;
        this.totalGuests = totalGuests;
    }

    @Override
    public void run() {
        while (true) {
            labyrinth.lock();
            try {
                if (!hasVisited && cupcakes.get() == 1) {
                    if (isLeader) {
                        cupcakes.decrementAndGet();
                        System.out.printf("Guest %d (Leader) has eaten the cupcake at his first visit%n", id);
                        hasEatenCupcake = true;
                    } else {
                        System.out.printf("Guest %d has not eaten the cupcake at his first visit%n", id);
                    }
                    hasVisited = true;
                } else if (hasVisited && cupcakes.get() == 1) {
                    if (!isLeader && !hasEatenCupcake) {
                        cupcakes.decrementAndGet();
                        System.out.printf("Guest %d has eaten the cupcake at a subsequent visit%n", id);
                        hasEatenCupcake = true;
                    }
                } else if (isLeader && cupcakes.get() == 0) {
                    cupcakes.incrementAndGet();
                    uniqueGuestsThatVisited.incrementAndGet();
                    System.out.printf("Guest %d (Leader) has noted the missing cupcake and requested another one%n", id);
                    if (uniqueGuestsThatVisited.get() == totalGuests) {
                        System.out.println("All guests have visited the labyrinth at least once");
                        return;
                    }
                }
            } finally {
                labyrinth.unlock();
            }
        }
    }
}