package agh.ics.oop;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    public int grassFields;
    public Map<Vector2d,Grass> grasses;

    public GrassField(int grassFields) {
        super(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int fieldBorder = (int) (10 * Math.sqrt(grassFields));
        this.grassFields = grassFields;
        grasses = new HashMap<>();
        Random rn = new Random();
        int i = 0;
        while (i < grassFields) {
            Vector2d newGrassPosition = new Vector2d(rn.nextInt(fieldBorder), rn.nextInt(fieldBorder));
            Grass newGrassField = new Grass(newGrassPosition);
            if (!grasses.containsKey(newGrassPosition)) {
                grasses.put(newGrassPosition, newGrassField);
                i++;
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (animals.containsKey(position)) {
            return true;
        }
        if (grasses.containsKey(position)) {
            return true;
        }
        return false;
    }
    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        if (grasses.containsKey(position)) {
            return grasses.get(position);
        }
        return null;
    }
    @Override
    public Vector2d[] getMapBounds() {
        Vector2d leftLowerBound = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d rightUpperBound = new Vector2d(0, 0);
        for (Animal animal: animals.values()) {
            leftLowerBound = leftLowerBound.lowerLeft(animal.getPosition());
            rightUpperBound = rightUpperBound.upperRight(animal.getPosition());
        }
        for (Grass grass: grasses.values()) {
            leftLowerBound = leftLowerBound.lowerLeft(grass.getPosition());
            rightUpperBound = rightUpperBound.upperRight(grass.getPosition());
        }
        return new Vector2d[]{leftLowerBound, rightUpperBound};
    }
}
