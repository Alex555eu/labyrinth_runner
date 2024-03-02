package com.example.labyrinth_runner.labyrinth;

import com.example.labyrinth_runner.figure.IFigure;
import com.example.labyrinth_runner.memento.impl.LabyrinthMemento;
import com.example.labyrinth_runner.Pair;

import java.util.List;

/**
 * Interface representing Labyrinth in a form of 2D list, consisting of Pair< boolean, IFigure> <br/><br/> boolean value signals wall/floor of a labyrinth, and IFigure
 * holds values necessary to build it's front-end representation (javafx 'Shapes').
 */
public interface ILabyrinth {

    List<List<Pair<Boolean, IFigure>>> getLabyrinth();

    double[] getDimensions();

    Pair<Integer,Integer> getExitPoint();

    LabyrinthMemento takeSnapshot();

    void restoreMemento(LabyrinthMemento memento);

}
