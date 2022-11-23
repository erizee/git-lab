package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import agh.ics.oop.*;
import javafx.scene.layout.ColumnConstraints;


public class App extends Application{
    GridPane grid = new GridPane();
    Scene scene;
    private GrassField map;
    private Vector2d bottomLeft;
    private Vector2d upperRight;

    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionParser().parse(args);
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        this.map = map;
        this.bottomLeft = map.getMapBounds()[0];
        this.upperRight = map.getMapBounds()[1];
        this.scene = new Scene(grid, 20*(upperRight.x- bottomLeft.x+2)+2, 20*(upperRight.y- bottomLeft.y+2));
    }

    @Override
    public void start(Stage primaryStage) {
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(20));
        grid.add(new Label("y/x"), 0, 0);

        for (int i = 0; i <= upperRight.x- bottomLeft.x; i++) {
            Label axisLabel = new Label(Integer.toString(i+bottomLeft.x));
            grid.getColumnConstraints().add(new ColumnConstraints(20));
            grid.add(axisLabel, i+1, 0);
            GridPane.setHalignment(axisLabel, HPos.CENTER);
        }

        for (int i = upperRight.y- bottomLeft.y; i >=0 ; i--) {
            Label axisLabel = new Label(Integer.toString(upperRight.y-i));
            grid.getRowConstraints().add(new RowConstraints(20));
            grid.add(axisLabel, 0, i+1);
            GridPane.setHalignment(axisLabel, HPos.CENTER);
        }

        for (Animal animal: map.animalsList) {
            Label element = new Label(animal.toString());
            grid.add(element, animal.getPosition().x-1, upperRight.y-animal.getPosition().y+bottomLeft.y);
            GridPane.setHalignment(element, HPos.CENTER);
        }
        for (Grass grass: map.grassesList) {
            if (!(map.objectAt(grass.getPosition()) instanceof Animal)) {
                Label element = new Label(grass.toString());
                grid.add(element, grass.getPosition().x-1, upperRight.y-grass.getPosition().y+bottomLeft.y);
                GridPane.setHalignment(element, HPos.CENTER);
            }
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
