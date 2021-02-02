package g44382.humbug.controller;

import g44382.humbug.model.Animal;
import g44382.humbug.model.Direction;
import g44382.humbug.model.LevelStatus;
import g44382.humbug.model.Model;
import g44382.humbug.model.Position;
import g44382.humbug.view.text.InterfaceView;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author El Fahsi Abdessalam
 *
 * The controller class allows you to start a level and tell if a level is
 * finished.
 */
public class Controller {

    private Model game;
    private InterfaceView view;

    /**
     * Constructor of Controller.
     *
     * @param game model of game.
     * @param view of the game.
     */
    public Controller(Model game, InterfaceView view) {
        this.game = Objects.requireNonNull(game, "The game is not initialized");
        this.view = Objects.requireNonNull(view, "The view is nit initialized");

    }

    /**
     * Start game method he initialized all the animals on the board initialized
     * the board and display.
     *
     * @param nLevel number of level.
     */
    public void startGame(int nLevel) {
        view.displayHumbug();
        while (nLevel <= 100) {
            try {

                game.startLevel(nLevel);
                do {

                    view.displayBoard(game.getBoard(), game.getAnimals());
                    view.displayRemainingMoves(game.getRemainingMoves());

                    Position pos = view.askPosition();
                    while (!positionValid(pos, game.getAnimals())) {
                        view.displayError("Position is not in board");
                        pos = view.askPosition();
                    }
                    Direction dir = view.askDirection();
                    game.move(pos, dir);
                } while (game.getLevelStatus() != LevelStatus.WIN);
                view.displayBoard(game.getBoard(), game.getAnimals());
                nLevel++;
                view.displayLevel(nLevel);
            } catch (NullPointerException | IllegalStateException | ArrayIndexOutOfBoundsException | IllegalArgumentException | IOException e) {
                view.displayError(e.getMessage() + "\n");
            }
        }
    }

    /**
     * Check position in board is valid.
     *
     * @param position position on board.
     * @param animals array of animals.
     * @return boolean position.
     */
    private boolean positionValid(Position position, Animal... animals) {
        int i = 0;
        boolean positionValide = false;
        while (i < animals.length && !positionValide) {
            positionValide = animals[i].getPositionOnBoard().equals(position);
            i++;
        }
        return positionValide;
    }
}
