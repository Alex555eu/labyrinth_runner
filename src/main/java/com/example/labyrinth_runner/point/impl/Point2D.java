package com.example.labyrinth_runner.point.impl;

import com.example.labyrinth_runner.point.IPoint;
import com.example.labyrinth_runner.memento.impl.Point2DMemento;

public class Point2D implements IPoint {
    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {};

    public Point2D(IPoint copy) {
        this.x = copy.getX();
        this.y = copy.getY();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getDistance(IPoint pt) {
        return Math.sqrt(Math.pow(pt.getX() - x, 2) + Math.pow(pt.getY() - y, 2));
    }

    @Override
    public Point2DMemento takeSnapshot() {
        return new Point2DMemento(x, y);
    }

    @Override
    public void restoreMemento(Point2DMemento memento) {
        this.x = memento.getX();
        this.y = memento.getY();
    }
}
