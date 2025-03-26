package ex04;

public abstract class Animal {
    private final String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = getAge() + 1;
    }

}

