package Tests;

import Elements.PointLight;
import Scenes.Box;
import Scenes.Scene;

import geometries.Geometry;
import geometries.Sphere;

import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;

import java.awt.*;
import java.util.ArrayList;


public class BoxingTest {

        private final String IMAGES_TEST_DIR = "/src/Images/";

        @Test
        public void checkProblemInBall() {
            var color = new Color(255,233,170);
            Scene scene = new Scene();
            scene.get_camera().set_P0(new Point3D(0, 0, 0));
            scene.set_screenDistance(100);

            Material material = new Material(20, 0.55, 0.1, 0.6,0.4); // the original one
            //   Material material = new Material(20, 0.55, 0.1, 0.6,0.4);
/*
        Point3D pSphere = new Point3D(-50, -100, -150);
        Sphere sphere = new Sphere(50, pSphere);
        sphere.set_emmission(new Color(10, 100, 20));
        sphere.set_material(material);
        scene.addGeometry(sphere);
*/

            Point3D pSphere1 = new Point3D(70, -100, -250);
            Sphere sphere1 = new Sphere(50, pSphere1);
            sphere1.set_emmission(new Color(110, 20, 10));
            sphere1.set_material(material);
           // scene.addGeometry(sphere1);
           var geometries = new ArrayList<Geometry> ();
           geometries.add(sphere1);
            var box1 = new Box(geometries);
            var boxes  = new ArrayList<Box>();
            boxes.add(box1);



            Point3D pSphere2 = new Point3D(70, 100, -250);
            Sphere sphere2 = new Sphere(50, pSphere2);
            sphere2.set_emmission(new Color(110, 20, 10));
            sphere2.set_material(material);
            // scene.addGeometry(sphere1);
            var geometries2 = new ArrayList<Geometry> ();
            geometries2.add(sphere2);
            var box2 = new Box(geometries2);
           // var boxes2  = new ArrayList<Box>();
            boxes.add(box2);
            scene.set_boxes(boxes);
/*
        Point3D pSphere2 = new Point3D(-10, 150, -350);
        Sphere sphere2 = new Sphere(90,pSphere2 );
        sphere2.set_emmission(new Color(20, 20, 100));
        sphere2.set_material(material);
        scene.addGeometry(sphere2);
*/

/*
            Plane plane = new Plane(new Vector(1,0,0), new Point3D(-100, 0 , 0));
            //  plane.set_material(15, 0.1, 0.4, 0.1,0.2); the original one
            plane.set_material(0.5, 0.1, 0.4, 0.1,0.2);


            plane.set_emmission(new Color(133, 133, 133));
            scene.addGeometry(plane);
*/
            scene.addLight(new PointLight(new Color(130, 100, 130), new Point3D(150, 150, -50), 0, 0.00001, 0.000005));
            scene.addLight(new PointLight(new Color(110, 130, 30), new Point3D(150, 150, -50), 0, 0.00001, 0.000005));

//        scene.addLight(new PointLight(new Color(90, 30, 130), new Point3D(150, -150, -50), 0, 0.00001, 0.000005));


            ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "testPart3_ABall", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

          //  render.renderImage();
            render.renderImageWithBoxing();
            render.writeToImage();
        }


    @Test
    public void checkProblemInBallwithoutBoxing() {
        var color = new Color(255,233,170);
        Scene scene = new Scene();
        scene.get_camera().set_P0(new Point3D(0, 0, 0));
        scene.set_screenDistance(100);

        Material material = new Material(20, 0.55, 0.1, 0.6,0.4); // the original one

        Point3D pSphere1 = new Point3D(70, -100, -250);
        Sphere sphere1 = new Sphere(50, pSphere1);
        sphere1.set_emmission(new Color(110, 20, 10));
        sphere1.set_material(material);
        // scene.addGeometry(sphere1);
        var geometries = new ArrayList<Geometry> ();
        geometries.add(sphere1);

        scene.set_geometries(geometries);


        Point3D pSphere2 = new Point3D(70, 100, -250);
        Sphere sphere2 = new Sphere(50, pSphere2);
        sphere2.set_emmission(new Color(110, 20, 10));
        sphere2.set_material(material);


        geometries.add(sphere2);

        scene.set_geometries(geometries);

        scene.addLight(new PointLight(new Color(130, 100, 130), new Point3D(150, 150, -50), 0, 0.00001, 0.000005));
        scene.addLight(new PointLight(new Color(110, 130, 30), new Point3D(150, 150, -50), 0, 0.00001, 0.000005));


        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "testPart3_ABall", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        //  render.renderImage();
        render.renderImage();
        render.writeToImage();
    }


}











