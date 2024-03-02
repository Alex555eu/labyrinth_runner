package com.example.labyrinth_runner.memento.impl;

import com.example.labyrinth_runner.memento.IMemento;
import com.example.labyrinth_runner.Pair;

import java.io.Serializable;
import java.util.List;

public class LabyrinthMemento implements IMemento, Serializable {
    private List<List<Pair<Boolean, SquareMemento>>> walls;
    private double labyrinthWidth;
    private double labyrinthHeight;
    private Pair<Integer,Integer> exitPoint;

    public LabyrinthMemento(List<List<Pair<Boolean, SquareMemento>>> walls, double labyrinthWidth, double labyrinthHeight, Pair<Integer, Integer> exitPoint) {
        this.walls = walls;
        this.labyrinthWidth = labyrinthWidth;
        this.labyrinthHeight = labyrinthHeight;
        this.exitPoint = exitPoint;
    }

    public LabyrinthMemento() {
    }

    public List<List<Pair<Boolean, SquareMemento>>> getWalls() {
        return walls;
    }

    public double getLabyrinthWidth() {
        return labyrinthWidth;
    }

    public double getLabyrinthHeight() {
        return labyrinthHeight;
    }

    public Pair<Integer, Integer> getExitPoint() {
        return exitPoint;
    }

    public void setWalls(List<List<Pair<Boolean, SquareMemento>>> walls) {
        this.walls = walls;
    }

    public void setLabyrinthWidth(double labyrinthWidth) {
        this.labyrinthWidth = labyrinthWidth;
    }

    public void setLabyrinthHeight(double labyrinthHeight) {
        this.labyrinthHeight = labyrinthHeight;
    }

    public void setExitPoint(Pair<Integer, Integer> exitPoint) {
        this.exitPoint = exitPoint;
    }
}
