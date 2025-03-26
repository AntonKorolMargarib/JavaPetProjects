package ex05;
import java.util.concurrent.*;

public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge();
    }

    public double goToWalk() {
        double walkTime = getAge() * 0.25;
        try {
            TimeUnit.SECONDS.sleep((long) walkTime);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return walkTime;
    }
}