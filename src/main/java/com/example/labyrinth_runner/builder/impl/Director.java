package com.example.labyrinth_runner.builder.impl;

import com.example.labyrinth_runner.memento.impl.DirectorMemento;
import com.example.labyrinth_runner.mesh_map_generator.impl.MeshMapGenerator;
import com.example.labyrinth_runner.builder.IDirector;
import com.example.labyrinth_runner.builder.ILabyrinthBuilder;


public class Director implements IDirector {

    private final int defaultWidth = 820;
    private final int defaultHeight = 420;
    private final int increment = 4;
    private int dimensionX = 41;
    private int dimensionY = 21;

    @Override
    public void constructRectangleLabyrinth(ILabyrinthBuilder builder) {
        builder.setLabyrinthDimensions(820, 420);
        builder.setMeshMap(new MeshMapGenerator(41, 21));
    }

    @Override
    public void constructSquareLabyrinth(ILabyrinthBuilder builder) {
        builder.setLabyrinthDimensions(810, 810);
        builder.setMeshMap(new MeshMapGenerator(81, 81));
    }

    @Override
    public void resizableRectangleLabyrinth(ILabyrinthBuilder builder) {
        builder.setLabyrinthDimensions(findNearestMultiple(dimensionX, defaultWidth), findNearestMultiple(dimensionY, defaultHeight));
        builder.setMeshMap(new MeshMapGenerator(dimensionX , dimensionY ));
        dimensionX += increment;
        dimensionY += increment;
    }

    @Override
    public void resizableSquareLabyrinth(ILabyrinthBuilder builder) {
        //todo: implementation
    }

    @Override
    public DirectorMemento takeSnapshot() {
        return new DirectorMemento(dimensionX, dimensionY);
    }

    @Override
    public void restoreMemento(DirectorMemento memento) {
        this.dimensionX = memento.getDimensionX();
        this.dimensionY = memento.getDimensionY();
    }

    private int findNearestMultiple(int numberToMultiply, int target) {
        int tmp = 0;
        int result = 0;
        while(true) {
            tmp = result;
            result += numberToMultiply;
            if (result > target)
                return (result - target) >= (target - tmp) ? tmp : result;
        }
    }

}
