package com.gera.codechalange.gameoflife;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Game of life app
 */
public class GameOfLifeApp {
    public static void main(String[] args) {
        try {
            GameField newField = GameOfLife.executeGameRules(gameFieldFromLifeCells(new URL(args[0])));
            System.out.println(newField.toString());
        } catch (MalformedURLException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Read the file from the given URL
     *
     * @param url - url to a file containing input
     * @return game field for the game of life
     */
    public static GameField gameFieldFromLifeCells(URL url) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(url.toURI()));

            Set<Coordinate> liveCells = new LinkedHashSet<>();
            int cols = lines.get(0).length();
            for (int row = 0; row < lines.size(); row++) {
                String line = lines.get(row);
                checkArgument(cols == line.length());
                for (int col = 0; col < cols; col++) {
                    if (line.charAt(col) == '*') {
                        liveCells.add(Coordinate.of(row, col));
                    }
                }
            }

            return new GameField(liveCells, lines.size(), cols);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
