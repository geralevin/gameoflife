package com.gera.codechalange.gameoflife;

import org.junit.Test;

import java.util.Collections;

import static com.gera.codechalange.gameoflife.GameOfLife.executeGameRules;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Testing GameLife test
 */
public class GameOfLifeTest {

    private static GameField loadLiveCells(String file) {
        return GameOfLifeApp.gameFieldFromLifeCells(GameOfLifeTest.class.getClassLoader().getResource(file));
    }

    /**
     * Check empty field
     */
    @Test(expected = IllegalArgumentException.class)
    public void emptyFieldTest() {
        new GameField(Collections.emptySet(), 0, 0);
    }

    /**
     * Check if all cells are dead they will still be dead
     */
    @Test
    public void testAllDeadCellsInput() {
        GameField emptyGameField = new GameField(Collections.emptySet(), 100, 50);

        GameField outputField = executeGameRules(emptyGameField);

        assertEquals(outputField.getLifeCells(), emptyGameField.getLifeCells());
    }

    /**
     * One cell field test
     */
    @Test
    public void testOneCellField() {
        GameField oneCellDeadField = new GameField(Collections.emptySet(), 1, 1);
        GameField output= executeGameRules(new GameField(Collections.singleton(Coordinate.of(0, 0)), 1, 1));

        assertEquals(output.getLifeCells(), oneCellDeadField.getLifeCells());
        assertArrayEquals(output.getGameField(), output.getGameField());
    }

    /**
     * Check 4x8 field
     */
    @Test
    public void test1For4by8() {
        GameField expectedField = loadLiveCells("output1-4x8.txt");
        GameField output = executeGameRules(loadLiveCells("input1-4x8.txt"));

        assertEquals(output.getLifeCells(), expectedField.getLifeCells());
        assertArrayEquals(output.getGameField(), expectedField.getGameField());

    }

    /**
     * Check 6x8 field (as in assignment)
     */
    @Test
    public void test2For6x8() {
        GameField expectedField = loadLiveCells("output2-6x8.txt");
        GameField output = executeGameRules(loadLiveCells("input2-6x8.txt"));

        assertEquals(output.getLifeCells(), expectedField.getLifeCells());
        assertArrayEquals(output.getGameField(), expectedField.getGameField());
    }

    /**
     * Check 10x1 field (just a corner case with single column)
     */
    @Test
    public void test3For10x1() {
        GameField expectedField = loadLiveCells("output3-10x1.txt");
        GameField output = executeGameRules(loadLiveCells("input3-10x1.txt"));

        assertEquals(output.getLifeCells(), expectedField.getLifeCells());
        assertArrayEquals(output.getGameField(), expectedField.getGameField());
    }

    /**
     * Check 10x1 field (corner case with single row)
     */
    @Test
    public void test4For1x10() {
        GameField expectedField = loadLiveCells("output4-1x10.txt");
        GameField output = executeGameRules(loadLiveCells("input4-1x10.txt"));

        assertEquals(output.getLifeCells(), expectedField.getLifeCells());
        assertArrayEquals(output.getGameField(), expectedField.getGameField());
    }
}
