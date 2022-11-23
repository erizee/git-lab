package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {

    public int grassFields;
    public Map<Vector2d,Grass> grasses;
    public List<Grass> grassesList;

    public GrassField(int grassFields) {
        super(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int fieldBorder = (int) (10 * Math.sqrt(grassFields));
        this.grassFields = grassFields;
        grasses = new HashMap<>();
        grassesList = new ArrayList<>();
        Random rn = new Random();
        int i = 0;
        while (i < grassFields) {
            Vector2d newGrassPosition = new Vector2d(rn.nextInt(fieldBorder), rn.nextInt(fieldBorder));
            Grass newGrassField = new Grass(newGrassPosition);
            if (!grasses.containsKey(newGrassPosition)) {
                grasses.put(newGrassPosition, newGrassField);
                grassesList.add(newGrassField);
                mapBoundary.addObject(newGrassPosition);
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

}
