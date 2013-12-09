package ru.arlechin.life.model;

import java.util.ArrayList;

/**
 * @author arlechin
 */
public class Pattern {

    private ArrayList<Point> cells;
    private Boolean useLoop;

    public ArrayList<Point> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Point> cells) {
        this.cells = cells;
    }

    public Boolean getUseLoop() {
        return useLoop;
    }

    public void setUseLoop(Boolean useLoop) {
        this.useLoop = useLoop;
    }
}
