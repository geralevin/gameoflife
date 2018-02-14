package com.gera.codechalange.gameoflife;

import java.util.Objects;

/**
 * Class representing 2D coordinate
 */
public final class Coordinate {
    private int row;
    private int col;

    /**
     * Coordinate
     *
     * @param row - row
     * @param col - col
     * @return new instance of a coordinate
     */
    public static Coordinate of(int row, int col) {

        Coordinate coordinate = new Coordinate();
        coordinate.row = row;
        coordinate.col = col;
        return coordinate;
    }

    /**
     * Returns row
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns column
     *
     * @return column
     */
    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row &&
                col == that.col;
    }

    @Override
    public int hashCode() {

        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
