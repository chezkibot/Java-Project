package Tests;

import Elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CameraTest {


        @Test
        public void Test13()
        {

            final int WIDTH  = 11;
            final int HEIGHT = 11;

            Point3D[][] screen = new Point3D [HEIGHT][WIDTH];

            Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                    new Vector (0.0, 1.0, 0.0),
                    new Vector (0.0, 0.0, -1.0));

            System.out.println("Test13: Camera test:\n" + camera);

            for (int i = 0; i < HEIGHT; i++)
            {
                for (int j = 0; j < WIDTH; j++)
                {

                    Ray ray = camera.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

                    screen[i][j] = ray.getPOO();

                    System.out.print(screen[i][j]);
                    System.out.println(ray.getDirection());

                    // Checking z-coordinate
                    assertTrue(Double.compare(screen[i][j].getZ().getCoordinate(), -1.0) == 0);

                    // Checking all options
                    double x = screen[i][j].getX().getCoordinate();
                    double y = screen[i][j].getY().getCoordinate();
                    double[] values = {0.0,3.0,6.0,9.0,12.0,15.0};

                    int k = 0;
                    for(; k < values.length; k++)
                        if(values[k] == Math.abs(x))
                            break;
                    if(k == values.length)
                        fail("Wrong x coordinate");

                    k = 0;
                    for(; k < values.length; k++)
                        if(values[k] == Math.abs(y))
                            break;
                    if(k == values.length)
                        fail("Wrong Y coordinate");
                }
                System.out.println("---");
            }
        }
    }