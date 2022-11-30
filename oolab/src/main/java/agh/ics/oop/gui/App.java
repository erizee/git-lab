package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import agh.ics.oop.*;


public class App extends Application{
    GridPane grid = new GridPane();
    private GrassField map;
    private Vector2d bottomLeft;
    private Vector2d upperRight;
    private MapDirection orient;

    @Override
    public void start(Stage primaryStage) {
        try {
            TextField textField = new TextField();
            Button startButton = getStartButton(textField);
            Button orientButton = getOrientButton();
            HBox hBox = new HBox(this.grid, textField, startButton, orientButton);
            Scene scene = new Scene(hBox, 800, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Button getStartButton(TextField textField) {
        Button startButton = new Button("Start");
        startButton.setOnAction((action) -> {
            String text = textField.getText();
            MoveDirection[] directions = new OptionParser().parse(text.split(" "));
            Vector2d[] positions = {new Vector2d(1, 3), new Vector2d(2, 5)};
            GrassField map = new GrassField(2);
            IEngine engine = new SimulationEngine(directions, map, positions, this);
            Thread engineThread = new Thread(engine::run);
            engineThread.start();
        });
        return startButton;
    }

    public Button getOrientButton() {
        Button orientButton = new Button(orient.toString());
        orientButton.setOnAction((action) -> {
            orient = orient.next();
            orientButton.setText(orient.toString());
        });
        return orientButton;
    }

    public void renderMap(AbstractWorldMap newMap) {
        this.map = (GrassField) newMap;
        this.bottomLeft = map.getMapBounds()[0];
        this.upperRight = map.getMapBounds()[1];
        grid.setGridLinesVisible(false);
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(40));
        Label axisDesc = new Label("y/x");
        grid.add(axisDesc, 0, 0);
        GridPane.setHalignment(axisDesc, HPos.CENTER);

        for (int i = 0; i <= upperRight.x- bottomLeft.x; i++) {
            Label axisLabel = new Label(Integer.toString(i+bottomLeft.x));
            grid.getColumnConstraints().add(new ColumnConstraints(40));
            grid.add(axisLabel, i+1, 0);
            GridPane.setHalignment(axisLabel, HPos.CENTER);
        }

        for (int i = upperRight.y- bottomLeft.y; i >=0 ; i--) {
            Label axisLabel = new Label(Integer.toString(upperRight.y-i));
            grid.getRowConstraints().add(new RowConstraints(40));
            grid.add(axisLabel, 0, i+1);
            GridPane.setHalignment(axisLabel, HPos.CENTER);
        }

        for (Animal animal: map.animalsList) {
            GuiElementBox element = new GuiElementBox(animal);
            grid.add(element.getvBox(), animal.getPosition().x-bottomLeft.x+1, upperRight.y-animal.getPosition().y+1);
            GridPane.setHalignment(element.getvBox(), HPos.CENTER);
        }
        for (Grass grass: map.grassesList) {
            if (!(map.objectAt(grass.getPosition()) instanceof Animal)) {
                GuiElementBox element = new GuiElementBox(grass);
                grid.add(element.getvBox(), grass.getPosition().x-bottomLeft.x+1, upperRight.y-grass.getPosition().y+1);
                GridPane.setHalignment(element.getvBox(), HPos.CENTER);
            }
        }
    }
}
