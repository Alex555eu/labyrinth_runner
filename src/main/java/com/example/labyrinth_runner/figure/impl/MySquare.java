package com.example.labyrinth_runner.figure.impl;

import com.example.labyrinth_runner.memento.IMemento;
import com.example.labyrinth_runner.point.IPoint;
import com.example.labyrinth_runner.figure.IFigure;
import com.example.labyrinth_runner.memento.impl.SquareMemento;
import com.example.labyrinth_runner.point.impl.Point2D;

public class MySquare implements IFigure  {

    private IPoint topLeftCornerPoint;
    private IPoint middlePoint;
    private double wallLength;

    public MySquare(IPoint middlePoint, double wallLength) {
        this.middlePoint = middlePoint;
        this.wallLength = wallLength;
        this.topLeftCornerPoint = new Point2D(middlePoint.getX() - (wallLength/2), middlePoint.getY() - (wallLength/2));
    }

    @Override
    public IPoint getDrawingStartingPoint() {
        return topLeftCornerPoint;
    }

    @Override
    public IPoint getMiddlePoint() {
        return middlePoint;
    }

    @Override
    public double getWidth() {
        return wallLength;
    }

    @Override
    public double getHeight() {
        return wallLength;
    }

    @Override
    public SquareMemento takeSnapshot() {
        return new SquareMemento(topLeftCornerPoint.takeSnapshot(), middlePoint.takeSnapshot(), wallLength);
    }

    @Override
    public void restoreMemento(IMemento imemento) {
        if (imemento.getClass().equals(SquareMemento.class)) {
            SquareMemento memento = (SquareMemento) imemento;
            this.topLeftCornerPoint.restoreMemento(memento.getTopLeftCornerPoint());
            this.middlePoint.restoreMemento(memento.getMiddlePoint());
            this.wallLength = memento.getWallLength();
        }
    }
}
