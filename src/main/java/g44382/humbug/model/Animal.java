package g44382.humbug.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @Type(value = Bumbelbee.class),
    @Type(value = Grasshopper.class),
    @Type(value = Ladybird.class),
    @Type(value = Butterfly.class),
    @Type(value = Snail.class),
    @Type(value = Spider.class),})
/**
 * The animals move around on the game board.
 *
 * The animals know where they are in the game board.
 *
 * Animals don't know if it'son a star.
 *
 * @author El Fahsi Abdessalam.
 *
 */
public abstract class Animal {

    private Position positionOnBoard;
    private boolean onStar;

    /**
     * Constructor default.
     */
    public Animal() {

    }

    /**
     * Constructor of Animal.
     *
     * @param position of animal in board.
     */
    public Animal(Position position) {
        this.positionOnBoard = position;
        this.onStar = false;
    }

    /**
     *
     * Getter position of the animal on the board.
     *
     * @return position on board.
     */
    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Return boolean value of onStar if animal is in the star box.
     *
     * @return value of onStar.
     */
    public boolean isOnStar() {
        return onStar;
    }

    /**
     * abstract method that must be defined with board, direction and animal.
     *
     * @param board board game board.
     * @param direction direction of Array.
     * @param animal table of animal.
     * @return Position position in board.
     */
    public abstract Position move(Board board, Direction direction,
            Animal... animal);

    /**
     * Setter of positionOnBoard.
     *
     * @param positionOnBoard is the position on board.
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * Setter of OnStar.
     *
     * @param onStar boolean .
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    /**
     * Check position of animal.
     *
     * @param position position in board.
     * @param animals table of animals.
     * @return true if found animal.
     */
    protected boolean checkAnimalPosition(Position position, Animal... animals) {
        int i = 0;
        boolean foundAnimal = true;
        while (i < animals.length && foundAnimal) {
            foundAnimal = isOnPosition(i, position, animals);
            i++;
        }
        return foundAnimal;
    }

    /**
     *
     * @param i constante.
     * @param position on board.
     * @param animals array of animal.
     * @return boolean if positionOnboard equals position.
     */
    protected boolean isOnPosition(int i, Position position, Animal... animals) {
        return !animals[i].getPositionOnBoard().equals(position);
    }

    /**
     * Check the position on board if is inside.
     *
     * @param board board of the game.
     * @param position position of the animal.
     * @return true if inside.
     */
    protected boolean isInside(Board board, Position position) {
        return board.isInside(position);
    }

    /**
     * check if the position is inside and grass and if there are no animals on
     * the space.
     *
     * @param board of the game.
     * @param position next position of the animal.
     * @param animals table of animals.
     * @return true if is inside and if the box is grass and if there are no
     * animals in the next box.
     */
    protected boolean isInsideAndGrass(Board board, Position position,
            Animal... animals) {
        return isInside(board, position) && isGrass(board, position) && checkAnimalPosition(position, animals);
    }

    /**
     * Check if Squaretype position in board is a GRASS.
     *
     * @param board board in game.
     * @param position in game.
     * @return true is GRASS.
     */
    protected boolean isGrass(Board board, Position position) {
        return board.getSquareType(position) == SquareType.GRASS;
    }

    /**
     * Check if the position is inside and star.
     *
     * @param board board the game.
     * @param position next position of the animal.
     * @param animals table of animals.
     * @return true if the box is Star and is inside.
     */
    protected boolean isInsideAndStar(Board board, Position position,
            Animal... animals) {
        return isInside(board, position)
                && isStar(board, position);
    }

    /**
     * Check if Squaretype position in board is a STAR.
     *
     * @param board board in game.
     * @param position in game.
     * @return true is STAR.
     */
    protected boolean isStar(Board board, Position position) {
        return board.getSquareType(position) == SquareType.STAR;
    }

    /**
     *
     * Check if there is a wall.
     *
     * @param board on game.
     * @param direction NORTH SOUTH EAST WEST.
     * @return returns true if there is a wall.
     */
    protected boolean hasWall(Board board, Direction direction) {
        return positionOnBoard(board).hasWall(direction);
    }

    /**
     * @param board in game
     * @return board getsquare
     */
    protected Square positionOnBoard(Board board) {
        return board.getSquares()[positionOnBoard.getRow()][positionOnBoard.getColumn()];
    }

    /**
     * Check if there a wall opposition.
     *
     * @param position on board.
     * @param board game board.
     * @param direction direction NORTH SOUTH EAST WEST.
     * @return boolean true if has a wall opposite.
     */
    protected boolean hasWallOpposite(Position position, Board board, Direction direction) {
        return isInside(board, position) && squarePosition(board, position).hasWall(direction.opposite());
    }

    /**
     *
     * @param board board on game.
     * @param position position on board.
     * @return square with position.
     */
    protected Square squarePosition(Board board, Position position) {
        return board.getSquares()[position.getRow()][position.getColumn()];
    }

    /**
     * Check is a wall in a direction.
     *
     * @param board of the game.
     * @param direction NORTH SOUTH EAST WEST.
     * @param position of the game.
     * @return boolean if has a wall in a direction.
     */
    protected boolean hasWallDirection(Board board, Direction direction, Position position) {
        return isInside(board, position) && squarePosition(board, position).hasWall(direction);
    }

    /**
     * Move animal in board.
     *
     * @param board board in game.
     * @param position in game.
     * @param direction NORTH SOUTH EAST WEST.
     * @param animals array of animal.
     * @return position NextPosition in board.
     */
    protected Position checkAnimal(Board board, Position position, Direction direction, Animal... animals) {

        while (!checkAnimalPosition(position, animals)) {
            position = position.next(direction);
        }

        return position;
    }

    /**
     *
     * Move animal in board.
     *
     * @param board board in game.
     * @param positionNext next Position.
     * @param position position position.
     * @param checkAnimalPosition check if animal is free.
     * @param animals array of animal.
     * @return positioNext.
     */
    protected Position moveGeneral(Board board, Position positionNext, Position position, boolean checkAnimalPosition, Animal... animals) {

        if (board.isInside(positionNext)) {
            if (isInsideAndGrass(board, positionNext, animals)) {
                setPositionOnBoard(positionNext);
                return positionNext;
            }
            if (isInsideAndStar(board, positionNext, animals)) {
                setOnStar(true);
                setPositionOnBoard(positionNext);
                board.setOnGrass(positionNext);
                return getPositionOnBoard();
            }
        } else {
            setPositionOnBoard(null);
            return null;
        }

        return positionNext;
    }

   
   

}
