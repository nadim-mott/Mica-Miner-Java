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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static List<GameObject> game_objects;
    private static final int SLEEP_LENGTH = 1000;

    @Override
    public void start(Stage stage) throws IOException {
        // ---- Define JavaFX Parameters: -----
        Group root = new Group();
        scene = new Scene(root, 640, 480, Color.WHITE);
        drawGameObjects(root);
        stage.setScene(scene);
        stage.show();


        // Initialize lastUpdateTime
        long lastUpdateTime = System.nanoTime();

        // Create and start the game loop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double deltaTime = (now - lastUpdateTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
                stepGameObjects();
            }
        };
        gameLoop.start();
    }

    public void drawGameObjects(Group root){
        for (GameObject object : game_objects){
            object.drawEvent(root);
        }
    }

    public static void stepGameObjects(){
        for (GameObject object : game_objects){
            object.stepEvent();
            object.updateDrawing();
        }
    }

    public static void addGameObject(GameObject game_object){
        game_objects.add(game_object);
    }



    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    
    static void sleep(){
        try {
            Thread.sleep(SLEEP_LENGTH);
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted");
            e.printStackTrace();
        }
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        game_objects = new ArrayList<GameObject>();
        PlayerPuppet puppet = new PlayerPuppet(100,100);
        addGameObject(puppet);
        launch();
        
    }

}