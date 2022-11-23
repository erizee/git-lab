package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.out;

public class World {


    public static void main(String[] argss) {
        try {
            out.println("system wystartowal");
            Application.launch(App.class);
            out.println("system zakonczyl dzialanie");
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }
    }
}