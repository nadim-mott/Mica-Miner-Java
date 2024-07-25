package micaminer;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class PuppetCharacter extends SimpleGameObject {
    private static boolean input_left;
    private static boolean input_right;
    private static final KeyCode key_left = KeyCode.A;
    private static final KeyCode key_right = KeyCode.D;
    private static int player_speed = 10;
    

    public PuppetCharacter(Group root, int x, int y) {
        super(root, x, y);
        this.setDrawing(new RectangleDrawing(root, 32, 32, -16, -16, Color.BLACK));
    }


    public void stepEvent(){
        // ----- Respond to keyboard input: -----
        int left_vector = input_left ? 1 : 0;
        int right_vector = input_right ? 1 : 0;
        setX(getX() + (right_vector - left_vector) * player_speed);
        System.out.println("new x=" + (getX()).toString());
        
        
    }


    public void keyPressed(KeyEvent event){
        KeyCode code = event.getCode();
        if (code == key_right){
            input_right = true;
        }
        else if (code == key_left){
            input_left = true;
        }
    }

    public void keyReleased(KeyEvent event){
        KeyCode code = event.getCode();
        if (code == key_right){
            input_right = false;
        }
        else if (code == key_left){
            input_left = false;
        }

        
    }
}
