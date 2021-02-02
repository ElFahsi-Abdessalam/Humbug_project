package g44382.humbug;

import g44382.humbug.controller.Controller;
import g44382.humbug.model.Game;
import g44382.humbug.view.text.View;
import java.util.Scanner;

/**
 * Launch the game
 *
 * @author El Fahsi Abdessalam
 */
public class Main {

    /**
     * Launch the game.
     *
     * @param args arguments from the console (no arguments expected).
     */
    public static void main(String[] args) {
        Controller controller = new Controller(new Game(), new View());
        int level = askLevel();
        controller.startGame(level);

    }

    /**
     * Ask Level for user.
     *
     * @return value of keyboard.
     */
    private static int checkLevelIsNumber() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Entrez level");
        while (!keyboard.hasNextInt()) {
            System.out.println("The value is not a number");
            keyboard.next();
        }
        return keyboard.nextInt();
    }

    /**
     * Check level is between 0 and 100.
     *
     * @return i value of level.
     */
    private static int askLevel() {
        int i = checkLevelIsNumber();
        while (i <= 0 || i > 100) {
            System.out.println("Select a level between 1 and   100  ");
            i = checkLevelIsNumber();

        }
        return i;
    }
}
