package micaminer;

import javafx.scene.input.KeyEvent;

public interface GameObject {
    public void stepEvent();
    public void drawEvent();
    public void keyPressed(KeyEvent event);
    public void keyReleased(KeyEvent event);

    
}
