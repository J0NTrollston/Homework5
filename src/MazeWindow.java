import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.*;
import java.io.File;

/**
 * @author Brandon Ramos
 * Resources: Learning Commons tutor Alex S. and Trenton
 *
 * This program simulates a robot getting through a maze.
 *	Unlike tic-tac-toe, where the user is the player, the robot moves
autonomously through the maze.
 * 	We intentionally share the memory location of the maze with the robot.
Otherwise the robot would not know how to plan and make its moves.

The maze is stored in a text file that will be entered at runtime.
The layout of each maze file will contain:
�	In the first line:  two integers (the number of rows and columns, respectively,  in the maze)
�	In the second line:  two integers (the row and column locations, respectively, of the Start cell
�	In the third line:  two integers (the row and column locations, respectively, of the Exit cell
�	Each line thereafter will contain characters appearing in one row of the maze.

 * Created by Sherri Harms
 * built on top of Eddy's UW-Parkside solution
 *
 * Multiple Choice
 * 1.)A
 * 2.)D
 * 3.)C
 * 4.)B
 * 5.)C
 * 6.)D
 * 7.)A
 * 8.)C
 * 9.)D
 * 10.)D
 * 11.)B
 * 12.)B
 * 13.)B
 * 14.)ABD
 */

public class MazeWindow extends Application {
    private File result;
    private Label title;
    private Maze maze;
    private File file;
    private Robot currentRobot;
    private double HEIGHT = 20;
    private double WIDTH = 20;
    private MazePane pane;
    private Scene scene;
    private Timeline timeline;
    private Menu fileMenu;
    private Menu mazeMenu;
    private Menu robotMenu;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){

        //set the title of the application
        title = new Label("Homework 4: GUI Robot Maze");
        pane = new MazePane();


        //Create the menu and add all necessary components to this application.
        MenuBar menuBar = new MenuBar();

        //Create File menu
        fileMenu = new Menu("File");
        MenuItem solveItem = new MenuItem("Solve");
        MenuItem repeatItem = new MenuItem("Repeat");
        MenuItem helpItem = new MenuItem("Help");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(solveItem, repeatItem, helpItem, new SeparatorMenuItem(), exitItem);
        solveItem.setDisable(true);
        repeatItem.setDisable(true);

        //Create the Maze menu
        mazeMenu = new Menu("Maze");
        MenuItem loadItem = new MenuItem("Load File");
        mazeMenu.getItems().add(loadItem);

        //Create the Robot menu
        robotMenu = new Menu("Robot");
        MenuItem leftHandRobotItem = new MenuItem("Left Hand Robot");
        MenuItem rightHandRobotItem = new MenuItem("Right Hand Robot");
        MenuItem randomRobotItem = new MenuItem("Random Robot");
        MenuItem lookAheadRobotItem = new MenuItem("Look Ahead Robot");
        MenuItem memoryRobotItem = new MenuItem("Memory Right Hand Robot");
        robotMenu.getItems().addAll(leftHandRobotItem, rightHandRobotItem, randomRobotItem, lookAheadRobotItem, memoryRobotItem);
        robotMenu.setDisable(true);

        //Add all the menus into the menu bar
        menuBar.getMenus().addAll(fileMenu, mazeMenu, robotMenu);

        //Next we will add events to these menu items
        solveItem.setOnAction(event -> solveMaze());

        repeatItem.setOnAction(event -> solveCurrentMaze());

        exitItem.setOnAction(event -> System.exit(0));

        loadItem.setOnAction(event -> {
            file = getFile();
            try {
                maze = new Maze(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pane.setMaze(maze);
            pane.paint();
            primaryStage.setWidth(WIDTH * maze.getCols() + 13);
            primaryStage.setHeight(HEIGHT * maze.getRows() + 62);
            robotMenu.setDisable(false);

        });

        helpItem.setOnAction(event -> showInfoDialog("Choose a Maze then choose the robot you want to run \n" +
                "through the maze. Once that is done click file and solve!"));

        leftHandRobotItem.setOnAction(event -> {
            solveItem.setDisable(false);
            repeatItem.setDisable(true);
            currentRobot = new LeftHandRobot(maze);
            pane.resetMaze();
            pane.paint();
        });
        rightHandRobotItem.setOnAction(event -> {
            solveItem.setDisable(false);
            repeatItem.setDisable(true);
            currentRobot = new RightHandRobot(maze);
            pane.resetMaze();
            pane.paint();
        });
        randomRobotItem.setOnAction(event -> {
            solveItem.setDisable(false);
            repeatItem.setDisable(true);
            currentRobot = new RandomRobot(maze);
            pane.resetMaze();
            pane.paint();
        });
        lookAheadRobotItem.setOnAction(event -> {
            solveItem.setDisable(false);
            repeatItem.setDisable(true);
            currentRobot = new LookAheadRobot(maze);
            pane.resetMaze();
            pane.paint();
        });
        memoryRobotItem.setOnAction(event ->{
            solveItem.setDisable(false);
            currentRobot = new MemoryRobot(maze);
            pane.resetMaze();
            pane.paint();
            repeatItem.setDisable(false);
        });


        VBox mainVBox = new VBox(menuBar, pane);
        scene = new Scene(mainVBox);

        //Set the scene to the stage and display it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Once the maze has been solved, we will ask the user if they would like to choose a new robot.
     */
    public void askUserNewRobot() {
        showInfoDialog("Maze is complete, pick a new robot and or maze and go again");
    }

    public File getFile() {
        JFileChooser chooser;
        try {
            //Get the file
            chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status != JFileChooser.APPROVE_OPTION) {
                showInfoDialog("No File Chosen\n Exiting Program");
                System.exit(0);
            }
            return chooser.getSelectedFile();
        } catch (Exception e) {
            showInfoDialog("Exception: " + e.getMessage());
        }
        return null;
    }

    public void solveMaze() {
        disableAllButtons(true);
        pane.resetMaze();
        currentRobot.setCurrentCol(maze.getStartCol());
        currentRobot.setCurrentRow(maze.getStartRow());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> runMaze()));
        timeline.play();
    }

    public void solveCurrentMaze(){
        disableAllButtons(true);
        currentRobot.setCurrentCol(maze.getStartCol());
        currentRobot.setCurrentRow(maze.getStartRow());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> runMaze()));
        timeline.play();
    }

    public void repeatMaze(){
        currentRobot.setCurrentRow(maze.getStartRow());
        currentRobot.setCurrentCol(maze.getStartCol());
        solveMaze();

    }

    public void disableAllButtons(boolean flag){
        fileMenu.setDisable(flag);
        mazeMenu.setDisable(flag);
        robotMenu.setDisable(flag);
    }

    public void runMaze() {
      {
          pane.paint();
          int direction = currentRobot.chooseMoveDirection();
            if (direction >= 0) {
                currentRobot.move(direction);
                pane.paint();
            }
            if (currentRobot.solved()) {
                pane.paint();
                timeline.stop();
                disableAllButtons(false);
                maze.setCell(maze.getExitRow(), maze.getExitCol(), 'X');
                askUserNewRobot();
            }
        }
}

    /**
     * Pops up a window that has a message for the user
     * @param message string to display to user
     */
    private void showInfoDialog(String message){
        Alert a = (new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK));
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // stretch box to show all of message
        a.show();
    }
}