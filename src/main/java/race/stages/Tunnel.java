package race.stages;

import race.Car;
import race.ConsoleColors;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore semaphore;

    public Tunnel(Semaphore semaphore) {
        this.length = 80;
        this.description = "Tunnel " + length + " meters";
        this.semaphore = semaphore;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName()
                        + " is preparing for the stage (waiting): "
                        + ConsoleColors.RED
                        + description
                        + ConsoleColors.BLACK);
                semaphore.acquire();
                System.out.println(c.getName()
                        + " started the stage: "
                        + ConsoleColors.GREEN
                        + description
                        + ConsoleColors.BLACK);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " finished the stage: "
                        + ConsoleColors.BLUE
                        + description
                        + ConsoleColors.BLACK);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
