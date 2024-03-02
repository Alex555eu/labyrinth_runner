package com.example.labyrinth_runner.builder.impl;

import com.example.labyrinth_runner.labyrinth.impl.RectangleLabyrinth;
import com.example.labyrinth_runner.figure.IFigure;
import com.example.labyrinth_runner.labyrinth.ILabyrinth;
import com.example.labyrinth_runner.mesh_map_generator.IMeshMapGenerator;
import com.example.labyrinth_runner.builder.ILabyrinthBuilder;
import com.example.labyrinth_runner.point.impl.Point2D;
import com.example.labyrinth_runner.figure.impl.MySquare;
import com.example.labyrinth_runner.Pair;

import java.util.ArrayList;
import java.util.List;

public class RectangleLabyrinthBuilder implements ILabyrinthBuilder {

    private double labyrinthWidth;
    private double labyrinthHeight;
    private IMeshMapGenerator meshMap;


    @Override
    public void setLabyrinthDimensions(int labyrinthWidth, int labyrinthHeight) {
        this.labyrinthWidth = labyrinthWidth;
        this.labyrinthHeight = labyrinthHeight;
    }

    @Override
    public void setMeshMap(IMeshMapGenerator meshMap) {
        this.meshMap = meshMap;
    }

    @Override
    public ILabyrinth buildLabyrinth() { //todo: add docs
        List<List<Boolean>> mesh = meshMap.generateMeshMap();

        double iteX = labyrinthWidth / meshMap.getMeshMapDimensionX() / 2;
        double iteY = labyrinthHeight / meshMap.getMeshMapDimensionY() / 2;
        double wallLength = labyrinthWidth / meshMap.getMeshMapDimensionX();

        List<List<Pair<Boolean,IFigure>>> walls = new ArrayList<>();

        for(int i = 0;  i < meshMap.getMeshMapDimensionX(); i++) {
            walls.add(new ArrayList<>());
            for(int j = 0; j < meshMap.getMeshMapDimensionY(); j++) {
                walls.get(i).add(j, null);
            }
        }

        for(int i = 0; i < mesh.size(); i++) {
            for(int j = 0; j < mesh.getFirst().size(); j++) {
                if (mesh.get(i).get(j)) {
                    walls.get(i).set(j, new Pair<>(true, (new MySquare(new Point2D(i * labyrinthWidth / meshMap.getMeshMapDimensionX() + iteX, j * labyrinthHeight / meshMap.getMeshMapDimensionY() + iteY), wallLength))));
                } else {
                    walls.get(i).set(j, new Pair<>(false, (new MySquare(new Point2D(i * labyrinthWidth / meshMap.getMeshMapDimensionX() + iteX, j * labyrinthHeight / meshMap.getMeshMapDimensionY() + iteY), wallLength))));
                }
            }
        }

        return new RectangleLabyrinth(walls, meshMap.getExitPoint(), labyrinthWidth, labyrinthHeight);
    }


}
