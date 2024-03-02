package com.example.labyrinth_runner.memento.impl;

import com.example.labyrinth_runner.memento.IMemento;
import com.example.labyrinth_runner.state.IMovementState;
import com.example.labyrinth_runner.Pair;

import java.io.Serializable;

public class PlayerObjectMemento implements IMemento, Serializable {

    private Point2DMemento middlePoint;
    private double radius;
    private Pair<Integer, Integer> meshMapLocation;
    //private transient IMovementState state;

    public PlayerObjectMemento(Point2DMemento middlePoint, double radius, Pair<Integer, Integer> meshMapLocation, IMovementState state) {
        this.middlePoint = middlePoint;
        this.radius = radius;
        this.meshMapLocation = meshMapLocation;
        //this.state = state;
    }

    public PlayerObjectMemento() {
    }

    public Point2DMemento getMiddlePoint() {
        return middlePoint;
    }

    public void setMiddlePoint(Point2DMemento middlePoint) {
        this.middlePoint = middlePoint;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Pair<Integer, Integer> getMeshMapLocation() {
        return meshMapLocation;
    }

    public void setMeshMapLocation(Pair<Integer, Integer> meshMapLocation) {
        this.meshMapLocation = meshMapLocation;
    }



}
