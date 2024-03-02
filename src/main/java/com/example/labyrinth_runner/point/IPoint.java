package com.example.labyrinth_runner.point;

import com.example.labyrinth_runner.memento.impl.Point2DMemento;

public interface IPoint {

    double getX();

    double getY();

    double getDistance(IPoint pt);

    Point2DMemento takeSnapshot();

    void restoreMemento(Point2DMemento memento);

}
