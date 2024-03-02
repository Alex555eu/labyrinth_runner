package com.example.labyrinth_runner.player_object.impl;

public class Axis {
    private Integer vertical;
    private Integer horizontal;

    public Axis(Integer vertical, Integer horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Integer getVertical() {
        return vertical;
    }

    public Integer getHorizontal() {
        return horizontal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Axis other = (Axis) obj;

        return other.getVertical().equals(vertical) && other.getHorizontal().equals(horizontal);
    }
}
