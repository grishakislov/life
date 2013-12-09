package ru.arlechin.life;

import ru.arlechin.life.model.Pattern;
import ru.arlechin.life.model.Point;

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
    private Screen screen;
    private Boolean useLoop;

    public void init(int width, int height, Screen screen) {
        this.width = width;
        this.height = height;
        this.screen = screen;
        this.currentGeneration = new BitSet(width * height);
        this.nextGeneration = new BitSet(width * height);
    }

    public void loadPattern(Pattern pattern) {
        Point cell;
        this.useLoop = pattern.getUseLoop();
        for (int i = 0; i < pattern.getCells().size(); i++) {
            cell = pattern.getCells().get(i);
            setCell(cell.getX(), cell.getY(), true);
        }
    }

    public void makeTurn() {
        nextGeneration.clear();

        int h = useLoop ? height : height - 1;
        int w = useLoop ? width : width - 1;

        for (int y = 0; y <= h; y++) {
            for (int x = 0; x <= w; x++) {
                nextGeneration.set(indexOf(x, y), createNextGenerationCell(x, y));
            }
        }

        currentGeneration = (BitSet)nextGeneration.clone();
        screen.redraw(currentGeneration);
    }

    private Boolean createNextGenerationCell(int currentCellX, int currentCellY) {
        int numLivingNeighbours = 0;
        Boolean currentCellIsAlive = getCellAt(currentCellX, currentCellY);
        ArrayList<Boolean> neighbours = mooreNeighbourhoodFor(currentCellX, currentCellY);

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

    private ArrayList<Boolean> mooreNeighbourhoodFor(int x, int y) {
        ArrayList<Boolean> result = new ArrayList<Boolean>();

        result.add(getCellAt(x - 1, y - 1));
        result.add(getCellAt(x, y - 1));
        result.add(getCellAt(x + 1, y - 1));
        result.add(getCellAt(x - 1, y));
        result.add(getCellAt(x + 1, y));
        result.add(getCellAt(x - 1, y + 1));
        result.add(getCellAt(x, y + 1));
        result.add(getCellAt(x + 1, y + 1));

        return result;
    }

    public void setCell(int x, int y, Boolean value) {
        currentGeneration.set(indexOf(x, y), value);
    }

    private Boolean getCellAt(int x, int y) {
        if (!useLoop) {
            if (x < 0 || x > width - 1 || y < 0 || y > height - 1) {
                return false;
            }
        }
        return currentGeneration.get(indexOf(x, y));
    }

    private int indexOf(int x, int y) {

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
}
