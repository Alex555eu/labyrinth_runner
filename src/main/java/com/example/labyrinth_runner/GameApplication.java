package com.example.labyrinth_runner;

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;

import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class GameApplication extends Application {

    private final int WINDOW_WIDTH = 900;
    private final int WINDOW_HEIGHT = 500;
    private Stage secondaryStage;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.secondaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Labyrinth Runner");
        primaryStage.setScene(scene);
        primaryStage.show();

        GameApplicationController controller = fxmlLoader.getController();
        controller.setMainApp(this);
    }

    public void openMainWindow(boolean isGameNew) {
        Stage primaryStage = new Stage();

        Pane pane = new Pane();
        GameClass gameClass = GameClass.getInstance(pane);
        GameClassController gameClassController = new GameClassController(gameClass);

        gameClass.generateNewGame();

        if (!isGameNew)
            gameClassController.mementoRestoreFromFileHandler(null);

        Button button1 = new Button("New Checkpoint");
        Button button2 = new Button("Go Back To Checkpoint");

        button1.setOnAction(gameClassController::mementoSnapHandler);
        button2.setOnAction(gameClassController::mementoRestoreHandler);

        button1.setFocusTraversable(false);
        button2.setFocusTraversable(false);

        button1.setLayoutX(gameClass.getLabyrinth().getDimensions()[0] * 0.25);
        button1.setLayoutY(450);

        button2.setLayoutX(gameClass.getLabyrinth().getDimensions()[0] * 0.5);
        button2.setLayoutY(450);

        pane.getChildren().addAll(button1, button2);

        double[] tmp = gameClass.getLabyrinth().getDimensions();
        Translate translate = new Translate((int)((WINDOW_WIDTH - tmp[0]) / 2), 20);
        pane.getTransforms().add(translate);


        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);

        scene.setOnKeyPressed(gameClassController::handleDirectionKeyPress);
        scene.setOnKeyReleased(gameClassController::handleDirectionKeyRelease);

        primaryStage.setTitle("Labyrinth Runner");
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                gameClassController.mementoSnapToFileHandler(t);
            }
        });

        secondaryStage.close();

    }

}