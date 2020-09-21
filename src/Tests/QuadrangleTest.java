package Tests;

import Elements.SpotLight;
import Scenes.Scene;
import geometries.Quadrangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;

import java.awt.*;

public class QuadrangleTest {

    private final String IMAGES_TEST_DIR = "/src/Images/";

    @Test
    // gives an perfect Square
    public void QuadranglTest(){

       Scene scene = new Scene();

        var p1 = new Point3D(-200, -200, -300);
        var p2 = new Point3D(200, -200, -300);
        var p3 = new Point3D(200, 200, -300);
        var p4 = new Point3D(-200, 200, -300);

        Quadrangle quadrangle = new Quadrangle(p1, p2, p3, p4);
        quadrangle.set_nShininess(20);
        quadrangle.set_emmission(new Color(0,0,100));
        scene.addGeometry(quadrangle);
        scene.addLight( (new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005)));

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Quadrangle Test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    // gives an perfect Square
    public void QuadranglTest2(){
        Scene scene = new Scene();

        var p1 = new Point3D(-100, -200, -300);
        var p2 = new Point3D(300, -150, -450);
        var p3 = new Point3D(200, 250, -450);
        var p4 = new Point3D(-200, 200, -300);

      //  var p1 = new Point3D(-200 , -400, -100);
      //  var p2 = new Point3D(-600, -600, -200);
      //  var p3 = new Point3D(- 100, -80, -30);
      //  var p4 = new Point3D(200, -440, -40);


        Quadrangle quadrangle = new Quadrangle(p1, p2, p3, p4);
        quadrangle.set_nShininess(20);
        quadrangle.set_emmission(new Color(0,0,100));
        scene.addGeometry(quadrangle);
        scene.addLight( (new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005)));

        ImageWriter imageWriter = new ImageWriter(IMAGES_TEST_DIR + "Quadrangle Test2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }
}
