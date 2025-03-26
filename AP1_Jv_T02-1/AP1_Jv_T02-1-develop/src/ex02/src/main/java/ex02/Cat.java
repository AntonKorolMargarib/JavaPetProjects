package ex02;

public class Cat extends Animal {

    public Cat(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge() +
                ", mass = " + String.format("%.2f", getWeight()) + ", feed = " +  String.format("%.2f", getFeedInfoKg());
    }

    public double getFeedInfoKg() {
        return getWeight() * 0.1;
    }
}
