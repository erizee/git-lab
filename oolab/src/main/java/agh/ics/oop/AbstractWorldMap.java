package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected int width;
    protected int height;
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    public ArrayList<Animal> animalsList = new ArrayList<>();
    protected MapBoundary mapBoundary;

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.mapBoundary = new MapBoundary(this);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }

    public String toString() {
        Vector2d[] bounds = getMapBounds();
        return new MapVisualizer(this).draw(bounds[0], bounds[1]);
    }

    public Vector2d[] getMapBounds() {
        return mapBoundary.getMapBounds();
    }

    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height));
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            if (!isOccupied(animal.getPosition()) || objectAt(animal.getPosition()) instanceof Grass) {
                animals.put(animal.getPosition(), animal);
                animalsList.add(animal);
                mapBoundary.addObject(animal.getPosition());
                return true;
            }
            else {
                throw new IllegalArgumentException("field "+animal.getPosition().toString()+" is occupied");
            }
        }
        throw new IllegalArgumentException(animal.getPosition().toString() + " is not valid field");
    }

    public abstract boolean isOccupied(Vector2d position);
    public abstract Object objectAt(Vector2d position);


}
