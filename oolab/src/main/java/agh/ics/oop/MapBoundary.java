package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    public AbstractWorldMap map;
    public MapBoundary(AbstractWorldMap map) {
        this.map = map;
    }
    private SortedSet<Vector2d> setX = new TreeSet<>(Comparator.<Vector2d>comparingInt(p -> p.x).thenComparingInt(p -> p.y).thenComparing(p -> map.objectAt(p) instanceof Animal));
    private SortedSet<Vector2d> setY = new TreeSet<>(Comparator.<Vector2d>comparingInt(p -> p.y).thenComparingInt(p -> p.x).thenComparing(p -> map.objectAt(p) instanceof Animal));

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeObject(oldPosition);
        addObject(newPosition);

    }

    public void addObject(Vector2d position) {
        setX.add(position);
        setY.add(position);
    }

    public void removeObject(Vector2d position) {
        setX.remove(position);
        setY.remove(position);
    }

    public Vector2d[] getMapBounds() {
        return new Vector2d[]{new Vector2d(setX.first().x, setY.first().y), new Vector2d(setX.last().x, setY.last().y)};
    }


}
