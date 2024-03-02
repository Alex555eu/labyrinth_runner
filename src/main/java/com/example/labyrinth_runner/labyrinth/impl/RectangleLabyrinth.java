package com.example.labyrinth_runner.labyrinth.impl;

import com.example.labyrinth_runner.figure.IFigure;
import com.example.labyrinth_runner.figure.impl.MySquare;
import com.example.labyrinth_runner.labyrinth.ILabyrinth;
import com.example.labyrinth_runner.memento.impl.LabyrinthMemento;
import com.example.labyrinth_runner.memento.impl.SquareMemento;
import com.example.labyrinth_runner.Pair;
import com.example.labyrinth_runner.point.impl.Point2D;

import java.util.ArrayList;
import java.util.List;

public class RectangleLabyrinth implements ILabyrinth {
    private List<List<Pair<Boolean, IFigure>>> labyrinth;
    private double labyrinthWidth;
    private double labyrinthHeight;
    private Pair<Integer,Integer> exitPoint;

    public RectangleLabyrinth(List<List<Pair<Boolean, IFigure>>> labyrinth, Pair<Integer,Integer> exitPoint, double labyrinthWidth, double labyrinthHeight) {
        this.labyrinth = labyrinth;
        this.exitPoint = exitPoint;
        this.labyrinthWidth = labyrinthWidth;
        this.labyrinthHeight = labyrinthHeight;
    }

    @Override
    public List<List<Pair<Boolean,IFigure>>> getLabyrinth() {
        return labyrinth;
    }

    @Override
    public double[] getDimensions() {
        return new double[]{labyrinthWidth, labyrinthHeight};
    }

    @Override
    public Pair<Integer, Integer> getExitPoint() {
        return exitPoint;
    }

    @Override
    public LabyrinthMemento takeSnapshot() {
        List<List<Pair<Boolean, SquareMemento>>> mementoWalls = new ArrayList<>();
        for (List<Pair<Boolean,IFigure>> ob : labyrinth) {
            mementoWalls.add(new ArrayList<>());
            for (Pair<Boolean,IFigure> ob2 : ob) {
                mementoWalls.getLast().add(new Pair<>(ob2.getKey(), (SquareMemento)ob2.getValue().takeSnapshot()));
            }
        }
        return new LabyrinthMemento(mementoWalls, labyrinthWidth, labyrinthHeight, exitPoint);
    }

    @Override
    public void restoreMemento(LabyrinthMemento memento) {
        labyrinth = new ArrayList<>();
        for(int i = 0; i < memento.getWalls().size(); i++) {
            labyrinth.add(new ArrayList<>());
            for(int j = 0; j < memento.getWalls().get(i).size(); j++) {
                IFigure figure = new MySquare(new Point2D(0,0), 0);
                figure.restoreMemento(memento.getWalls().get(i).get(j).getValue());
                labyrinth.get(i).add(new Pair<>(memento.getWalls().get(i).get(j).getKey(), figure));
            }
        }

        this.labyrinthWidth = memento.getLabyrinthWidth();
        this.labyrinthHeight = memento.getLabyrinthHeight();
        this.exitPoint = memento.getExitPoint();
    }
}
