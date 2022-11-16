package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{

    public MoveDirection[] directions;
    public AbstractWorldMap map;

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        for (Vector2d position: positions) {
            Animal animal = new Animal(map, position);
            map.place(animal);
            animal.addObserver(map);
        }
    }

    public void run() {
        int animalNumber = map.animalsList.size();
//        System.out.println("START");
//        System.out.println(map.toString());

        for (int i=0; i<directions.length; i++) {
//            System.out.printf("STEP %d of animal %d\n", i, i%animalNumber);
//            System.out.println(directions[i]);
            Animal toMoveAnimal = map.animalsList.get(i % animalNumber);
            switch (directions[i]) {
                case FORWARD -> {
                    Vector2d newPosition1 = toMoveAnimal.getPosition().add(toMoveAnimal.getOrient().toUnitVector());
                    if (map.canMoveTo(newPosition1) && !map.isOccupied(newPosition1)) {
                        toMoveAnimal.move(directions[i]);
                    }
                }
                case BACKWARD -> {
                    Vector2d newPosition2 = toMoveAnimal.getPosition().subtract(toMoveAnimal.getOrient().toUnitVector());
                    if (map.canMoveTo(newPosition2) && !map.isOccupied(newPosition2)) {
                        toMoveAnimal.move(directions[i]);
                    }
                }
                default -> toMoveAnimal.move(directions[i]);
            }
//            System.out.println(map.toString());
        }
        System.out.println(map.toString());
    }
}
