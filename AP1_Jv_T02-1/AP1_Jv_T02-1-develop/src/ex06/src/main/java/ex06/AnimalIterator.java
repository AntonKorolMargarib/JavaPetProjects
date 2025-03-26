package ex06;

import java.util.List;

public class AnimalIterator implements BaseIterator<Animal>{
    private List<Animal> animals;
    private int index = 0;

    public AnimalIterator(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public Animal next() {
        if(hasNext()) {
            return this.animals.get(index++);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return (index < this.animals.size());
    }

    @Override
    public void reset() {
        index = 0;
    }
}
