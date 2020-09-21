package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.*;

import static org.junit.Assert.*;

public class Point3DTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void compareTo() {
       var a = new Point3D(1,3.48748747,-9.8373873);
       var b = new Point3D(1 ,2,4);
       var c = new Point3D(1,3.48748747,-9.8373873);

       a.compareTo(b);
       assertEquals(1, a.compareTo(b));
        assertEquals(0,a.compareTo(c));
    }

    @Test
    public void add() {
       var vector = new Vector(1,2,3);
      var point = new Point3D(1,2,3);
       var resultPoint = point.add(vector);
      var expected = new Point3D(2,4,6);
      Boolean a = (resultPoint.getX().compareTo(point.getX())) == 0 && (resultPoint.getY().compareTo(point.getY())) ==0 && (resultPoint.getY().compareTo(point.getY()) == 0);
      assertEquals(0,expected.compareTo(resultPoint));
    }

    @Test
    public void subtract() {
        var point1 = new Point3D(7,7,9);
        var vector2 = new Vector(4,5,6);


        var result = point1.subtract(vector2);
       var expected = new Point3D(3,2,3);
              //  assertEquals(1,point1.compareTo(expected));
        assertEquals(0,expected.compareTo(result));
    }

    @Test
    public void distance() {
        var a = new Point3D(2,5,6);
        var b = new Point3D(4,5,2);
        double c = Math.sqrt(20);

        assertTrue(isZero(usubtract(c,a.distance(b))));
    }

    @Test
    public void toString1() {
        var point = new Point3D(1,2,3);
        String string  =  "(1.00, 2.00, 3.00)";
        assertEquals(point.toString(),string);
    }

}