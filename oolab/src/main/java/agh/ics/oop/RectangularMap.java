package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    public int width;
    public int height;
    public ArrayList<Animal> animals;
    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<Animal>();
    }


    public String toString() {
        return (new MapVisualizer(this)).draw(new Vector2d(0, 0), new Vector2d(width, height));
    }


    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height));
    }


    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }


    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) {
                return true;
            }
        }
        return false;
    }


    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (position.equals(animal.getPosition())) {
                return animal;
            }
        }
        return null;
    }
}
