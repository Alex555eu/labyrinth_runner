package com.example.labyrinth_runner.mesh_map_generator;

import com.example.labyrinth_runner.Pair;

import java.util.List;

public interface IMeshMapGenerator {

    /**
     * Generates a 2D boolean list, representing a mesh/grid of a labyrinth,
     * true/false values represent walls/floor, between(on) which players will navigate their object to find exit from the labyrinth.
     *
     * @return 2D boolean list, based on which labyrinth will be constructed.
     */
    List<List<Boolean>> generateMeshMap();

    int getMeshMapDimensionX();

    int getMeshMapDimensionY();

    /**
     * Element of a mesh, which is an exit/escape from labyrinth.
     *
     * @return Pair, storing indexes of exit point in a mesh
     */
    Pair<Integer,Integer> getExitPoint();
}
