package micaminer;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class RectangleDrawing implements ObjectDrawing {
    private static Rectangle rectangle;
    private int x;
    private int y;
    
    public RectangleDrawing(Group root, int width, int height, int x, int y, Paint fill){
        rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        this.x = x;
        this.y = y;
        rectangle.setFill(fill);
        root.getChildren().add(rectangle);
    }


    @Override
    public void drawObject(int x, int y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

}
