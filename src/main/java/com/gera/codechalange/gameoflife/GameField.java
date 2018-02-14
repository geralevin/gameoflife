package com.gera.codechalange.gameoflife;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Game Field represents a structure on where the game off life is played
 */
public final class GameField {
    private final boolean gameField[][];
    private final Collection<Coordinate> lifeCells;

    /**
     * @param lifeCells collection of life cells
     * @param rows      - rows of the field
     * @param cols      - columns of the field
     */
    public GameField(Collection<Coordinate> lifeCells, int rows, int cols) {
        checkNotNull(lifeCells);
        checkArgument(rows > 0);
        checkArgument(cols > 0);
        this.lifeCells = lifeCells;
        this.gameField = new boolean[rows][cols];
        lifeCells.parallelStream().forEach(cell -> gameField[cell.getRow()][cell.getCol()] = true);
    }


    /**
     * Get game field in 2d representation
     *
     * @return 2D representation of the game field
     */
    public boolean[][] getGameField() {
        return gameField;
    }

    /**
     * Returns collection of the life cells on the field
     *
     * @return collection of life cells coordinate
     */
    public Collection<Coordinate> getLifeCells() {
        return lifeCells;
    }

    /**
     * @return row count of the field
     */
    public int getRows() {
        return gameField.length;
    }

    /**
     * @return column count of the field
     */
    public int getCols() {
        return gameField[0].length;
    }

    /**
     * Convers field into .* format, where * is alive cell and . is the dead cell
     *
     * @return dot-star representation of the field
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(gameField.length * gameField[0].length + gameField.length);

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                builder.append(gameField[i][j] ? "*" : ".");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
