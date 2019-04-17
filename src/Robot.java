/**
 * Is the abstract class for the robots, is the super class of any other specific robots
 */
public abstract class Robot {
    private Maze currentMaze;
    private int currentRow;
    private int currentCol;

    /**
     * Get a reference of the maze
     * @param maze text file given
     */
    public Robot(Maze maze){
        if(maze!=null){
            currentMaze = maze;
            currentRow = maze.getStartRow();
            currentCol = maze.getStartCol();
        }else{
            System.out.println("Maze is null!");
            System.exit(0);
        }
        maze.setCell(maze.getStartRow(),maze.getStartCol(),'R');
    }

    /**
     * Setter for the maze
     * @param inMaze maze
     */
    public void setMaze(Maze inMaze){
        currentMaze = inMaze;
    }

    /**
     * Getter for current maze
     * @return the current maze
     */
    public Maze getCurrentMaze(){
        return currentMaze;
    }

    /**
     * Robot Constructor
     */
    public Robot(){}

    /**
     * Abstract move direction
     * @return int direction
     */
    abstract public int chooseMoveDirection();

    /**
     * Abstract move direction
     * @param direction int direction to move
     * @return true if worked
     */
    abstract public boolean move(int direction);

    /**
     * Sets the current row for robot
     * @param row given row
     */
    public void setCurrentRow(int row){
        currentRow = row;
    }

    /**
     * Sets the current column for robot
     * @param col given column
     */
    public void setCurrentCol(int col){
        currentCol = col;
    }

    /**
     * Get the current row
     * @return row
     */
    public int getCurrentRow(){
        return currentRow;
    }

    /**
     * Find the current column
     * @return current col
     */
    public int getCurrentCol(){
        return currentCol;
    }

    /**
     * Check to see if the robot has reached the exit
     * @return true if maze is solved
     */
    public boolean solved(){
        if(getCurrentCol()== currentMaze.getExitCol() && getCurrentRow() == currentMaze.getExitRow()) {
            return true;
        }else{
            return false;
        }
    }



}
