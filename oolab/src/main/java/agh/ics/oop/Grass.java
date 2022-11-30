package agh.ics.oop;

public class Grass implements IMapElement {
    private final Vector2d position;
    Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return "*";
    }

    public String getImagePath() {
        return "src/main/resources/grass.png";

    }
}
