package Tests;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.*;

public class VectorTest {
    /************************************** Point3D tests **************************************************************/



    @Test
    public void compareTo() {
        Vector a = new Vector(5,6,7);
        Vector b = new Vector(5,6,7);
        Vector c = new Vector(4,6,7);


        assertEquals(1,a.compareTo(c));
        assertEquals(0,a.compareTo(b));
    }

    @Test
    public void add() {
        Vector a = new Vector(5,6,7);
        Vector b = new Vector(5,6,7);

        var result = new Vector(10,12,14);
        assertEquals(0,result.compareTo(a.add(b)));
    }

    @Test
    public void subtract() {
        Vector a = new Vector(5,6,7);
        Vector b = new Vector(3,5,6);


        Vector expected = new Vector(2,1,1);

       Vector result = new Vector(a.subtract(b));
        assertEquals(0,expected.compareTo(result));
    }

    @Test
    public void scale() {
        double scalar = 2.5;
        Vector a = new Vector(5,6,7);
        Vector expected = new Vector(12.5,15,17.5);
        Vector result = new Vector(a.scale(scalar));
        assertEquals(0,result.compareTo(expected));
    }

    @Test
    public void crossProduct() {
        Vector a = new Vector(1,2,3);
        Vector b = new Vector(4,5,6);

        Vector expected = new Vector(-3,6,-3);
      var result =   a.crossProduct(b);
       assertEquals(0,expected.compareTo(result));
    }

    @Test
    public void length() {
        Vector a = new Vector(1,2,3);

        double expected = Math.sqrt(14);

       assertTrue(isZero(usubtract(expected,a.length())));
    }

    @Test
    public void normalize() {
        Vector a = new Vector(1,1,1);

        double val = 1/Math.sqrt(3);
        Vector expected = new Vector(val,val,val);
       Vector result = new Vector(a.normalize());
        assertEquals(0,expected.compareTo(result));
    }

    @Test
    public void dotProduct() {
        Vector a = new Vector(3,4,5);
        Vector b = new Vector(3.5,4.5,5.5);

        double expected = 56;
        assertTrue(isZero(usubtract(expected,a.dotProduct(b))));
    }

}