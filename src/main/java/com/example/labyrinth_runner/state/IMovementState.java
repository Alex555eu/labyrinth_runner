package com.example.labyrinth_runner.state;

import com.example.labyrinth_runner.player_object.impl.Axis;

public interface IMovementState {

    void execute(Axis direction);


}
