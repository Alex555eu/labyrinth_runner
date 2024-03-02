package com.example.labyrinth_runner;

import com.example.labyrinth_runner.builder.IDirector;
import com.example.labyrinth_runner.builder.ILabyrinthBuilder;
import com.example.labyrinth_runner.builder.impl.Director;
import com.example.labyrinth_runner.builder.impl.RectangleLabyrinthBuilder;
import com.example.labyrinth_runner.figure.IFigure;
import com.example.labyrinth_runner.labyrinth.ILabyrinth;
import com.example.labyrinth_runner.memento.impl.MementoCareTaker;
import com.example.labyrinth_runner.player_object.IPlayerObject;
import com.example.labyrinth_runner.player_object.impl.PlayerObject;
import com.example.labyrinth_runner.point.IPoint;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameClass {

    private static GameClass instance = null;
    private Map<String, KeyCode> activeKeys;
    private ILabyrinth labyrinth;
    private IPlayerObject playerObject;
    private Pane pane;
    private MementoCareTaker mementoCareTaker;
    private IDirector director;

    public GameClass(Pane pane) {
        this.pane = pane;
        activeKeys = new HashMap<>();
        mementoCareTaker = new MementoCareTaker();
        director = new Director();
    }

    public void generateNewGame() {
        labyrinth = buildLabyrinth();
        paintLabyrinth();

        double wallLength = labyrinth.getLabyrinth().getFirst().getFirst().getValue().getWidth(); // could be any other element of labyrinth
        IPoint startLocation = labyrinth.getLabyrinth().get(1).get(1).getValue().getMiddlePoint();// location from which player will begin their game
        playerObject = new PlayerObject(new Circle(startLocation.getX(), startLocation.getY(), wallLength - (wallLength / 2)), new Pair<>(1,1));
        paintPlayerObject();
    }

    private ILabyrinth buildLabyrinth() {
        ILabyrinthBuilder labyrinthBuilder = new RectangleLabyrinthBuilder();
        director.resizableRectangleLabyrinth(labyrinthBuilder);

        return labyrinthBuilder.buildLabyrinth();
    }

    public void paintLabyrinth() {
        List<Node> paneCopy = new ArrayList<>(pane.getChildren());
        for (Node child : paneCopy) {
            if (child.getClass().equals(Rectangle.class))
                pane.getChildren().remove(child);
        }
        for(List<Pair<Boolean, IFigure>> list : labyrinth.getLabyrinth()) {
            for(Pair<Boolean,IFigure> square : list) {
                IPoint cord = square.getValue().getDrawingStartingPoint();
                double wallLength = square.getValue().getWidth();

                Rectangle rectangle = new Rectangle(cord.getX(), cord.getY(), wallLength, wallLength);

                if (square.getKey())
                    rectangle.setFill(Color.BLACK);
                else
                    rectangle.setFill(Color.WHITE);

                pane.getChildren().add(rectangle);
            }
        }
    }

    public void paintPlayerObject() {
        List<Node> paneCopy = new ArrayList<>(pane.getChildren());
        for (Node child : paneCopy) {
            if (child.getClass().equals(Circle.class))
                pane.getChildren().remove(child);
        }
        Circle object = playerObject.getMovingObject();
        object.setFill(Color.RED);
        pane.getChildren().add(object);
    }

    public MementoCareTaker getMementoCareTaker() {
        return mementoCareTaker;
    }

    public Map<String, KeyCode> getActiveKeys() {
        return activeKeys;
    }


    public ILabyrinth getLabyrinth() {
        return labyrinth;
    }

    public IDirector getDirector() {
        return director;
    }

    public IPlayerObject getPlayerObject() {
        return playerObject;
    }

    public Pane getPane() {
        return pane;
    }

    public static GameClass getInstance(Pane pane) {
        if (instance == null)
            instance = new GameClass(pane);
        return instance;
    }

}
