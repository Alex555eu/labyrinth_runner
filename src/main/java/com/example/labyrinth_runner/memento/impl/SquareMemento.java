package com.example.labyrinth_runner.memento.impl;

import com.example.labyrinth_runner.memento.IMemento;

import java.io.Serializable;

public class SquareMemento implements IMemento, Serializable {

    private Point2DMemento topLeftCornerPoint;
    private Point2DMemento middlePoint;
    private double wallLength;

    public SquareMemento(Point2DMemento topLeftCornerPoint, Point2DMemento middlePoint, double wallLength) {
        this.topLeftCornerPoint = topLeftCornerPoint;
        this.middlePoint = middlePoint;
        this.wallLength = wallLength;
    }

    public SquareMemento() {
    }

    public Point2DMemento getTopLeftCornerPoint() {
        return topLeftCornerPoint;
    }

    public void setTopLeftCornerPoint(Point2DMemento topLeftCornerPoint) {
        this.topLeftCornerPoint = topLeftCornerPoint;
    }

    public Point2DMemento getMiddlePoint() {
        return middlePoint;
    }

    public void setMiddlePoint(Point2DMemento middlePoint) {
        this.middlePoint = middlePoint;
    }

    public double getWallLength() {
        return wallLength;
    }

    public void setWallLength(double wallLength) {
        this.wallLength = wallLength;
    }
}
