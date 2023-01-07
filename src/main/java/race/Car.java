package race;

import static race.MainClass.*;
public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Participant #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " getting ready");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " is ready");
            cd1.countDown();
            cd2.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if(firstPlace==null){
            firstPlace = this;
            System.out.println(getName() + " - WIN");
        }
        cd3.countDown();
    }
}