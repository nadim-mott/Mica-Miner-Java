package micaminer;

import java.util.List;

import javafx.geometry.Point2D;

public class CollisionManager {





    
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
