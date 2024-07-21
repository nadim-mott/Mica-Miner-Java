package micaminer;

import javafx.scene.Group;

public interface GameObject {
    public void stepEvent();
    public void drawEvent(Group root);
    public void updateDrawing();
    public Integer[] getCoordinates();
    public Integer getX();
    public Integer getY();
    public void setX(int new_x);
    public void setY(int new_y);
    public void set_coordinates();
    public Collider get_collider();
}
