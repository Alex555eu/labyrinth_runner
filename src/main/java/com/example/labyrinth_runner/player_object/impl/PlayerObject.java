package com.example.labyrinth_runner.player_object.impl;

import com.example.labyrinth_runner.player_object.IPlayerObject;
import com.example.labyrinth_runner.state.IMovementState;
import com.example.labyrinth_runner.state.impl.MovingState;
import com.example.labyrinth_runner.state.impl.StopState;
import com.example.labyrinth_runner.memento.impl.PlayerObjectMemento;
import com.example.labyrinth_runner.point.impl.Point2D;
import javafx.animation.AnimationTimer;
import com.example.labyrinth_runner.Pair;
import javafx.scene.shape.Circle;


public class PlayerObject implements IPlayerObject {

    private Circle movingObject;
    private Pair<Integer, Integer> locationOnMeshMap;
    private IMovementState state;
    private Axis currentDirection;
    private AnimationTimer timer;

    public PlayerObject(Circle object, Pair<Integer,Integer> meshMapLocation) {
        this.movingObject = object;
        this.locationOnMeshMap = meshMapLocation;
        this.state = new StopState(this);
    }

    @Override
    public void moveUp() {
        move(Direction.UP.getDirection());
    }

    @Override
    public void moveRight() {
        move(Direction.RIGHT.getDirection());
    }

    @Override
    public void moveDown() {
        move(Direction.DOWN.getDirection());
    }

    @Override
    public void moveLeft() {
        move(Direction.LEFT.getDirection());
    }

    @Override
    public IMovementState getState() {
        return state;
    }

    @Override
    public void setState(IMovementState state) {
        this.state = state;
    }

    @Override
    public Circle getMovingObject() {
        return movingObject;
    }

    @Override
    public Pair<Integer, Integer> getLocationOnMeshMap() {
        return locationOnMeshMap;
    }

    @Override
    public void setLocationOnMeshMap(Pair<Integer, Integer> mesh) {
        this.locationOnMeshMap = mesh;
    }

    @Override
    public PlayerObjectMemento takeSnapshot() {
        return new PlayerObjectMemento(new Point2D(movingObject.getCenterX(), movingObject.getCenterY()).takeSnapshot(), movingObject.getRadius(), locationOnMeshMap, state);
    }

    @Override
    public void restoreMemento(PlayerObjectMemento memento) {
        this.movingObject = new Circle(memento.getMiddlePoint().getX(), memento.getMiddlePoint().getY(), memento.getRadius());
        this.locationOnMeshMap = memento.getMeshMapLocation();
        this.state = new StopState(this);
    }

    /**
     * This method checks the state of the object to determine whether it's in the moving state
     * or stopped state. If the object is in the moving state and the provided direction is different
     * from the current direction, or if there's no existing movement timer, the method starts
     * the movement in the specified direction. If the object is currently stopped, it stops
     * the movement by halting the animation timer.
     *
     * @param direction The direction in which to move the object
     */
    private void move(Axis direction) {
        if ((timer == null || !direction.equals(currentDirection)) && state.getClass().equals(MovingState.class)) {
            currentDirection = direction;
            if (timer != null)
                timer.stop();
            timer = new AnimationTimer() { // executes handle() method continuously, since calling start() until calling stop()
                long timeStamp = 0;
                @Override
                public void handle(long l) {
                    if ((System.currentTimeMillis() - timeStamp) > 70) {
                        state.execute(direction);
                        timeStamp = System.currentTimeMillis();
                    }
                }
            };
            timer.start();
        } else if (timer != null && state.getClass().equals(StopState.class)) {
            timer.stop();
            timer = null;
        }
    }

}

enum Direction {
    UP (new Axis(-1, 0)),
    RIGHT (new Axis(0, 1)),
    DOWN (new Axis(1, 0)),
    LEFT (new Axis(0, -1));

    private final Axis direction;

    private Direction(Axis obj) { this.direction = obj; }

    public Axis getDirection() { return direction; }
}
