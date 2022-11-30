package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;

public class GuiElementBox {
    public VBox vBox;
    public GuiElementBox (IMapElement mapElement) {
        try {
            Image image = new Image(new FileInputStream(mapElement.getImagePath()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            Label label;
            if (mapElement instanceof Animal) {
                label = new Label(mapElement.getPosition().toString());
            } else
                label = new Label("Grass");
            this.vBox = new VBox();
            this.vBox.getChildren().addAll(imageView, label);
            this.vBox.setAlignment(Pos.CENTER);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public VBox getvBox() {
        return vBox;
    }
}
