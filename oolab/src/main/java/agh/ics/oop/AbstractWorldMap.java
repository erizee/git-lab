package agh.ics.oop;

import java.util.ArrayList;

abstract class AbstractWorldMap implements IWorldMap {
    protected int width;
    protected int height;
    protected ArrayList<Animal> animals = new ArrayList<>();

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String toString() {
        Vector2d[] bounds = getMapBounds();
        return new MapVisualizer(this).draw(bounds[0], bounds[1]);
    }

    public abstract Vector2d[] getMapBounds();

    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height));
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition()) && (!isOccupied(animal.getPosition()) || objectAt(animal.getPosition()) instanceof Grass)) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public abstract boolean isOccupied(Vector2d position);
    public abstract Object objectAt(Vector2d position);


}
