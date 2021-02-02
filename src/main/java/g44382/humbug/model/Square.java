package g44382.humbug.model;

import java.util.Objects;

/**
 * Square on the board. A square has a type grass or star and it's all. A square
 * doesn't know it is on the board.
 *
 * @author El Fahsi Abdessalam
 */
public class Square {

    private SquareType type;
    private boolean northWall;
    private boolean southWall;
    private boolean westWall;
    private boolean eastWall;
    /**
     * Construcor default
     */
    public Square() {
    
    }
    /**
     * Constroctor
     *
     * @param northWall boolean is false if there is not a wall in north.
     * @param southWalll boolean if false there is not a wall in south.
     * @param westWall boolean if false there is not a wall in west.
     * @param eastWall boolean if false there is not a wall in east.
     */
    public Square(boolean northWall, boolean southWalll, boolean westWall, boolean eastWall) {
        this.northWall = false;
        this.southWall = false;
        this.westWall = false;
        this.eastWall = false;
    }
    /**
     * Constructor of Square on board.
     *
     * @param type of Square.
     */
    public Square(SquareType type) {
        this.type = Objects.requireNonNull(type, "ty");
    
    }

    /**
     * check if there is a wall using a given direction.
     * 
     * @param direction is North,South,East,West.
     * @return boolean if hasWall has a wall..
     */
    public  boolean hasWall(Direction direction) {
        boolean hasWall = false;
        switch (direction) {
            case NORTH:
                hasWall = northWall;
                break;
            case SOUTH:
                hasWall = southWall;
                break;
            case EAST:
                hasWall = eastWall;
                break;
            case WEST:
                hasWall = westWall;
                break;

            default:
                throw new IllegalArgumentException("invalid direction");
        }
        return hasWall;
    }

    /**
     * Simple setter of northWall.
     *
     * @param northWall wall.
     */
    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }
    /**
     * Simple setter of southWall.
     * @param southWalll  wall.
     */
    public void setSouthWall(boolean southWalll) {
        this.southWall = southWalll;
    }
    /**
     * Simple setter of southWall.
     * @param westWall wall on west.
     */
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    /**
     * Simple setter of eastWall.
     *
     * @param eastWall west on east.
     */
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }


    /**
     * Setter of type.
     *
     * @param type of Square.
     */
    public void setType(SquareType type) {
        this.type = type;
    }

    /**
     * Simple getter of type.
     *
     * @return type of Square.
     */
    public SquareType getType() {
        return type;
    }
}
