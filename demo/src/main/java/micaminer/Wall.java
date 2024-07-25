package micaminer;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Wall extends SimpleGameObject{

    public Wall(Group root, int x, int y) {
        super(root, x, y);
        setDrawing(new RectangleDrawing(root, 32, 32, -16, -16, Color.GREY));
    }


}
