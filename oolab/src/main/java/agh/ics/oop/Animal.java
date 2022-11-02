package agh.ics.oop;




public class Animal {
    private MapDirection orient;
    private Vector2d position;
    public IWorldMap map;

    public Animal(IWorldMap map) {
        orient = MapDirection.NORTH;
        position = new Vector2d(2, 2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
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
                    position = position.add(orient.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (map.canMoveTo(position.subtract(orient.toUnitVector()))) {
                    position = position.subtract(orient.toUnitVector());
                }
            }
        }
    }
}
