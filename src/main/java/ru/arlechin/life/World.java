package ru.arlechin.life;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author arlechin
 */
public class World {

    private BitSet currentGeneration;
    private BitSet nextGeneration;
    private int width;
    private int height;

    public void init(int width, int height) {
        this.width = width;
        this.height = height;
        currentGeneration = new BitSet(width * height);
        nextGeneration = new BitSet(width * height);
    }

    public void turn() {
        nextGeneration.clear();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nextGeneration.set(getIndex(x, y), createNextGenerationCell(x, y));
            }
        }

        currentGeneration = (BitSet)nextGeneration.clone();
    }

    private Boolean createNextGenerationCell(int currentCellX, int currentCellY) {
        int numLivingNeighbours = 0;
        Boolean currentCellIsAlive = getCellAt(currentCellX,currentCellY);
        ArrayList<Boolean> neighbours = getMooreNeighbourhoodFor(currentCellX, currentCellY);

        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i)) {
                numLivingNeighbours++;
            }
        }

        if (currentCellIsAlive) {
            currentCellIsAlive = numLivingNeighbours == 2 || numLivingNeighbours == 3;
        } else {
            currentCellIsAlive = numLivingNeighbours == 3;
        }

        return currentCellIsAlive;
    }


    private ArrayList<Boolean> getMooreNeighbourhoodFor(int x, int y) {
        ArrayList<Boolean> result = new ArrayList<Boolean>();
        result.add(getCellAt(x-1, y-1));
        result.add(getCellAt(x,   y-1));
        result.add(getCellAt(x+1, y-1));
        result.add(getCellAt(x-1, y));
        result.add(getCellAt(x+1, y));
        result.add(getCellAt(x-1, y+1));
        result.add(getCellAt(x,   y+1));
        result.add(getCellAt(x+1, y+1));
        return result;
    }

    public void setCell(int x, int y, Boolean value) {
        currentGeneration.set(getIndex(x, y), value);
    }

    private Boolean getCellAt(int x, int y) {
        return currentGeneration.get(getIndex(x, y));
    }

    private int getIndex(int x, int y) {

        if (x < 0) {
            x = width-1 + x;
        } else if (x > (width - 1)) {
            x = x - width;
        }

        if (y < 0) {
            y = height - 1 + y;
        } else if (y > (height - 1)) {
            y = y - height;
        }

        return y * height + x;
    }

    public BitSet getCurrentGeneration() {
        return currentGeneration;
    }
}