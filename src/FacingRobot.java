/**
 * Abstract class that extends robot
 */
public abstract class FacingRobot extends Robot {
    private direction currentFace;
    enum direction {NORTH, SOUTH, EAST, WEST }

    /**
     * Constructor
     * @param maze text maze
     */
    public FacingRobot(Maze maze){
        super(maze);
        currentFace = direction.SOUTH;
    }

    /**
     * Getter for direction
     * @return current facing of robot
     */
    public direction getDirection(){
        return currentFace;
    }

    /**
     * Setter for direction
     * @param face sets the face of the robot
     */
    public void setDirection(direction face){
        currentFace = face;
    }


}
