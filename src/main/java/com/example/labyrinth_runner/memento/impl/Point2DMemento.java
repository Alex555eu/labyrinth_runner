package com.example.labyrinth_runner.memento.impl;

import com.example.labyrinth_runner.memento.IMemento;

import java.io.Serializable;

public class Point2DMemento implements IMemento, Serializable {

    private double x;
    private double y;

    public Point2DMemento(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2DMemento(){};

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
