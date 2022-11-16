package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected int width;
    protected int height;
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected ArrayList<Animal> animalsList = new ArrayList<>();

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.remove(oldPosition);
        animals.put(newPosition, animal);
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
            animals.put(animal.getPosition(), animal);
            animalsList.add(animal);
            return true;
        }
        return false;
    }

    public abstract boolean isOccupied(Vector2d position);
    public abstract Object objectAt(Vector2d position);


}
