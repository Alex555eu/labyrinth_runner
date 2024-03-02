package com.example.labyrinth_runner.state.impl;

import com.example.labyrinth_runner.state.IMovementState;
import com.example.labyrinth_runner.player_object.IPlayerObject;
import com.example.labyrinth_runner.player_object.impl.Axis;

public class StopState implements IMovementState {

    private IPlayerObject player;

    public StopState(IPlayerObject player){
        this.player = player;
    }

    @Override
    public void execute(Axis direction) {

    }
}
