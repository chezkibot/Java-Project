package Tests;

import Elements.PointLight;
import Elements.SpotLight;
import Scenes.Scene;
import geometries.Quadrangle;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;

import java.awt.*;

public class requrionTests {

    private final String IMAGES_TEST_DIR = "/src/Images/";

    @Test
    public void recursiveTest() {

        Scene scene = new Scene();
        scene.set_screenDistance(300);

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(250, new Point3D(0.0, 0.0, -1000));
        sphere2.set_nShininess(20);
        sphere2.set_emmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Recursive Test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void recursiveTest2(){

        Scene scene = new Scene();
        scene.set_screenDistance(300);

        Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000));
        sphere2.set_nShininess(20);
        sphere2.set_emmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(  1500, -1500, -1500),
                new Point3D( -1500,  1500, -1500),
                new Point3D(  200,  200, -375));

        Triangle triangle2 = new Triangle(new Point3D(  1500, -1500, -1500),
                new Point3D( -1500,  1500, -1500),
                new Point3D( -1500, -1500, -1500));

        triangle.set_emmission(new Color(20, 20, 20));
        triangle2.set_emmission(new Color(20, 20, 20));
        triangle.setKr(1);
        triangle2.setKr(0.5);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Recursive Test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


        @Test
        public void recursiveTest4()
        {
            Scene scene = new Scene();
            scene.set_screenDistance(300);

            Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
            sphere.set_nShininess(20);
            sphere.set_emmission(new Color(0, 0, 100));
          //  sphere.setKt(1);
            sphere.setKt(0.2);
            scene.addGeometry(sphere);

            Sphere sphere2 = new Sphere(250, new Point3D(0.0, 0.0, -1000));
            sphere2.set_nShininess(20);
            sphere2.set_emmission(new Color(100, 20, 20));
            sphere2.setKt(0);
            scene.addGeometry(sphere2);

            scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                    new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

            ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "recursiveTest4", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();
        }


        @Test
        public void recursiveTest6(){

            Scene scene = new Scene();
            scene.set_screenDistance(300);

            Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000));
            sphere.set_nShininess(20);
            sphere.set_emmission(new Color(0, 0, 100));
            sphere.setKt(0.5);
            scene.addGeometry(sphere);

            Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000));
            sphere2.set_nShininess(20);
            sphere2.set_emmission(new Color(100, 20, 20));
            sphere2.setKt(0);
            scene.addGeometry(sphere2);

            Triangle triangle = new Triangle(new Point3D(  1500, -1500, -1500),
                    new Point3D( -1500,  1500, -1500),
                    new Point3D(  200,  200, -375));

            Triangle triangle2 = new Triangle(new Point3D(  1500, -1500, -1500),
                    new Point3D( -1500,  1500, -1500),
                    new Point3D( -1500, -1500, -1500));

            triangle.set_emmission(new Color(20, 20, 20));
            triangle2.set_emmission(new Color(20, 20, 20));
            triangle.setKr(1);
            triangle2.setKr(0.5);
            scene.addGeometry(triangle);
            scene.addGeometry(triangle2);

            scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150),
                    new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

            ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "RecursiveTest6", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();

        }

        @Test
        public void recursiveTest7(){

            Scene scene = new Scene();
         //   scene.set_screenDistance(300);
            scene.set_screenDistance(200);

            Sphere sphere = new Sphere(300, new Point3D(0, 0, -1000));
            sphere.set_nShininess(20);
            sphere.set_emmission(new Color(0, 0, 100));
            sphere.setKt(0.5);
            scene.addGeometry(sphere);

            Sphere sphere2 = new Sphere(150, new Point3D(0, 0, -1000));
            sphere2.set_nShininess(20);
            sphere2.set_emmission(new Color(100, 20, 20));
            sphere2.setKt(0);
            scene.addGeometry(sphere2);

            Triangle triangle = new Triangle(new Point3D(  2000, -1000, -1500),
                    new Point3D( -1000,  2000, -1500),
                    new Point3D(  700,  700, -375));

            Triangle triangle2 = new Triangle(new Point3D(  2000, -1000, -1500),
                    new Point3D( -1000,  2000, -1500),
                    new Point3D( -1000, -1000, -1500));

            triangle.set_emmission(new Color(20, 20, 20));
            triangle2.set_emmission(new Color(20, 20, 20));
          //  triangle.setKr(1); the original
              triangle.setKr(0.5);
          //  triangle2.setKr(0.5); the original
            triangle2.setKr(1);
            scene.addGeometry(triangle);
            scene.addGeometry(triangle2);

            scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150),
                    new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

            ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "RecursiveTest7", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();
        }




    @Test
    public void twoPointLightsRecursive(){

        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000));
      //  triangle2.setKr(1);

        Triangle triangle3 = new Triangle(new Point3D(  -300,  -200, -200),
                new Point3D( -300,  -100, -200),
                new Point3D( -100, -100, -200));
        triangle3.set_emmission(new Color(20,100,0));

        var p1 = new Point3D(2000, -2000, -1000);
        var p2 = new Point3D(300, -2000, -1000);
        var p3 = new Point3D(200, 2000, -1000);
        var p4 = new Point3D(2000, 2000, -1000);

        Quadrangle quadrangle = new Quadrangle(p1, p2, p3, p4);
       // quadrangle.setKr(1);
      //  quadrangle.setKt(0.5);
        quadrangle.set_nShininess(30);
        quadrangle.set_emmission(new Color(50,99,176));
        scene.addGeometry(quadrangle);

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);



        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -100), 0, 0.000001, 0.0000005));
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -100), 0, 0.000001, 0.0000005));



        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "twoPointLightsRecursive", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    }

