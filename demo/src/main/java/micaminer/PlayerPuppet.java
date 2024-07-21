package micaminer;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class PlayerPuppet implements GameObject{

    private static Rectangle javafx_rectangle;
    private int x;
    private int y;

    public PlayerPuppet(int x, int y){
        this.setX(x);
        this.setY(y);
    }


    @Override
    public void stepEvent() {
        setX(getX() + 1);
        System.out.println("new x=" + (getX()).toString());
    }
    

    @Override
    public void drawEvent(Group root) {
        int x = this.getX();
        int y = this.getY();
        javafx_rectangle = new Rectangle();
        javafx_rectangle.setX(x - 5);
        javafx_rectangle.setY(y - 5);
        javafx_rectangle.setWidth(10);
        javafx_rectangle.setHeight(10);

        root.getChildren().add(javafx_rectangle);

    }
    @Override
    public void updateDrawing() {
        int x = this.getX();
        int y = this.getY();
        javafx_rectangle.setX(x - 5);
        javafx_rectangle.setY(y - 5);
    }


    @Override
    public Integer[] getCoordinates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getX() {
        return this.x;
    }

    @Override
    public Integer getY() {
        return this.y;
    }

    @Override
    public void setX(int new_x) {
        this.x = new_x;
    }

    @Override
    public void setY(int new_y) {
        this.y = new_y;
    }

    @Override
    public void set_coordinates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collider get_collider() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
