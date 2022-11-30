package agh.ics.oop;


import java.util.ArrayList;

public class Animal implements IMapElement {
    private MapDirection orient;
    private Vector2d position;
    public IWorldMap map;
    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(AbstractWorldMap map) {
        orient = MapDirection.NORTH;
        position = new Vector2d(2, 2);
        this.map = map;
    }

    public Animal(AbstractWorldMap map, Vector2d initialPosition) {
        orient = MapDirection.NORTH;
        position = new Vector2d(initialPosition.x, initialPosition.y);
        this.map = map;
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrient() {
        return orient;
    }

    public boolean isAt(Vector2d position2) {
        return position.equals(position2);
    }

    public String toString() {
        return switch (orient) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orient = orient.next();
            case LEFT -> orient = orient.previous();
            case FORWARD -> {
                if (map.canMoveTo(position.add(orient.toUnitVector()))) {
                    Vector2d oldPos = position;
                    position = position.add(orient.toUnitVector());
                    positionChanged(oldPos);
                }
            }
            case BACKWARD -> {
                if (map.canMoveTo(position.subtract(orient.toUnitVector()))) {
                    Vector2d oldPos = position;
                    position = position.subtract(orient.toUnitVector());
                    positionChanged(oldPos);
                }
            }
        }
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, position);
        }
    }

    public String getImagePath() {
        return switch (orient) {
            case NORTH -> "src/main/resources/up.png";
            case SOUTH -> "src/main/resources/down.png";
            case EAST -> "src/main/resources/right.png";
            case WEST -> "src/main/resources/left.png";
        };
    }

}
