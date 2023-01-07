package race.stages;

import race.Car;

/**
 * We organize races:
 * All participants must start at the same time,
 * despite the fact that each of them takes a different time to prepare.
 * More than half of the participants cannot enter the tunnel at the same time (conditional).
 * It all needs to be synchronized.
 * Only after everyone has completed the race should an end announcement be issued.
 */
public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Road " + length + " meters";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " is started the stage: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " is finished stage: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}