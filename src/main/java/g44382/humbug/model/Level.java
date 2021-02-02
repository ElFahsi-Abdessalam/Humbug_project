package g44382.humbug.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Load Level of game.
 *
 * @author El Fahsi Abdessalam
 */
public class Level {

    private Board board;
    private Animal[] animals;
    private int nMoves;

    /**
     * read level in file json.
     *
     * @param n intenger.
     * @return level
     * @throws IOException
     */
    private static Level readLevel(int n) throws IOException {

        var objectMapper = new ObjectMapper();
        var inputStream = Level.class.getResourceAsStream(
                "/data/level-" + n + ".json");
        var level = objectMapper.readValue(inputStream, Level.class);
        return level;
    }

    /**
     * default constructor of Level.
     */
    public Level() {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     * Getter of game board.
     *
     * @return board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter of Animal.
     *
     * @return animal.
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Getter of nMove.
     *
     * @return nMove.
     */
    public int getnMoves() {
        return nMoves;
    }

    /**
     * Constructor of Level.
     *
     * @param board board in game.
     * @param animals array of animal.
     * @param nMoves number of movements.
     */
    private Level(Board board, Animal[] animals, int nMoves) {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     *
     * @param level getter of level.
     * @return nLevel number of level.
     */
    public static Level getLevel(int level) throws IOException {
            Level nLevel = readLevel(level);
            return nLevel;
        
    }
}
