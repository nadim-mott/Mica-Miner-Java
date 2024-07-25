package micaminer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class App extends Application {

    private static Scene scene;
    private static List<GameObject> game_objects;
    private static final double TARGET_FPS = 60.0;
    private static final double SLEEP_LENGTH = 1_000_000_000 / TARGET_FPS;
    private long lastUpdateTime;

    @Override
    public void start(Stage stage) throws IOException {
        // ----- Define JavaFX Parameters: -----
        Group root = new Group();
        scene = new Scene(root, 640, 480, Color.WHITE);
        SimpleGameObject puppet = new PuppetCharacter(root, 100,100);
        stage.setScene(scene);
        stage.show();


        // ----- Initialize Keyboard input: -----
        scene.setOnKeyPressed(event -> handleKeyPress(event));
        scene.setOnKeyReleased(event -> handleKeyReleased(event));


        // ----- Create and start gameLoop: -----
        lastUpdateTime = System.nanoTime();
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double deltaTime = (now - lastUpdateTime); 
                if (deltaTime >= SLEEP_LENGTH) {
                    stepGameObjects();
                    drawGameObjects();
                    lastUpdateTime = now;
                }
                
            }
        };
        gameLoop.start();
    }

    private void handleKeyPress(KeyEvent event) {
        /* 
            TODO Potentially make this more efficient if the game is slowing down
            only a handful of GameObject will have keyevents relative to the many
            that do not so this loop. I am leaving this as is for now for the sake
            of making this engine easier to expand.
        */
        for (GameObject object : game_objects){
            object.keyPressed(event);
        }
    }
    private void handleKeyReleased(KeyEvent event) {
        /* 
            TODO Potentially make this more efficient if the game is slowing down
            only a handful of GameObject will have keyevents relative to the many
            that do not so this loop. I am leaving this as is for now for the sake
            of making this engine easier to expand.
        */
        for (GameObject object : game_objects){
            object.keyReleased(event);
        }
    }



    public void drawGameObjects(){
        for (GameObject object : game_objects){
            object.drawEvent();
        }
    }

    public static void stepGameObjects(){
        for (GameObject object : game_objects){
            object.stepEvent();
        }
    }

    public static void addGameObject(GameObject game_object){
        game_objects.add(game_object);
    }



    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }




    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        game_objects = new ArrayList<GameObject>();
        launch();
        
    }

}