package Tests;

import Elements.Camera;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriangleTest {

    @Test
    public void testIntersectionPoints() {



            final int WIDTH = 3;
            final int HEIGHT = 3;

            Ray[][] rays = new Ray[HEIGHT][WIDTH];

            Camera camera = new Camera(new Point3D(0.0, 0.0, 0.0),
                    new Vector(0.0, 1.0, 0.0),
                    new Vector(0.0, 0.0, -1.0));

            Triangle triangle = new Triangle(new Point3D(0, 1, -2),
                    new Point3D(1, -1, -2),
                    new Point3D(-1, -1, -2));

            Triangle triangle2 = new Triangle(new Point3D(0, 10, -2),
                    new Point3D(1, -1, -2),
                    new Point3D(-1, -1, -2));

            List<Point3D> intersectionPointsTriangle = new ArrayList<Point3D>();
            List<Point3D> intersectionPointsTriangle2 = new ArrayList<Point3D>();

        System.out.println("Camera:\n"+camera);

        for(
            int i = 0;
            i<HEIGHT;i++)

            {
                for (int j = 0; j < WIDTH; j++) {

                    Ray ray111 = camera.constructRayThroughPixel(
                            WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
                    rays[i][j] = ray111;
                    List<Point3D> rayIntersectionPoints = triangle.FindIntersections(rays[i][j]);
                    List<Point3D> rayIntersectionPoints2 = triangle2.FindIntersections(rays[i][j]);

                    for (Point3D iPoint : rayIntersectionPoints)
                        intersectionPointsTriangle.add(iPoint);

                    for (Point3D iPoint : rayIntersectionPoints2)
                        intersectionPointsTriangle2.add(iPoint);
                }
            }

            assertTrue(intersectionPointsTriangle.size() ==1);

            assertTrue(intersectionPointsTriangle2.size() ==2);

        System.out.println("Intersection Points:");
        for(
            Point3D iPoint:intersectionPointsTriangle)

            {
                System.out.println(iPoint);
            }
        System.out.println("--");
        for(
            Point3D iPoint:intersectionPointsTriangle2)

            {
                System.out.println(iPoint);
            }

        }



    @Test
    void getNormal () {
       Triangle tr = new Triangle(
                new Point3D(0.0, 1.0, 0.0),
                new Point3D(1.0, 0.0, 0.0),
                new Point3D(0.0, 0.0, 1.0)
       );

        Point3D headOfNormal = tr.getNormal(null).getHead();
        Coordinate exceptedX = new Coordinate(0.5773502691896258);
        Coordinate exceptedY = new Coordinate(0.5773502691896258);
        Coordinate exceptedZ = new Coordinate(0.5773502691896258);

        assertTrue(headOfNormal.getX().equals(exceptedX) && headOfNormal.getY().equals(exceptedY) && headOfNormal.getZ().equals(exceptedZ));
    }
}
