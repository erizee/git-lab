package agh.ics.oop;

import java.util.ArrayList;

import java.util.Random;

public class GrassField extends AbstractWorldMap {

    public int grassFields;
    public ArrayList<Grass> grasses;

    public GrassField(int grassFields) {
        super(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int fieldBorder = (int) (10 * Math.sqrt(grassFields));
        this.grassFields = grassFields;
        grasses = new ArrayList<>();
        Random rn = new Random();
        int i = 0;
        while (i < grassFields) {
            Vector2d newGrassPosition = new Vector2d(rn.nextInt(fieldBorder), rn.nextInt(fieldBorder));
            Grass newGrassField = new Grass(newGrassPosition);
            boolean flag = true;
            for (Grass grass: grasses) {
                if (newGrassPosition.equals(grass.getPosition())) {
                    flag = false;
                }
            }
            if (flag) {
                grasses.add(newGrassField);
                i++;
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) {
                return true;
            }
        }
        for (Grass grass : grasses) {
            if (position.equals(grass.getPosition())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (position.equals(animal.getPosition())) {
                return animal;
            }
        }
        for (Grass grass : grasses) {
            if (position.equals(grass.getPosition())) {
                return grass;
            }
        }
        return null;
    }
    @Override
    public Vector2d[] getMapBounds() {
        Vector2d leftLowerBound = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d rightUpperBound = new Vector2d(0, 0);
        for (Animal animal: animals) {
            leftLowerBound = leftLowerBound.lowerLeft(animal.getPosition());
            rightUpperBound = rightUpperBound.upperRight(animal.getPosition());
        }
        for (Grass grass: grasses) {
            leftLowerBound = leftLowerBound.lowerLeft(grass.getPosition());
            rightUpperBound = rightUpperBound.upperRight(grass.getPosition());
        }
        return new Vector2d[]{leftLowerBound, rightUpperBound};
    }
}
