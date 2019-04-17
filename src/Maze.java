import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is where we read the text file and look at the text file
 */
public class Maze {
    private int rows, cols;
    private int startRow, startCol;
    private int exitRow, exitCol;
    private char[][] currentMaze;

    /**
     * Contructor for maze
     * @param filename maze file
     * @throws Exception for fileScanner
     */
    public Maze(File filename){
        Scanner fileScan = null;
        try {
            fileScan = new Scanner(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        rows = fileScan.nextInt();
        cols = fileScan.nextInt();
        startRow = fileScan.nextInt();
        startCol = fileScan.nextInt();
        exitRow = fileScan.nextInt();
        exitCol = fileScan.nextInt();
        fileScan.nextLine();

        //Read the file and put it into a 2D array
        currentMaze = new char[rows][cols];
        for(int row=0; row<rows; row++){
            String s = fileScan.nextLine();
            for(int col=0; col<cols; col++){
                currentMaze[row][col] = s.charAt(col);
            }
        }
    }

    /**
     * Get the rows of the maze
     * @return number of rows in the maze
     */
    public int getRows(){
        return rows;
    }

    /**
     * Get the cols in the maze
     * @return number of cols in the maze
     */
    public int getCols(){
        return cols;
    }

    /**
     * Get the start cell's row
     * @return the start cell's row
     */
    public int getStartRow(){
        return startRow;
    }

    /**
     * Get the start cell's cols
     * @return the start cell's cols
     */
    public int getStartCol(){
        return startCol;
    }

    /**
     * Get the exit cell's row
     * @return the exit cell's row
     */
    public int getExitRow(){
        return exitRow;
    }

    /**
     * Get the exit cell's col
     * @return the exit cell's col
     */
    public int getExitCol(){
        return exitCol;
    }

    /**
     * Get the char at a coordinate
     * @param row position for row
     * @param col position for col
     * @return the character stored at this cell
     */
    public char getCell(int row, int col){
        return currentMaze[row][col];
    }

    /**
     * Check to see if the cell is open
     * @param row position for row
     * @param col position for col
     * @return true if the cell is open and the row and
     * coll values are valid for the given maze
     */
    public boolean openCell(int row, int col){
        if(row <= 0 || row >= rows || col <= 0 || col >= cols){
            return false;
        }
        if(currentMaze[row][col] == ' ' || currentMaze[row][col] == 'X'){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Sets the velue stored in this cell
     * @param row position for row
     * @param col position for col
     * @param newCh new character we want to include
     */
    public void setCell(int row, int col, char newCh){
        currentMaze[row][col] = newCh;
    }

    /**
     * Return the maze as a string fo output
     * @return 2D maze
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                s.append(currentMaze[row][col]);
            }
            s.append("\n");
        }
        return s.toString();
    }
}
