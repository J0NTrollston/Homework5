import java.util.Random;

/**
 * This class extends from robot and is the random robot
 */
public class RandomRobot extends Robot{
    private Maze currentMaze;
    private int facingRobot;

    /**
     * Constructor
     * @param maze maze from text file
     */
    public RandomRobot(Maze maze){
        super(maze);
        currentMaze = maze;
    }

    /**
     * Sets the current row for robot
     * @param row given row
     */
    public void setCurrentRow(int row){
        super.setCurrentRow(row);
    }

    /**
     * Sets the current column for robot
     * @param col given column
     */
    public void setCurrentCol(int col){
        super.setCurrentCol(col);
    }

    /**
     * Returns the current row
     * @return row
     */
    public int getCurrentRow(){ return super.getCurrentRow();}

    /**
     * Returns the current col
     * @return col
     */
    public int getCurrentCol(){ return super.getCurrentCol();}

    /**
     * Choose the move direction
     * @return number which is the direction
     */
    public int chooseMoveDirection(){
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        if(randomNumber ==2){
            if(currentMaze.openCell(getCurrentRow(), getCurrentCol()-1)) {
                facingRobot = 3;
                return 3;
            }else if(currentMaze.openCell(getCurrentRow()+1,getCurrentCol())){
                facingRobot = 2;
                return 2;
            }else if(currentMaze.openCell(getCurrentRow(),getCurrentCol()+1)){
                facingRobot = 1;
                return 1;
            }else{
                facingRobot = 0;
                return 0;
            }
        }else if(randomNumber ==3){
            if(currentMaze.openCell(getCurrentRow()-1, getCurrentCol())) {
                facingRobot = 0;
                return 0;
            }else if(currentMaze.openCell(getCurrentRow(),getCurrentCol()-1)){
                facingRobot = 3;
                return 3;
            }else if(currentMaze.openCell(getCurrentRow()+1,getCurrentCol())){
                facingRobot =2;
                return 2;
            }else {
                facingRobot =0;
                return 1;
            }
        } else if (randomNumber == 0) {
            if(currentMaze.openCell(getCurrentRow(), getCurrentCol()+1)) {
                facingRobot =1;
                return 1;
            }else if(currentMaze.openCell(getCurrentRow()-1,getCurrentCol())){
                facingRobot = 0;
                return 0;
            }else if(currentMaze.openCell(getCurrentRow(),getCurrentCol()-1)){
                facingRobot = 3;
                return 3;
            }else {
                facingRobot =2;
                return 2;
            }
        }else if (randomNumber == 1){
            if(currentMaze.openCell(getCurrentRow()+1, getCurrentCol())) {
                facingRobot =2;
                return 2;
            }else if(currentMaze.openCell(getCurrentRow(),getCurrentCol()+1)){
                facingRobot =1;
                return 1;
            }else if(currentMaze.openCell(getCurrentRow()-1,getCurrentCol())){
                facingRobot =0;
                return 0;
            }else {
                facingRobot =3;
                return 3;
            }
        }
        return Integer.parseInt(null);
    }

    /**
     * Move to the given direction one space
     * @param direction int direction
     * @return true if it worked
     */
    public boolean move(int direction){
        //use the 2D array to move
        boolean valid = false;
        switch(direction){
            case 0:
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol(), ' ');
                currentMaze.setCell(super.getCurrentRow()-1, super.getCurrentCol(),'M');
                setCurrentCol(super.getCurrentCol());
                setCurrentRow(super.getCurrentRow()-1);
                valid = true;
                break;
            case 1:
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol(), ' ');
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol()+1,'M');
                setCurrentCol(super.getCurrentCol()+1);
                setCurrentRow(super.getCurrentRow());
                valid = true;
                break;
            case 2:
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol(), ' ');
                currentMaze.setCell(super.getCurrentRow()+1, super.getCurrentCol(),'M');
                setCurrentCol(super.getCurrentCol());
                setCurrentRow(super.getCurrentRow()+1);
                valid = true;
                break;
            case 3:
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol(), ' ');
                currentMaze.setCell(super.getCurrentRow(), super.getCurrentCol()-1,'M');
                setCurrentCol(super.getCurrentCol()-1);
                setCurrentRow(super.getCurrentRow());
                valid = true;
                break;
        }
        return valid;
    }

    /**
     * Find out if maze is solved
     * @return true if solved
     */
    public boolean solved(){
        if(getCurrentCol() == currentMaze.getExitCol() && getCurrentRow() == currentMaze.getExitRow()){
            return true;
        }else{
            return false;
        }
    }
}