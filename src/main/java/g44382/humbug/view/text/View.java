package g44382.humbug.view.text;

import g44382.humbug.model.Animal;
import java.util.Scanner;
import g44382.humbug.model.Board;
import g44382.humbug.model.Bumbelbee;
import g44382.humbug.model.Butterfly;
import g44382.humbug.model.Direction;
import g44382.humbug.model.Grasshopper;
import g44382.humbug.model.Ladybird;
import g44382.humbug.model.Position;
import g44382.humbug.model.Snail;
import g44382.humbug.model.Spider;
import g44382.humbug.model.Square;
import g44382.humbug.model.SquareType;

/**
 * View displays the game board.
 *
 * check the keyboard entries.
 *
 * @author El Fahsi Abdessalam
 */
public class View implements InterfaceView {

    /**
     *
     * Display the board according to the content of the board and display the
     * games.
     *
     * @param board Table board.
     * @param animals table of animals.
     */
    @Override
    public void displayBoard(Board board, Animal... animals) {
        /**
         * Displays the game board and displays the animals.
         *
         */
        int sep_Row = 5;
        int multiplicator = 4;
        int middleRow = 2;
        int size_SquareRow = 3;

        String[][] tab = new String[sep_Row + multiplicator * (board.getNbRow() - 1)][board.getNbColumn()];
        counter(tab);
        System.out.println("");
        for (int row = 0, k = 0; row < sep_Row + multiplicator * (board.getNbRow() - middleRow); row = row + multiplicator, k++) {
            for (int column = 0; column < board.getNbColumn(); column++) {
                Position position = new Position(k, column);

                boolean foundAnimal = foundAnimal(position, animals);
                displayLigneSpace(board, position, tab, row, column, middleRow, size_SquareRow);

                if (board.isInside(position)) {
                    tab[row + 1][column] = tab[row + size_SquareRow][column] = displayEmpty();
                    wallNorhSouth(board, position, tab, column, row, middleRow, size_SquareRow);

                    if (board.getSquareType(position) == SquareType.STAR) {
                        tab[row + middleRow][column] = displayStar();
                        displayStarWall(board, position, tab, column, row, middleRow);

                    } else if (board.getSquareType(position) == SquareType.GRASS) {
                        tab[row + middleRow][column] = displayEmpty();

                        if (foundAnimal) {
                            displayAnimalWall(board, position, tab, column, row, middleRow, animals);
                        } else {
                            displayWall(board, position, tab, column, row, middleRow);

                        }

                    }
                }
                isInsideAndLine(board, position, tab, column, row, multiplicator, k);
            }
        }
        displayArray(tab);
    }

    /**
     * Display case is empty.
     *
     * @return String.
     */
    private String displayEmpty() {
        return (TerminalColor.BG_GREEN
                + "\033[34m|      |\033[0m" + TerminalColor.DEFAULT);
    }

    /**
     * Display case empty with star.
     *
     * @return String.
     */
    private String displayStar() {
        return (TerminalColor.BG_GREEN + "\033[34m|  *   |\033[0m"
                + TerminalColor.DEFAULT);
    }

    /**
     * Display ligne if is inside and Space in display board if is not inside.
     *
     * @param board board of game.
     * @param position position on game.
     * @param tab array of tab.
     * @param row constant i.
     * @param column constant j.
     * @param middleRow constant c.
     * @param size_SquareRow constant d.
     */
    private void displayLigneSpace(Board board, Position position, String[][] tab, int row, int column, int middleRow, int size_SquareRow) {
        if (row == 0 && board.isInside(position)) {
            tab[row][column] = TerminalColor.BG_GREEN + "--------"
                    + TerminalColor.DEFAULT;
        } else if (row == 0) {
            tab[row][column] = "        ";
        }
        for (int index = 0; index < size_SquareRow; index++) {

        }
        if (!board.isInside(position)) {
            tab[row + 1][column] = tab[row + middleRow][column] = tab[row + size_SquareRow][column] = ("        ");
        }
    }

    /**
     * Display the case if there a direction.
     *
     * @param board of the game.
     * @param position in game.
     * @param tab array of tab.
     * @param column constant for array.
     * @param row constant for array.
     * @param middleRow constant for array.
     */
    private void displayStarWall(Board board, Position position, String[][] tab, int column, int row, int middleRow) {
        if (squarePosition(board, position).hasWall(Direction.EAST) && squarePosition(board, position).hasWall(Direction.WEST)) {
            tab[row + middleRow][column] = WALL_EAST_WEST;
        } else if (squarePosition(board, position).hasWall(Direction.EAST)) {

            tab[row + middleRow][column] = WALL_EAST;
        } else if (squarePosition(board, position).hasWall(Direction.WEST)) {
            tab[row + middleRow][column] = WALL_WEST;
        }
    }
    /**
     * String wall east with star.
     */
    private static final String WALL_EAST = (TerminalColor.BG_GREEN
            + "\033[34m|  *  \u00A4|\033[0m" + TerminalColor.DEFAULT);
    /*
    *String wall west with star.
     */
    private static final String WALL_WEST = (TerminalColor.BG_GREEN
            + "\033[34m|\u00A4  *  |\033[0m" + TerminalColor.DEFAULT);

    /**
     * String wall west and easr with star.
     */
    private static final String WALL_EAST_WEST = (TerminalColor.BG_GREEN
            + "\033[34m|\u00A4 *  \u00A4|\033[0m" + TerminalColor.DEFAULT);

    /**
     * Display a number of row.
     *
     * @param tab array .
     */
    private void counter(String[][] tab) {
        System.out.print("     ");
        for (int l = 0; l < tab[0].length; l++) {
            System.out.print("|  " + l + "   |");
        }
    }

    /**
     * Display ----- in board.
     *
     * @param board board in game.
     * @param position position in board.
     * @param tab array of tab.
     * @param column constant for array.
     * @param row constant for array.
     * @param multiplicateur constant for array.
     * @param k constant for array.
     */
    private void isInsideAndLine(Board board, Position position, String[][] tab, int column, int row, int multiplicateur, int k) {
        if (board.isInside(position) || board.isInside(new Position(k + 1, column))) {
            tab[row + multiplicateur][column] = LIGN;
        } else {
            tab[row + multiplicateur][column] = "        ";
        }
    }
    /**
     * String lign in board.
     */
    private static final String LIGN = TerminalColor.BG_GREEN + "--------"
            + TerminalColor.DEFAULT;

    /**
     * Display case if the direction is North or South.
     *
     * @param board of the game.
     * @param position in game.
     * @param tab array of table.
     * @param column constant for array.
     * @param row constant for array.
     * @param cconstant for array.
     * @param size_SquareRow constant for array.
     */
    private void wallNorhSouth(Board board, Position position, String[][] tab, int column, int row, int middleRow, int size_SquareRow) {
        if (squarePosition(board, position).hasWall(Direction.NORTH)) {
            tab[row + 1][column] = tab[row + middleRow][column] = WALL_NORTH;
        }
        if (squarePosition(board, position).hasWall(Direction.SOUTH)) {
            tab[row + middleRow][column] = tab[row + size_SquareRow][column] = WALL_SOUTH;
        }
    }
    /**
     * String wall in north.
     */
    private static final String WALL_NORTH = (TerminalColor.BG_GREEN
            + "\033[34m|  \u00A4   |\033[0m" + TerminalColor.DEFAULT);

    /**
     * String wall sou1th.
     */
    private static final String WALL_SOUTH = (TerminalColor.BG_GREEN
            + "\033[34m|  \u00A4   |\033[0m" + TerminalColor.DEFAULT);

    /**
     * Display the case if there a direction.
     *
     * @param board of the game.
     * @param position in game.
     * @param tab array of tab.
     * @param column constant for array.
     * @param row constant for array. displayWall
     * @param middleRow constant for array.
     */
    private void displayWall(Board board, Position position, String[][] tab, int column, int row, int middleRow) {
        if (squarePosition(board, position).hasWall(Direction.EAST)
                && squarePosition(board, position).hasWall(Direction.WEST)) {
            tab[row + middleRow][column] = HASWALL_EAST_WEST;
        } else if (squarePosition(board, position).hasWall(Direction.EAST)) {
            tab[row + middleRow][column] = HASWALL_EAST;
        } else if (squarePosition(board, position).hasWall(Direction.WEST)) {
            tab[row + middleRow][column] = HASWALL_WEST;
        }
    }
    /**
     * String hasWall west and east.
     */
    private static final String HASWALL_EAST_WEST = (TerminalColor.BG_GREEN
            + "\033[34m|\u00A4    \u00A4|\033[0m" + TerminalColor.DEFAULT);
    /**
     * String hasWall east.
     */
    private static final String HASWALL_EAST = (TerminalColor.BG_GREEN
            + "\033[34m|     \u00A4|\033[0m" + TerminalColor.DEFAULT);

    /**
     * String hasWall west.
     */
    private static final String HASWALL_WEST = (TerminalColor.BG_GREEN
            + "\033[34m|\u00A4     |\033[0m" + TerminalColor.DEFAULT);

    /**
     * Display the case if there a direction and animal.
     *
     * @param board of the game.
     * @param position in game.
     * @param tab array of tab.
     * @param column constant for array.
     * @param row constant for array.
     * @param middleRow constant for array.
     */
    private void displayAnimalWall(Board board, Position position, String[][] tab, int column, int row, int middleRow, Animal... animals) {

        tab[row + middleRow][column] = EMPTY_WEST + typeOfAnimal(position, animals) + EMPTY_EAST;
        if (squarePosition(board, position).hasWall(Direction.EAST)
                && squarePosition(board, position).hasWall(Direction.WEST)) {
            tab[row + middleRow][column]
                    = WALL_WEST_ANIMAL + typeOfAnimal(position, animals) + WALL_EAST_ANIMAL;
        } else if (squarePosition(board, position).hasWall(Direction.EAST)) {
            tab[row + middleRow][column] = WEAST_WALL1 + typeOfAnimal(position, animals) + EAST_WALL1;
        } else if (squarePosition(board, position).hasWall(Direction.WEST)) {
            tab[row + middleRow][column] = tab[row + 2][column] = WEST_WALL2 + typeOfAnimal(position, animals) + EAST_WALL2;
        }
    }
    private static final String EMPTY_WEST = TerminalColor.BG_GREEN + "\033[34m|\033[31m  ";
    private static final String EMPTY_EAST = "\033[0m" + TerminalColor.DEFAULT + TerminalColor.BG_GREEN + "  \033[34m|" + TerminalColor.DEFAULT;
    private static final String WALL_WEST_ANIMAL = TerminalColor.BG_GREEN + "\033[34m|\u00A4 \033[31m";
    private static final String WALL_EAST_ANIMAL = "\033[0m" + TerminalColor.DEFAULT + TerminalColor.BG_GREEN + "\033[34m \u00A4|" + TerminalColor.DEFAULT;
    private static final String WEAST_WALL1 = TerminalColor.BG_GREEN + "\033[34m|  \033[31m";
    private static final String EAST_WALL1 = "\033[0m" + TerminalColor.DEFAULT + TerminalColor.BG_GREEN + " \033[34m\u00A4|" + TerminalColor.DEFAULT;
    private static final String WEST_WALL2 = TerminalColor.BG_GREEN + "\033[34m|\u00A4 \033[31m";
    private static final String EAST_WALL2 = "\033[0m" + TerminalColor.DEFAULT + TerminalColor.BG_GREEN + "  \033[34m|" + TerminalColor.DEFAULT;
    /**
     * String of type of animal.
     *
     * @param position position in board.
     * @param animals array of animals.
     * @return String of type of animal.
     */
    private String typeOfAnimal(Position position, Animal... animals) {
        for (Animal animal : animals) {
            if (animal.getPositionOnBoard().equals(position)) {
                if (animal.getClass().equals(Spider.class)) {
                    return "SP";
                }
                if (animal.getClass().equals(Snail.class)) {
                    return "SN";
                }
                if (animal.getClass().equals(Grasshopper.class)) {
                    return "GR";
                }
                if (animal.getClass().equals(Bumbelbee.class)) {
                    return "BB";
                }
                if (animal.getClass().equals(Ladybird.class)) {
                    return "LB";
                }
                if (animal.getClass().equals(Butterfly.class)) {
                    return "BT";
                }
            }
        }
        return null;
    }
    /**
     * found animal.
     *
     * @param position check the position of the animal.
     * @param animals array of animal.
     * @return boolean is true if find animals.
     */
    private boolean foundAnimal(Position position, Animal... animals) {
        int i = 0;
        boolean foundAnimal = false;
        while (i < animals.length && !foundAnimal) {
            if (animals[i].getPositionOnBoard().equals(position)) {
                foundAnimal = true;
            }
            i++;
        }
        return foundAnimal;
    }
    /**
     * Display game board.
     *
     * @param mainTab array of board.
     */
    private void displayArray(String[][] mainTab) {
        int row = 0;
        for (int column = 0; column < mainTab.length; column++) {
            String[] counterCol = mainTab[column];
            if (column % 4 != 2) {
                System.out.print("     ");
            }
            if (column % 4 == 2) {
                System.out.print(" " + row + "   ");
                row++;
            }
            for (String tab3 : counterCol) {
                System.out.print(tab3);
            }
            System.out.println();
        }
    }
    /**
     * Display error message.
     *
     * @param message message with error.
     */
    @Override
    public void displayError(String message) {
        System.out.println(message);
    }
    /**
     * ask the user for a row and a column.
     *
     * @return positon(row,column).
     */
    @Override
    public Position askPosition() {
        System.out.println("Introduce your row");
        int row = askPositiveNb();
        System.out.println("Introduce your column");
        int column = askPositiveNb();
        Position position = new Position(row, column);
        return position;
    }
    /**
     * method that checks if the value is a number.
     *
     * @return number.
     */
    private int askNb() {
        Scanner keyboard = new Scanner(System.in);
        while (!keyboard.hasNextInt()) {
            keyboard.next();
            System.out.println("The value is not a number");
        }
        return keyboard.nextInt();
    }

    /**
     * method that checks if the value is positive.
     *
     * @return value positive of keyboard.
     */
    private int askPositiveNb() {
        int i = askNb();
        while (i < 0) {
            System.out.println("The value is not positive");
            i = askNb();
        }
        return i;
    }
    /**
     *
     * ask direction and return the direction.
     *
     * @return direction (north,south,west,east).
     */
    @Override
    public Direction askDirection() {
        Scanner keyboard = new Scanner(System.in);
        Direction direction1 = null;
        while (direction1 == null) {
            System.out.println("Give a direction :NORTH,SOUTH,WEST,EAST");
            String direction = keyboard.nextLine().toUpperCase();
            switch (direction) {
                case "NORTH":
                    direction1 = Direction.NORTH;
                    break;
                case "SOUTH":
                    direction1 = Direction.SOUTH;
                    break;
                case "EAST":
                    direction1 = Direction.EAST;
                    break;
                case "WEST":
                    direction1 = Direction.WEST;
                    break;
                default:
                    direction1 = null;
                    break;
            }
        }

        return direction1;
    }
    /**
     * print Remaining move.
     *
     * @param remaining remaining move.
     */
    public void displayRemainingMoves(int remaining) {
        System.out.println("nMove: " + remaining);
    }
    /**
     * Display number of level.
     *
     * @param nLevel in game.
     */
    @Override
    public void displayLevel(int nLevel) {
        System.out.println("Level: " + nLevel);
    }
    /**
     * Print a message.
     */
    @Override
    public void displayHumbug() {
        System.out.println("*****Welcome to humbug*****");
    }
    /**
     *
     * @param board board on game.
     * @param position position on board.
     * @return square with position.
     */
    private Square squarePosition(Board board, Position position) {
        return board.getSquares()[position.getRow()][position.getColumn()];
    }
}
