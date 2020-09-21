package Tests;

import Elements.Camera;
import Elements.DirectionalLight;
import Elements.PointLight;
import Elements.SpotLight;
import Scenes.Box;
import Scenes.Scene;
import geometries.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;

import java.awt.*;
import java.util.ArrayList;

public class ImageWriterTest {

    private final String IMAGES_TEST_DIR = "/src/Images/";

    @Test
    public void writeToImage() {

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Image writer test", 500, 500, 500, 500);

        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {

                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(j, i, 255, 255, 255);

            }
        }

        imageWriter.writeToimage();

    }


    @Test
    public void spotLightTest() {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        scene.addLight((new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005)));

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Spot test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void spotLightTest2() {

        Scene scene = new Scene();
        scene.set_screenDistance(200);
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-125, -225, -260),
                new Point3D(-225, -125, -260),
                new Point3D(-225, -225, -270));


        triangle.set_emmission(new Color(0, 0, 100));
        triangle.set_nShininess(4);
        scene.addGeometry(triangle);

        scene.addLight((new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005)));

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Spot test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void pointLightTest() {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),
                0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Point test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();


    }


    @Test
    public void spotLightTest3() {

        Scene scene = new Scene();

        Triangle triangle = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "ISpot test 3", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void pointLightTest2() {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));

        Triangle triangle = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Point test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void shadowTest() {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Shadow test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void twoSpotLights() {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        Triangle triangle3 = new Triangle(new Point3D(-300, -200, -200),
                new Point3D(-300, -100, -200),
                new Point3D(-100, -100, -200));
        triangle3.set_emmission(new Color(20, 20, 150));

        var p1 = new Point3D(2000, -2000, -1000);
        var p2 = new Point3D(300, -2000, -1000);
        var p3 = new Point3D(200, 2000, -1000);
        var p4 = new Point3D(2000, 2000, -1000);

        Quadrangle quadrangle = new Quadrangle(p1, p2, p3, p4);
        quadrangle.set_nShininess(30);
        quadrangle.set_emmission(new Color(50, 99, 176));
        scene.addGeometry(quadrangle);

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),
                new Vector(8, 2, -3), 0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "twoSpotLights", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void twoPointLights() {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.set_nShininess(20);
        sphere.set_emmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        Triangle triangle3 = new Triangle(new Point3D(-300, -200, -200),
                new Point3D(-300, -100, -200),
                new Point3D(-100, -100, -200));
        triangle3.set_emmission(new Color(20, 100, 0));

        var p1 = new Point3D(2000, -2000, -1000);
        var p2 = new Point3D(300, -2000, -1000);
        var p3 = new Point3D(200, 2000, -1000);
        var p4 = new Point3D(2000, 2000, -1000);

        Quadrangle quadrangle = new Quadrangle(p1, p2, p3, p4);
        quadrangle.set_nShininess(30);
        quadrangle.set_emmission(new Color(50, 99, 176));
        scene.addGeometry(quadrangle);

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);


        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -100), 0, 0.000001, 0.0000005));
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -100), 0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "twoPointLights", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void twoDirectionalLights() {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        // sphere.set_nShininess(20);
        sphere.setKt(0.9);
        //  sphere.set_emmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));


        //   Triangle starOfDavid1 = new Triangle(new Point3D(  -300,  -200, -200),
        //           new Point3D( -300,  200, -200),
        //           new Point3D( 200, 0, -200));
        //   starOfDavid1.set_emmission(new Color(45, 66, 100));


        var p1 = new Point3D(2000, -2000, -1000);
        var p2 = new Point3D(300, -2000, -1000);
        var p3 = new Point3D(200, 2000, -1000);
        var p4 = new Point3D(2000, 2000, -1000);

        Quadrangle quadrangle = new Quadrangle(p1, p2, p3, p4);
        quadrangle.set_nShininess(30);
        quadrangle.set_emmission(new Color(50, 99, 176));
        scene.addGeometry(quadrangle);

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        //  scene.addGeometry(starOfDavid1);


        scene.addLight(new DirectionalLight(new Color(170, 70, 100), new Vector(8, 2, -3)));
        scene.addLight(new DirectionalLight(new Color(170, 70, 100), new Vector(-2, -22, -3)));


        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "twoDirectionalLights", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void Testing() {

        Scene scene = new Scene();
        scene.set_backGround(new Color(206, 192, 179));
        scene.set_screenDistance(300);

        Sphere sphere1 = new Sphere(500, new Point3D(0.0, 150.0, -1200));
        sphere1.set_nShininess(20);
        sphere1.set_emmission(new Color(159, 26, 43));


        Sphere sphere2 = new Sphere(300, new Point3D(-80.0, 150.0, -800));
        sphere2.set_nShininess(20);
        sphere2.set_emmission(new Color(172, 96, 17));


        Sphere sphere3 = new Sphere(170, new Point3D(-160.0, 80.0, -500));
        sphere3.set_nShininess(20);
        sphere3.set_emmission(new Color(154, 172, 28));

        Sphere sphere4 = new Sphere(90, new Point3D(-150.0, 20.0, -300));
        sphere4.set_nShininess(20);
        sphere4.set_emmission(new Color(123, 58, 116));


        //scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        // scene.addLight(new SpotLight(new Color(130, 100, 130), new Point3D(150, 150, -50), //right light
        //       new Vector(new Point3D(300, 0, -250)), 0, 0.00001, 0.000005));

        scene.addLight(new SpotLight(new Color(158, 65, 61),
                new Point3D(500, 500, 100),
                new Vector(-2, -5, 0),
                0, 0.000001, 0.0000005));

        scene.addLight(new SpotLight(new Color(158, 65, 61),
                new Point3D(500, -500, 0),
                new Vector(-2, 3, 0),
                0, 0.000001, 0.0000005));


        Plane P1 = (new Plane(new Vector(1, 0, 0), new Point3D(-500, 0, 0)));
        P1.set_emmission(new Color(92, 155, 88));

        scene.addGeometry(P1);


        var p1 = new Point3D(-1200, -1200, -600);
        var p2 = new Point3D(500, -1200, -600);
        var p3 = new Point3D(500, 150, -1000);
        var p4 = new Point3D(-1200, 150, -1000);

        Quadrangle quadrangle = new Quadrangle(p1, p2, p3, p4);
        quadrangle.set_nShininess(20);
        quadrangle.set_emmission(new Color(77, 62, 83));
        scene.addGeometry(quadrangle);


        var a1 = new Point3D(-1200, 1200, -600);
        var a2 = new Point3D(500, 1200, -600);
        var a3 = new Point3D(500, 250, -1000);
        var a4 = new Point3D(-1200, 250, -1000);

        Quadrangle quadrangle2 = new Quadrangle(a1, a2, a3, a4);
        quadrangle.set_nShininess(20);
        quadrangle.set_emmission(new Color(76, 66, 78));
        scene.addGeometry(quadrangle2);


        var b1 = new Point3D(-1200, -1200 + 2450, -600);
        var b2 = new Point3D(500, -1200 + 2450, -600);
        var b3 = new Point3D(500, 150 + 2450, -1000);
        var b4 = new Point3D(-1200, 150 + 2450, -1000);

        Quadrangle quadrangle3 = new Quadrangle(b1, b2, b3, b4);
        quadrangle3.set_nShininess(20);
        quadrangle3.set_emmission(new Color(100, 41, 65));
        scene.addGeometry(quadrangle3);


        var c1 = new Point3D(-1200, 1200 - 2450, -600);
        var c2 = new Point3D(500, 1200 - 2450, -600);
        var c3 = new Point3D(500, 250 - 2850, -1000);
        var c4 = new Point3D(-1200, 250 - 2850, -1000);

        Quadrangle quadrangle4 = new Quadrangle(c1, c2, c3, c4);
        quadrangle4.set_nShininess(20);
        quadrangle4.set_emmission(new Color(100, 41, 65));
        scene.addGeometry(quadrangle4);

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "testing", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void TestingLineProblem() {
        var color = new Color(101, 101, 103);
        Scene scene = new Scene();
        scene.set_backGround(new Color(206, 192, 179));
        scene.set_screenDistance(100);
/*
        Sphere sphere1 = new Sphere(500, new Point3D(0.0, 150.0, -1200));
        sphere1.set_nShininess(20);
        sphere1.set_emmission(new Color(159, 26, 43));


        Sphere sphere2 = new Sphere(300, new Point3D(-80.0, 150.0, -800));
        sphere2.set_nShininess(20);
        sphere2.set_emmission(new Color(172, 96, 17));


        Sphere sphere3= new Sphere(170, new Point3D(-160.0, 80.0, -500));
        sphere3.set_nShininess(20);
        sphere3.set_emmission(new Color(154, 172, 28));

        Sphere sphere4 = new Sphere(90, new Point3D(-150.0, 20.0, -300));
        sphere4.set_nShininess(20);
        sphere4.set_emmission(new Color(123, 58, 116));


        //scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        // scene.addLight(new SpotLight(new Color(130, 100, 130), new Point3D(150, 150, -50), //right light
        //       new Vector(new Point3D(300, 0, -250)), 0, 0.00001, 0.000005));
*/
        scene.addLight(new SpotLight(new Color(158, 65, 61),
                new Point3D(500, 500, 100),
                new Vector(-2, -5, 0),
                0, 0.000001, 0.0000005));

        scene.addLight(new SpotLight(new Color(158, 65, 61),
                new Point3D(500, -500, 0),
                new Vector(-2, 3, 0),
                0, 0.000001, 0.0000005));


        Plane P1 = (new Plane(new Vector(1, 0, 0), new Point3D(-500, 0, 0)));
        P1.set_emmission(new Color(92, 155, 88));

        scene.addGeometry(P1);

/*
        var p1 = new Point3D(-1200, -1200, -600);
        var p2 = new Point3D(500, -1200, -600);
        var p3 = new Point3D(500, 150, -1000);
        var p4 = new Point3D(-1200, 150, -1000);

        Quadrangle quadrangle = new Quadrangle(p1, p2, p3, p4);
        quadrangle.set_nShininess(20);
        quadrangle.set_emmission(new Color(77, 62, 83));
        scene.addGeometry(quadrangle);

*/
        var a1 = new Point3D(-1200, 1200, -600);
        var a2 = new Point3D(500 + 1000, 1200, -600);///////////////// the highest geometry
        var a3 = new Point3D(500 + 1000, -2500, -1000);
        var a4 = new Point3D(-1200, -2500, -1000);

        //      Quadrangle quadrangle2 = new Quadrangle(a1, a2, a3, a4);
        //     quadrangle.set_nShininess(20);
        //      quadrangle.set_emmission(new Color(76, 66, 78));
        //     scene.addGeometry(quadrangle2);

        var triangle = new Triangle(a2, a4, a3);
        triangle.set_nShininess(20);
        triangle.set_emmission(new Color(76, 76, 78));
        scene.addGeometry(triangle);



/*
        var b1 = new Point3D(-1200, -1200+2450, -600);
        var b2 = new Point3D(500, -1200+2450, -600);
        var b3 = new Point3D(500, 150+2450, -1000);
        var b4 = new Point3D(-1200, 150+2450, -1000);

        Quadrangle quadrangle3 = new Quadrangle(b1, b2, b3, b4);
        quadrangle3.set_nShininess(20);
        quadrangle3.set_emmission(new Color(100, 41, 65));
        scene.addGeometry(quadrangle3);


        var c1 = new Point3D(-1200, 1200-2450, -600);
        var c2 = new Point3D(500, 1200-2450, -600);
        var c3 = new Point3D(500, 250-2850, -1000);
        var c4 = new Point3D(-1200, 250-2850, -1000);

        Quadrangle quadrangle4 = new Quadrangle(c1, c2, c3, c4);
        quadrangle4.set_nShininess(20);
        quadrangle4.set_emmission(new Color(100, 41, 65));
        scene.addGeometry(quadrangle4);
*/
        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "TestingLineProblem", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void glossey() {

        Scene scene = new Scene();
        scene.set_camera(new Camera(new Point3D(0, 0, 0),
                new Vector(0, 1, 0),
                new Vector(0, 0, 1)));
        scene.set_screenDistance(250);
//  **************************** Background  **********************************     //

        Quadrangle floor = new Quadrangle(new Point3D(-300, -200, 200), // left-down corner
                new Point3D(300, -200, 200),//right-down corner
                new Point3D(200, -140, 500), // right-up corner
                new Point3D(-200, -140, 500));// left-up corner
        floor.set_emmission(new Color(30, 60, 20));
        floor.set_nShininess(80);
        floor.setKr(0.5);
        floor.setKt(0);


        // mirror
        Quadrangle mirror = new Quadrangle(new Point3D(-500, 200, 500), // left-up corner
                new Point3D(-500, -300, 500),// Left-down-Far corner
                new Point3D(500, -300, 500), // right-down-Far corner
                new Point3D(500, 200, 500));// left-up corner
        mirror.set_emmission(new Color(0, 0, 0));
        mirror.set_nShininess(10);
        mirror.setKr(1);
        mirror.setKt(0.6);

        //scene.addGeometry(qGlass);
        scene.addGeometry(floor);
        scene.addGeometry(mirror);

//**************************** Spheres  ********************************** //


        Sphere sphere1 = new Sphere(50, new Point3D(130, -100, 350));
        sphere1.set_emmission(new Color(82, 17, 21));

        sphere1.set_nShininess(20);
        sphere1.setKr(0.5);
        sphere1.setKt(0.2);


        Sphere sphere4 = new Sphere(20, new Point3D(-30, -100, 350));
        Sphere sphere5 = new Sphere(20, new Point3D(30, -100, 350));
        sphere1.set_emmission(new Color(100, 41, 65));
        sphere4.set_emmission(new Color(66, 142, 222));

        //right ball
        Sphere sphere2 = new Sphere(40, new Point3D(0, 0, 150));
        sphere2.set_emmission(new Color(213, 255, 24));
        sphere2.set_nShininess(10);
        sphere2.setKr(0.2);
        sphere2.setKt(0.2);


        Sphere sphere3 = new Sphere(50, new Point3D(-130, -100, 350));
        sphere3.set_emmission(new Color(33, 79, 135));
        sphere3.set_nShininess(40);
        sphere3.setKr(0.2);
        sphere3.setKt(0.2);

        Sphere sphere6 = new Sphere(20, new Point3D(140, -100, 350));
        Sphere sphere7 = new Sphere(20, new Point3D(-140, -100, 350));


        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(sphere5);
        scene.addGeometry(sphere6);
        scene.addGeometry(sphere7);
//*********************************** Lights  ********************************** //


        SpotLight spotLight = new SpotLight(new Color(40, 40, 20), new Point3D(200, 100, 250),
                new Vector(-1, -1, 1), 0, 0.00001, 0.000005);

        PointLight pointLight = new PointLight(new Color(100, 30, 30), new Point3D(0, 100, 300), 0, 0.00001, 0.000005);
        scene.addLight(spotLight);
        scene.addLight(pointLight);




//**************************** Rendering  ********************************** //
        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "shlav4", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }



@Test
    public void glosseywithbox() {

        Scene scene = new Scene();
        scene.set_camera(new Camera(new Point3D(0, 0, 0),
                new Vector(0, 1, 0),
                new Vector(0, 0, 1)));
        scene.set_screenDistance(250);
//  **************************** Background  **********************************     //

        Quadrangle floor = new Quadrangle(new Point3D(-300, -200, 200), // left-down corner
                new Point3D(300, -200, 200),//right-down corner
                new Point3D(200, -140, 500), // right-up corner
                new Point3D(-200, -140, 500));// left-up corner
        floor.set_emmission(new Color(30, 60, 20));
        floor.set_nShininess(80);
        floor.setKr(0.5);
        floor.setKt(0);



        // mirror
        Quadrangle mirror = new Quadrangle(new Point3D(-200, 200, 500), // left-up corner
                new Point3D(-200, -300, 500),// Left-down-Far corner
                new Point3D(200, -300, 500), // right-down-Far corner
                new Point3D(200, 200, 500));// left-up corner
        mirror.set_emmission(new Color(0, 0, 0));
        mirror.set_nShininess(10);
        mirror.setKr(1);
        mirror.setKt(0.6);

        //scene.addGeometry(qGlass);
        scene.addGeometry(floor);
        scene.addGeometry(mirror);

//**************************** Spheres  ********************************** //


        Sphere sphere1 = new Sphere(50, new Point3D(130, -100, 350));
        sphere1.set_emmission(new Color(82, 17, 21));

        sphere1.set_nShininess(20);
        sphere1.setKr(0.5);
        sphere1.setKt(0.2);


        Sphere sphere4 = new Sphere(20, new Point3D(-30, -100, 350));
        Sphere sphere5 = new Sphere(20, new Point3D(30, -100, 350));
        sphere1.set_emmission(new Color(100, 41, 65));
        sphere4.set_emmission(new Color(66, 142, 222));

        //right ball
        Sphere sphere2 = new Sphere(40, new Point3D(0, 0, 150));
        sphere2.set_emmission(new Color(213, 255, 24));
        sphere2.set_nShininess(10);
        sphere2.setKr(0.2);
        sphere2.setKt(0.2);


        Sphere sphere3 = new Sphere(50, new Point3D(-130, -100, 350));
        sphere3.set_emmission(new Color(33, 79, 135));
        sphere3.set_nShininess(40);
        sphere3.setKr(0.2);
        sphere3.setKt(0.2);

        Sphere sphere6 = new Sphere(20, new Point3D(140, -100, 350));
        Sphere sphere7 = new Sphere(20, new Point3D(-140, -100, 350));


        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(sphere5);
        scene.addGeometry(sphere6);
        scene.addGeometry(sphere7);
//*********************************** Lights  ********************************** //


        SpotLight spotLight = new SpotLight(new Color(40, 40, 20), new Point3D(200, 100, 250),
                new Vector(-1, -1, 1), 0, 0.00001, 0.000005);

        PointLight pointLight = new PointLight(new Color(100, 30, 30), new Point3D(0, 100, 300), 0, 0.00001, 0.000005);
        scene.addLight(spotLight);
        scene.addLight(pointLight);


    var geometries = new ArrayList<Geometry>();
    geometries.add(sphere1);
    geometries.add(sphere2);

    var geometries2 = new ArrayList<Geometry>();

    geometries.add(sphere3);
    geometries.add(sphere4);
    geometries.add(sphere5);
    geometries.add(sphere6);
    geometries.add(sphere7);
    geometries.add(mirror);
    geometries.add(floor);


    var box1 = new Box(geometries);
    var boxes  = new ArrayList<Box>();
    boxes.add(box1);
    scene.set_boxes(boxes);


//**************************** Rendering  ********************************** //
        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "shlav4WithBox", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImageWithBoxing();
        render.writeToImage();
    }
}





