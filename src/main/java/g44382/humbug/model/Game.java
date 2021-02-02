package g44382.humbug.model;

import java.util.ArrayList;
import java.util.List;
import g44382.humbug.view.text.InterfaceView;
import java.io.IOException;

/**
 * The game class allows you to load the levels and be able to move the animals
 * on the board. The game is over when there is more star.
 *
 * @author El Fahsi Abdessalam.
 */
public class Game implements Model {

    private Board board;
    private Animal[] animals;
    private int remainingMoves;

    /**
     * board represents the game board.
     *
     * @return board board of the game.
     */
    @Override
    public Board getBoard() {

        return board;
    }

    /**
     *
     * representing the animals on the set.
     *
     * @return table of animal.
     */
    @Override
    public Animal[] getAnimals() {
        if (animalPresent(List.of(animals)).length != 0) {
            this.animals = animalPresent(List.of(animals));
        }
        return this.animals;
    }

    /**
     * initialize the game board and the animals for this first level.
     *
     * @param level of the game.
     */
    @Override
    public void startLevel(int level) throws IOException {
        board = Level.getLevel(level).getBoard();
        animals = Level.getLevel(level).getAnimals();
        remainingMoves = Level.getLevel(level).getnMoves();

    }

    /**
     *
     * a levelIsOver method as announced which specifies if the level is
     * finished. A level is completed if all animals are on a star box.
     *
     * @return boolean is the level is over.
     */
    private boolean levelIsOver() {
        int i = 0;
        while (i < getAnimals().length && getAnimals()[i].isOnStar()) {
            i++;
        }
        return i == getAnimals().length;

    }

    /**
     * Check if we can move.
     *
     * @param position position position on board.
     * @param direction direction is NORTH SOUTH EAST WEST.
     */
    @Override
    public void move(Position position, Direction direction) {
        if (position == null || direction == null) {
            throw new IllegalArgumentException("The position and the direction"
                    + "is null");
        }
        if (getLevelStatus() == LevelStatus.FAIL) {
            throw new IllegalStateException("End of game");
        }
        int i = 0;
        boolean checkMove = true;

        while (i < getAnimals().length && checkMove) {

            if (position.equals(getAnimals()[i].getPositionOnBoard())) {
                Position check = getAnimals()[i].move(board, direction, getAnimals());
                remainingMoves--;
                //enlever le throw new illegal
                if (getLevelStatus() == LevelStatus.FAIL) {
                    throw new IllegalStateException("You don't have remaining move");
                }
                if (check == null) {
                    throw new NullPointerException("Animal comes out of the games");
                }
                checkMove = false;
            }
            i++;
        }
    }

    /**
     *
     * @param list List of animal.
     * @return animal array of animal.
     */
    private Animal[] animalPresent(List<Animal> list) {
        List<Animal> animalPresent = new ArrayList<>();
        
        for (Animal animal : list) {
            if (!animal.isOnStar()) {
                animalPresent.add(animal);
            }
        }
      
        return  animalPresent.stream().toArray(x-> new Animal[x]);
    }



    /**
     * Remaining Moves.
     *
     * @return remaining moves.
     */
    @Override
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * Check level statut is the game is in progross or fail or not started.
     *
     * @return level statut.
     */
    @Override
    public LevelStatus getLevelStatus() {
        if (!levelIsOver() && getRemainingMoves() == 0) {
            return LevelStatus.FAIL;
        }
        if (!levelIsOver()) {
            return LevelStatus.IN_PROGRESS;
        }

        if (levelIsOver()) {
            return LevelStatus.WIN;
        }

        if (levelIsOver() && getRemainingMoves() == 0) {
            return LevelStatus.WIN;
        }
        return LevelStatus.NOT_STARTED;
    }
}
