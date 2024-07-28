package micaminer;

import java.util.List;

import javafx.geometry.Point2D;

public class CollisionManager {


    // ----- Place_meeting functions: -----
    public static boolean place_meeting(SimpleGameObject gameObject1, SimpleGameObject gameObject2, int x1, int y1, int x2, int y2){
        Collider collider1 = gameObject1.getCollider();
        Collider collider2 = gameObject2.getCollider();
        List<Point2D> poly1 = collider1.generate_polygons(x1, y1);
        List<Point2D> poly2 = collider2.generate_polygons(x2, y2);
        if (poly1.isEmpty() || poly2.isEmpty()){
            return false;
        }
        return isColliding(poly1, poly2);
    }


    public static boolean place_meeting(SimpleGameObject gameObject1, SimpleGameObject gameObject2, int x, int y){
        int x2 = gameObject2.getX();
        int y2 = gameObject2.getY();
        return place_meeting(gameObject1, gameObject2, x, y, x2, y2);
    }


    public static boolean place_meeting(SimpleGameObject gameObject1, SimpleGameObject gameObject2){
        int x1 = gameObject1.getX();
        int y1 = gameObject1.getY();
        return place_meeting(gameObject1, gameObject2, x1, y1);
    }

    public static boolean place_meeting(SimpleGameObject gameObject1, List<SimpleGameObject> gameObjects, int x, int y){
        for (SimpleGameObject gameObject2 : gameObjects){
            if (place_meeting(gameObject1, gameObject2, x, y)){
                return true;
            }
        }
        return false;
    }



    

    // ---- Collision detection algorithm from Separating Axis Theorem: ----
    private static boolean isColliding(List<Point2D> poly1, List<Point2D> poly2) {
        // Check all edges of polygon 1
        for (int i = 0; i < poly1.size(); i++) {
            Point2D p1 = poly1.get(i);
            Point2D p2 = poly1.get((i + 1) % poly1.size());
            Point2D axis = new Point2D(-(p2.getY() - p1.getY()), p2.getX() - p1.getX());
            if (!overlap(poly1, poly2, axis)) {
                return false;
            }
        }
        
        // Check all edges of polygon 2
        for (int i = 0; i < poly2.size(); i++) {
            Point2D p1 = poly2.get(i);
            Point2D p2 = poly2.get((i + 1) % poly2.size());
            Point2D axis = new Point2D(-(p2.getY() - p1.getY()), p2.getX() - p1.getX());
            if (!overlap(poly1, poly2, axis)) {
                return false;
            }
        }
        
        return true;
    }

    private static boolean overlap(List<Point2D> poly1, List<Point2D> poly2, Point2D axis) {
        double minA = Double.POSITIVE_INFINITY;
        double maxA = Double.NEGATIVE_INFINITY;
        double minB = Double.POSITIVE_INFINITY;
        double maxB = Double.NEGATIVE_INFINITY;
        
        for (Point2D p : poly1) {
            double projection = p.getX() * axis.getX() + p.getY() * axis.getY();
            if (projection < minA) {
                minA = projection;
            }
            if (projection > maxA) {
                maxA = projection;
            }
        }
        
        for (Point2D p : poly2) {
            double projection = p.getX() * axis.getX() + p.getY() * axis.getY();
            if (projection < minB) {
                minB = projection;
            }
            if (projection > maxB) {
                maxB = projection;
            }
        }
        
        return !(maxA < minB || maxB < minA);
    }
}
