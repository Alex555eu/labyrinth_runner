package com.example.labyrinth_runner;

import com.example.labyrinth_runner.state.impl.MovingState;
import com.example.labyrinth_runner.state.impl.StopState;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class GameClassController {

    private GameClass gameClass;

    public GameClassController(GameClass gameClass) {
        this.gameClass = gameClass;
    }

    public void mementoSnapHandler(Event event) {
        paintNewCheckpoint();
        gameClass.getMementoCareTaker().create(gameClass.getLabyrinth(), gameClass.getPlayerObject(), gameClass.getDirector());
    }

    public void mementoSnapToFileHandler(Event event) {
        mementoSnapHandler(event);
        gameClass.getMementoCareTaker().encode();
    }

    public void mementoRestoreHandler(Event event) {
        gameClass.getMementoCareTaker().restoreDirector(gameClass.getDirector());
        gameClass.getMementoCareTaker().restoreLabyrinth(gameClass.getLabyrinth());
        gameClass.getMementoCareTaker().restorePlayerObject(gameClass.getPlayerObject());

        gameClass.paintLabyrinth();
        gameClass.paintPlayerObject();
        paintNewCheckpoint();
    }

    public void mementoRestoreFromFileHandler(Event event) {
        gameClass.getMementoCareTaker().decode();
        mementoRestoreHandler(event);
    }

    public void handleDirectionKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                gameClass.getActiveKeys().put("direction", KeyCode.UP);
                if(gameClass.getPlayerObject().getState().getClass().equals(StopState.class))
                    gameClass.getPlayerObject().setState(new MovingState(gameClass.getLabyrinth(), gameClass.getPlayerObject()));
                gameClass.getPlayerObject().moveUp();
                break;
            case S:
            case DOWN:
                gameClass.getActiveKeys().put("direction", KeyCode.DOWN);
                if(gameClass.getPlayerObject().getState().getClass().equals(StopState.class))
                    gameClass.getPlayerObject().setState(new MovingState(gameClass.getLabyrinth(), gameClass.getPlayerObject()));
                gameClass.getPlayerObject().moveDown();
                break;
            case A:
            case LEFT:
                gameClass.getActiveKeys().put("direction", KeyCode.LEFT);
                if(gameClass.getPlayerObject().getState().getClass().equals(StopState.class))
                    gameClass.getPlayerObject().setState(new MovingState(gameClass.getLabyrinth(), gameClass.getPlayerObject()));
                gameClass.getPlayerObject().moveLeft();
                break;
            case D:
            case RIGHT:
                gameClass.getActiveKeys().put("direction", KeyCode.RIGHT);
                if(gameClass.getPlayerObject().getState().getClass().equals(StopState.class))
                    gameClass.getPlayerObject().setState(new MovingState(gameClass.getLabyrinth(), gameClass.getPlayerObject()));
                gameClass.getPlayerObject().moveRight();
                break;

            default:
                // Ignore other keys
        }
        exitMonitor();
    }

    public void handleDirectionKeyRelease(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                if (gameClass.getActiveKeys().containsKey("direction") && gameClass.getActiveKeys().get("direction").equals(KeyCode.UP)) {
                    gameClass.getActiveKeys().remove("direction");
                    gameClass.getPlayerObject().setState(new StopState(gameClass.getPlayerObject()));
                    gameClass.getPlayerObject().moveUp();
                }
                break;
            case S:
            case DOWN:
                if (gameClass.getActiveKeys().containsKey("direction") && gameClass.getActiveKeys().get("direction").equals(KeyCode.DOWN)) {
                    gameClass.getActiveKeys().remove("direction");
                    gameClass.getPlayerObject().setState(new StopState(gameClass.getPlayerObject()));
                    gameClass.getPlayerObject().moveDown();
                }
                break;
            case A:
            case LEFT:
                if (gameClass.getActiveKeys().containsKey("direction") && gameClass.getActiveKeys().get("direction").equals(KeyCode.LEFT)) {
                    gameClass.getActiveKeys().remove("direction");
                    gameClass.getPlayerObject().setState(new StopState(gameClass.getPlayerObject()));
                    gameClass.getPlayerObject().moveLeft();
                }
                break;
            case D:
            case RIGHT:
                if (gameClass.getActiveKeys().containsKey("direction") && gameClass.getActiveKeys().get("direction").equals(KeyCode.RIGHT)) {
                    gameClass.getActiveKeys().remove("direction");
                    gameClass.getPlayerObject().setState(new StopState(gameClass.getPlayerObject()));
                    gameClass.getPlayerObject().moveRight();
                }
                break;
            default:
                // Ignore other keys
        }
    }

    private void exitMonitor() {
        Pair<Integer,Integer> currentPos = gameClass.getPlayerObject().getLocationOnMeshMap();
        Pair<Integer,Integer> exitPoint = gameClass.getLabyrinth().getExitPoint();
        if (exitPoint.equals(currentPos)) {
            gameClass.generateNewGame();
        }
    }

    private void paintNewCheckpoint() { //todo: docs
        Circle movingObject = gameClass.getPlayerObject().getMovingObject();

        Circle checkpoint = new Circle(movingObject.getCenterX(), movingObject.getCenterY(), movingObject.getRadius());
        Color transparentColor = new Color(Color.RED.getRed(), Color.RED.getGreen(), Color.RED.getBlue(), 0.5); // Change opacity here
        checkpoint.setFill(transparentColor);

        gameClass.getPane().getChildren().add(checkpoint);
    }


}
