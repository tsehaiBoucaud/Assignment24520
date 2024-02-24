import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class Guest extends Thread {
    static AtomicInteger totalviewings = new AtomicInteger(0);
    static final int maxviewings = 20;
    private static final Semaphore vaseviewingroom = new Semaphore(1);
    private final int id;

    public Guest(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (totalviewings.get() < maxviewings) {
                vaseviewingroom.acquire();
                if (totalviewings.incrementAndGet() <= maxviewings) {
                    System.out.println("Party Guest " + id + " is viewing the vase. For safety, the door is locked.");
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
                    System.out.println("Guest " + id + " has left the showroom, the next guest can enter!");
                } else {
                    totalviewings.decrementAndGet();
                }
                vaseviewingroom.release();

                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            };
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class parttwo {
    public static void main(String[] args) throws InterruptedException {
        int totalGuests = 10;
        Guest[] friends = new Guest[totalGuests];

        for(int i=0; i<totalGuests; i++ ){
            friends[i] = new Guest(i+1);
            friends[i].start();

            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000));
        }

        for(int i=0; i<totalGuests; i++ ){
            friends[i].join();
        }
        System.out.println("Alright! Party is over, go home.");

    }
}
