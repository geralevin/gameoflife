package com.gera.codechalange.gameoflife;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <h1>Conway's Game of Life</h1>
 * <p>
 * <h2>Rules:</h2> <ol><li>Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.</li>
 * <li>Any live cell with two or three live neighbours lives on to the next generation.</li> <li>Any live cell with more
 * than three live neighbours dies, as if by overpopulation.</li> <li>Any dead cell with exactly three live neighbours
 * becomes a live cell, as if by reproduction.</li></ol>
 */
public final class GameOfLife {
    //coordinate offsets to reach neighbours
    private static final int neighboursOffsets[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    /**
     * @param field given the field for the game of life, executes game rules and returns new field
     * @return new Game of Life field
     */
    public static GameField executeGameRules(GameField field) {
        //rule if a life cell has < than 2 neighbours or > 3 than it dies, return true if the cell is still alive
        Predicate<Coordinate> dieRule = c -> {
            int count = lifeNeighboursCount(field.getGameField(), c.getRow(), c.getCol());

            //die rules: rule 1, and rule 2, and keep alive rule 3
            if (count < 2 || count > 3) {
                return false;
            }

            return true;
        };

        //rule to be used on a dead cell, whether it needs to be resurected
        Predicate<Coordinate> becomeAliveRule = c -> lifeNeighboursCount(field.getGameField(), c.getRow(), c.getCol()) == 3;

        //in parallel apply die rule and see who is still alive after the rule is applied
        Set<Coordinate> liveCells = field.getLifeCells().parallelStream().filter(dieRule).collect(Collectors.toSet());

        //in parallel apply resurrect rule on dead neighbors of a life cells and get set of resurrected cells
        Set<Coordinate> resurrectedCells = field.getLifeCells().parallelStream().map(coordinate -> {
            boolean[][] area = field.getGameField();
            Collection<Coordinate> deadCells = new LinkedList<>();
            for (int i = 0; i < neighboursOffsets.length; i++) {
                int row = coordinate.getRow() + neighboursOffsets[i][0];
                int col = coordinate.getCol() + neighboursOffsets[i][1];
                if (row >= 0 && col >= 0 && row < area.length && col < area[0].length && !area[row][col]) {
                    deadCells.add(Coordinate.of(row, col));
                }
            }
            return deadCells;
        }).flatMap(Collection::stream).filter(becomeAliveRule).collect(Collectors.toSet());

        //merge life cells and return new field
        liveCells.addAll(resurrectedCells);
        return new GameField(liveCells, field.getRows(), field.getCols());
    }

    //get the live cell neighbour count
    private static int lifeNeighboursCount(boolean[][] field, int row, int col) {
        int count = 0;
        for (int i = 0; i < neighboursOffsets.length; i++) {
            int r = row + neighboursOffsets[i][0];
            int c = col + neighboursOffsets[i][1];
            if (r >= 0 && c >= 0 && r < field.length && c < field[0].length && field[r][c]) {
                ++count;
            }
        }
        return count;
    }
}
