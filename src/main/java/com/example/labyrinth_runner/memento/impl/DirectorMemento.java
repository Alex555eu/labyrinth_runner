package com.example.labyrinth_runner.memento.impl;

import com.example.labyrinth_runner.memento.IMemento;

import java.io.Serializable;

public class DirectorMemento implements IMemento, Serializable {

    private int dimensionX;
    private int dimensionY;

    public DirectorMemento(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
    }

    public DirectorMemento(){};

    public int getDimensionX() {
        return dimensionX;
    }

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }
}
