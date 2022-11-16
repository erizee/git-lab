package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        super(width, height);
    }

    @Override
    public String toString() {
        return (new MapVisualizer(this)).draw(new Vector2d(0, 0), new Vector2d(width, height));
    }

    @Override
    public Vector2d[] getMapBounds() {
        return new Vector2d[]{new Vector2d(0, 0), new Vector2d(width, height)};
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals.values()) {
            if (position.equals(animal.getPosition())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        return null;
    }
}