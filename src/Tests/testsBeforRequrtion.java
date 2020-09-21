package Tests;

import Elements.SpotLight;
import Scenes.Scene;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;

import java.awt.*;

public class testsBeforRequrtion {

    private final String IMAGES_TEST_DIR = "/src/Images/";


        @Test
        public void multiObjectsTest1(){

            Scene scene = new Scene();
            Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
            sphere.set_nShininess(20);
            sphere.set_emmission(new Color(0, 0, 100));

            Sphere sphere2 = new Sphere(500, new Point3D(1500, 500, -1000));
            sphere2.set_nShininess(20);
            sphere2.set_emmission(new Color(0, 0, 100));

           // scene.addGeometry(sphere);
            scene.addGeometry(sphere2);

            Triangle triangle1 = new Triangle(new Point3D(  3500,  3500, -2000),
                    new Point3D( -3500, -3500, -1000),
                    new Point3D(  3500, -3500, -2000));

            Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                    new Point3D( -3500,  3500, -1000),
                    new Point3D( -3500, -3500, -1000));

            scene.addGeometry(triangle1);
            scene.addGeometry(triangle2);

            scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                    new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));


            ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "multi objects test1", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();

        }
    }


