package com.example.labyrinth_runner.builder;

import com.example.labyrinth_runner.memento.impl.DirectorMemento;

/**
 * Is responsible for orchestrating the Builder in the construction process of a labyrinth.
 */
public interface IDirector {

    void constructRectangleLabyrinth(ILabyrinthBuilder builder);

    void constructSquareLabyrinth(ILabyrinthBuilder builder);

    void resizableRectangleLabyrinth(ILabyrinthBuilder builder);

    void resizableSquareLabyrinth(ILabyrinthBuilder builder);

    DirectorMemento takeSnapshot();

    void restoreMemento(DirectorMemento memento);

}
