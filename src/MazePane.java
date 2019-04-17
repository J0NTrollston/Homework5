import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * This will be to paint and display the pane
 */
public class MazePane extends Pane{
    private Maze maze;
    private Robot currentRobot;
    private final int HEIGHT = 20;
    private final int WIDTH = 20;

    /**
     * Setter for current robot
     * @param currentRobot robot
     */
    public void setCurrentRobot(Robot currentRobot){this.currentRobot = currentRobot;}

    /**
     * Getter for current robot
     * @return current robot
     */
    public Robot getCurrentRobot(){return currentRobot;}

    /**
     * Setter for maze
     * @param maze maze
     */
    public void setMaze(Maze maze){this.maze = maze;}

    /**
     * Getter for maze
     * @return maze
     */
    public Maze getMaze(){return maze;}

    /**
     * Getter for the height
     * @return height
     */
    public int getHEIGHT(){return HEIGHT;}

    /**
     * Getter for the width
     * @return width
     */
    public int getWIDTH(){return WIDTH;}

    /**
     * Paints the pane to show the robot and the maze
     */
    public void paint(){
    Rectangle rectangles;
    getChildren().clear();
        for(int row = 0; row < HEIGHT*maze.getRows(); row = row + 20){
            for(int col = 0; col < WIDTH*maze.getCols(); col = col + 20){
                rectangles = new Rectangle(col,row,WIDTH,HEIGHT);
                getChildren().add(rectangles);
                if(maze.getCell(row/20,col/20) == '*'){
                    //black box
                    rectangles.setFill(Color.BLACK);
                }else if(maze.getCell(row/20,col/20) == ' ' || maze.getCell(row/20,col/20) == 'X' ||maze.getCell(row/20,col/20) == 'S') {
                    //white box
                    rectangles.setFill(Color.WHITE);
                }else if(maze.getCell(row/20,col/20) == 'B'){
                    rectangles.setFill(Color.BLUE);
                }else{
                    //paint white background then robot circle
                    rectangles.setFill(Color.WHITE);
                    Circle circle = new Circle(HEIGHT/2);
                    circle.setFill(Color.GRAY);
                    circle.relocate(col,row);
                    getChildren().add(circle);
                }
            }
        }
    }

    /**
     * Resets the maze so no blue blocks are placed
     */
    public void resetMaze(){
        Rectangle rectangles;
        getChildren().clear();
        for(int row = 0; row < HEIGHT*maze.getRows(); row = row + 20){
            for(int col = 0; col < WIDTH*maze.getCols(); col = col + 20){
                rectangles = new Rectangle(col,row,WIDTH,HEIGHT);
                getChildren().add(rectangles);
                if(maze.getCell(row/20,col/20) == ' ' || maze.getCell(row/20,col/20) == 'X' ||maze.getCell(row/20,col/20) == 'S') {
                    //white box
                    rectangles.setFill(Color.WHITE);
                }else if(maze.getCell(row/20,col/20) == 'B'){
                    rectangles.setFill(Color.WHITE);
                    maze.setCell(row/20,col/20, ' ');
                }
            }
        }
    }
}
