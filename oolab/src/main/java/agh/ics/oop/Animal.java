package agh.ics.oop;




public class Animal {
    private MapDirection orient = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrient() {
        return orient;
    }


    private final Vector2d borderVectorUpper = new Vector2d(4, 4);
    private final Vector2d borderVectorLower = new Vector2d(0, 0);

    public String toString() {
        return String.format("(%d,%d), %s", position.x, position.y, orient.toString());
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orient = orient.next();
            case LEFT -> orient = orient.previous();
            case FORWARD -> {
                Vector2d temp1 = new Vector2d(position.x, position.y);
                temp1 = temp1.add(orient.toUnitVector());
                if (temp1.follows(borderVectorLower) && temp1.precedes(borderVectorUpper)) {
                    position = temp1;
                }
            }
            case BACKWARD -> {
                Vector2d temp2 = new Vector2d(position.x, position.y);
                temp2 = temp2.subtract(orient.toUnitVector());
                if (temp2.follows(borderVectorLower) && temp2.precedes(borderVectorUpper)) {
                    position = temp2;
                }
            }
        }
    }

    public boolean isAt(Vector2d position2) {
        return position.equals(position2);
    }
}
