package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;
import javafx.application.Platform;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable{

    public MoveDirection[] directions;
    public AbstractWorldMap map;
    public App app;
    private int moveDelay = 300;

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions, App app) {
        this.directions = directions;
        this.map = map;
        this.app = app;
        for (Vector2d position: positions) {
            Animal animal = new Animal(map, position);
            map.place(animal);
            animal.addObserver(map);
        }
    }

    @Override
    public void run() {
        int animalNumber = map.animalsList.size();

        Platform.runLater(() -> {
            app.renderMap(map);
        });

        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }

        for (int i=0; i<directions.length; i++) {
            Animal toMoveAnimal = map.animalsList.get(i % animalNumber);
            switch (directions[i]) {
                case FORWARD -> {
                    Vector2d newPosition1 = toMoveAnimal.getPosition().add(toMoveAnimal.getOrient().toUnitVector());
                    if (!(map.objectAt(newPosition1) instanceof Animal)) {
                        toMoveAnimal.move(directions[i]);
                    }
                }
                case BACKWARD -> {
                    Vector2d newPosition2 = toMoveAnimal.getPosition().subtract(toMoveAnimal.getOrient().toUnitVector());
                    if (!(map.objectAt(newPosition2) instanceof Animal)) {
                        toMoveAnimal.move(directions[i]);
                    }
                }
                default -> toMoveAnimal.move(directions[i]);
            }
            Platform.runLater(() -> {
                app.renderMap(map);
            });
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }

    }
}
