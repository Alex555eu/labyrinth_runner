package com.example.labyrinth_runner.mesh_map_generator.impl;

import com.example.labyrinth_runner.mesh_map_generator.IMeshMapGenerator;
import com.example.labyrinth_runner.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class MeshMapGenerator implements IMeshMapGenerator {

    private int meshMapDimensionX;
    private int meshMapDimensionY;
    private List<List<Boolean>> meshMap;
    private Pair<Integer,Integer> exitPoint;
    private boolean[][] visited;

    public MeshMapGenerator(int meshMapDimensionX, int meshMapDimensionY) {
        this.meshMapDimensionX = meshMapDimensionX;
        this.meshMapDimensionY = meshMapDimensionY;
        meshMap = new ArrayList<>();
        for(int i = 0;  i < meshMapDimensionX; i++) {
            meshMap.add(new ArrayList<>());
            for(int j = 0; j < meshMapDimensionY; j++) {
                meshMap.get(i).add(true);
            }
        }
        visited = new boolean[meshMapDimensionX][meshMapDimensionY];
    }

    @Override
    public int getMeshMapDimensionX() {
        return this.meshMapDimensionX;
    }

    @Override
    public int getMeshMapDimensionY() {
        return this.meshMapDimensionY;
    }

    /**
     * This implementation uses 'Depth-First Search' algorithm variation, commonly called as 'Randomized Depth-First Search' or 'Recursive Backtracking'
     * algorithm, which uses a 2D list filled with 'true' values (walls) and carves inside of them 'false' values (floors).
     * Algorithm begins from specified point in our mesh (entry point), and works its way until all available space is utilized.
     *
     * @return Mesh, on which labyrinth will be built.
     */
    @Override
    public List<List<Boolean>> generateMeshMap() {

        Stack<Cell> cellsWithUnvisitedNeighbours = new Stack<>();
        cellsWithUnvisitedNeighbours.push(new Cell(1, 1)); // entry point from which we start building our walls

        while ( !cellsWithUnvisitedNeighbours.isEmpty()) {
            Cell current = cellsWithUnvisitedNeighbours.peek();

            visited[current.column][current.row] = true;

            meshMap.get(current.column).set(current.row, false);

            Cell[] neighbours = getNeighbours(current.column, current.row); // assign neighbours cell, if they don't exist assign null

            boolean hasUnvisitedNeighbour = false;
            for (Cell neighbour : neighbours) {
                if (neighbour != null && !visited[neighbour.column][neighbour.row]) {
                    hasUnvisitedNeighbour = true;
                    break;
                }
            }

            if (hasUnvisitedNeighbour) {
                Cell randomNeighbour = null;
                while (randomNeighbour == null || visited[randomNeighbour.column][randomNeighbour.row]) {
                    randomNeighbour = neighbours[(int) (Math.random() * neighbours.length)]; // we pick random neighbour that exist and hasn't been visited before
                }
                cellsWithUnvisitedNeighbours.push(randomNeighbour);

                int inDirectCellColumn = (current.column + randomNeighbour.column) / 2;
                int indirectCellRow =  (current.row + randomNeighbour.row) / 2;
                meshMap.get(inDirectCellColumn).set(indirectCellRow, false);

            } else {
                cellsWithUnvisitedNeighbours.pop(); // go back to previous neighbour, the one we came from
            }
        }

        makeExitPoint(meshMap); // algorithm doesn't make exit of labyrinth, so we make it separately ourselves

        return meshMap;
    }

    @Override
    public Pair<Integer, Integer> getExitPoint() {
        return exitPoint;
    }

    private Cell[] getNeighbours(int col, int row) { // cell is not a neighbour when it's out of bounds of a mesh
        Cell[] neighbours = new Cell[4];
        neighbours[0] = isValidCell(row - 2, col) ? new Cell(row - 2, col) : null;   // Top
        neighbours[1] = isValidCell(row, col + 2) ? new Cell(row, col + 2) : null; // Right
        neighbours[2] = isValidCell(row + 2, col) ? new Cell(row + 2, col) : null;   // Bottom
        neighbours[3] = isValidCell(row, col - 2) ? new Cell(row, col - 2) : null; // Left
        return neighbours;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < meshMapDimensionY && col >= 0 && col < meshMapDimensionX;
    }

    private void makeExitPoint(List<List<Boolean>> meshMap) {
        int lastColumn = meshMap.size()-1;
        int oneBeforeLastRow = meshMap.getFirst().size()-2;
        for(int i = lastColumn; i >= 0; i--) {
            if (!meshMap.get(i).get(oneBeforeLastRow)) { // if it's floor
                meshMap.get(i).set(oneBeforeLastRow + 1, false);
                exitPoint = new Pair<>(i, oneBeforeLastRow + 1);
                break;
            }
        }
    }

    private static class Cell {
        int row;
        int column;
        Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }


}
