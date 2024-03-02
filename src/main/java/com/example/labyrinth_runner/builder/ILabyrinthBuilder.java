package com.example.labyrinth_runner.builder;

import com.example.labyrinth_runner.labyrinth.ILabyrinth;
import com.example.labyrinth_runner.mesh_map_generator.IMeshMapGenerator;

public interface ILabyrinthBuilder {

    void setLabyrinthDimensions(int x, int y);

    void setMeshMap(IMeshMapGenerator meshMap);

    /**
     * Builds a labyrinth based on the mesh generated from the mesh map.
     *
     * @return An instance of ILabyrinth representing the constructed labyrinth.
     */
    ILabyrinth buildLabyrinth();

}
