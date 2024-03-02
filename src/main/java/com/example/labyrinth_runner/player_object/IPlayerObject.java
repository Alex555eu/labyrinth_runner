package com.example.labyrinth_runner.player_object;

import com.example.labyrinth_runner.memento.impl.PlayerObjectMemento;
import com.example.labyrinth_runner.state.IMovementState;
import com.example.labyrinth_runner.Pair;
import javafx.scene.shape.Circle;

/**
 * It is responsible for storing players object we see on screen, and invoking methods needed to change its position on it accordingly to users input.
 */
public interface IPlayerObject {

    void moveUp();

    void moveRight();

    void moveDown();

    void moveLeft();

    IMovementState getState();

    void setState(IMovementState state);

    Circle getMovingObject();

    Pair<Integer, Integer> getLocationOnMeshMap();

    void setLocationOnMeshMap(Pair<Integer, Integer> mesh);

    PlayerObjectMemento takeSnapshot();

    void restoreMemento(PlayerObjectMemento memento);


}
