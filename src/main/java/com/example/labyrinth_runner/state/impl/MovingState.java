package com.example.labyrinth_runner.state.impl;

import com.example.labyrinth_runner.figure.IFigure;
import com.example.labyrinth_runner.labyrinth.ILabyrinth;
import com.example.labyrinth_runner.player_object.IPlayerObject;
import com.example.labyrinth_runner.player_object.impl.Axis;
import com.example.labyrinth_runner.state.IMovementState;
import com.example.labyrinth_runner.point.IPoint;
import javafx.scene.shape.Circle;
import com.example.labyrinth_runner.Pair;

public class MovingState implements IMovementState {
    private ILabyrinth labyrinth;
    private IPlayerObject playerObject;

    public MovingState(ILabyrinth labyrinth, IPlayerObject playerObject) {
        this.labyrinth = labyrinth;
        this.playerObject = playerObject;
    }

    /**
     *
     * Method takes direction, and adds its values to current object's indexes, creating new index on mesh/grid of labyrinth.
     * If new index is in bounds of labyrinth, we access front-end object (moving object) and reassign its position on users screen.
     *
     * @param direction, in which player object is trying to be repositioned
     */
    @Override
    public void execute(Axis direction) {
        Circle movingObject = playerObject.getMovingObject();
        Pair<Integer, Integer> meshMapLocation = playerObject.getLocationOnMeshMap();

        int horIdx = meshMapLocation.getKey() + direction.getHorizontal();
        int verIdx = meshMapLocation.getValue() + direction.getVertical();

        Pair<Boolean, IFigure> square = null;
        try {
            square = labyrinth.getLabyrinth()
                    .get(horIdx)
                    .get(verIdx);
        } catch (IndexOutOfBoundsException e) {
            //e.printStackTrace();
        }

        if (square != null) {
            if (!square.getKey()) {
                IPoint newPosition = square.getValue().getMiddlePoint();
                playerObject.setLocationOnMeshMap(new Pair<>(horIdx,verIdx));
                movingObject.setCenterX(newPosition.getX());
                movingObject.setCenterY(newPosition.getY());
            }
        }
    }

}












