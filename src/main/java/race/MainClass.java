package race;

import race.stages.Road;
import race.stages.Tunnel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static Car firstPlace;
    public static CountDownLatch cd1;
    public static CountDownLatch cd2;
    public static CountDownLatch cd3;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("IMPORTANT ANNOUNCEMENT >>> Preparation!!!");
        Semaphore semaphore = new Semaphore(2, true);
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        cd1 = new CountDownLatch(CARS_COUNT);
        cd2 = new CountDownLatch(1);
        cd3 = new CountDownLatch(CARS_COUNT);

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        cd1.await();
        cd2.countDown();
        System.out.println("IMPORTANT ANNOUNCEMENT >>> The race has begun!!!");
        cd3.await();
        System.out.println("Winner: " + firstPlace.getName());
        System.out.println("IMPORTANT ANNOUNCEMENT >>> The race is over!!!");

    }
}
