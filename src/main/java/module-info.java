module com.example.labyrinth_runner {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;

    exports com.example.labyrinth_runner.memento;
    exports com.example.labyrinth_runner.player_object;
    exports com.example.labyrinth_runner.point;
    exports com.example.labyrinth_runner.state;
    exports com.example.labyrinth_runner.builder;

    opens com.example.labyrinth_runner to javafx.fxml;
    exports com.example.labyrinth_runner;
    exports com.example.labyrinth_runner.labyrinth;

    opens com.example.labyrinth_runner.labyrinth to javafx.fxml;
    exports com.example.labyrinth_runner.memento.impl;
    exports com.example.labyrinth_runner.labyrinth.impl;
    opens com.example.labyrinth_runner.labyrinth.impl to javafx.fxml;
    opens com.example.labyrinth_runner.memento.impl to javafx.fxml;
}