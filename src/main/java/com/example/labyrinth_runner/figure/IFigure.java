package com.example.labyrinth_runner.figure;

import com.example.labyrinth_runner.memento.IMemento;
import com.example.labyrinth_runner.point.IPoint;

/**
 * Interface for figures, that hold values necessary to build javafx 'Shapes'
 */
public interface IFigure {

    IPoint getDrawingStartingPoint();

    IPoint getMiddlePoint();

    double getWidth();

    double getHeight();

    IMemento takeSnapshot();

    void restoreMemento(IMemento memento);

}
