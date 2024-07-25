package micaminer;

import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

public class SimpleGameObject implements GameObject{

    private int x;
    private int y;
    private ObjectDrawing drawing;

    public SimpleGameObject(Group root, int x, int y){
        App.addGameObject(this);
        this.setX(x);
        this.setY(y);
        this.drawing = new NullDrawing();
    }


    @Override
    public void stepEvent() {
        
    }

    @Override
    public void drawEvent() {
        this.drawing.drawObject(getX(), getY());
    }

    public Integer getX() {
        return this.x;
    }


    public Integer getY() {
        return this.y;
    }


    public void setX(int new_x) {
        this.x = new_x;
    }


    public void setY(int new_y) {
        this.y = new_y;
    }

    public Collider getCollider() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public ObjectDrawing getDrawing() {
        return this.drawing;
    }

    public void setDrawing(ObjectDrawing new_drawing) {
        this.drawing = new_drawing;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        
    }

    public void keyReleased(KeyEvent event){

    }



}
