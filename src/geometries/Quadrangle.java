/*************************************************
 * class
 * Quadrangle
 * represents a Quadrangle
 * CONTAINS two triangles

 **************************************************/

package geometries;

import primitives.Limits;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Quadrangle extends Geometry implements FlatGeometry {

    private Triangle _tril1;
    private Triangle _tril2;

    // ***************** Constructors ********************** //

    public Quadrangle(Point3D P1, Point3D P2, Point3D P3, Point3D P4){
        _tril1 = new Triangle(P1, P2, P4);
        _tril2 = new Triangle(P2, P3, P4);

        if(!_tril1.IsOnTheSamePlane(_tril2))
            throw new IllegalArgumentException("the triangles are not on the same plane");
    }

    public Quadrangle(Quadrangle other) {
        _tril1 = new Triangle(other.get_Tril1());
        _tril2 = new Triangle(other.get_tril2());
    }

    // ***************** Getters/Setters ********************** //

    public Triangle get_Tril1() {
        return _tril1;
    }

    public Triangle get_tril2() {
        return _tril2;
    }


    public void set_tril1(Triangle tril1) {
        _tril1 = tril1;
    }

    public void set_tril2(Triangle tril2) {
        _tril2 = tril2;
    }

    // ***************** Administration ******************** //
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Quadrangle))return false;

        return _tril1.equals(((Quadrangle) obj).get_Tril1()) && _tril2.equals(((Quadrangle) obj).get_tril2());
    }

    @Override
    public String toString(){
        return "tril1 is: " + _tril1 + "trill2 is:" + _tril2;
    }

    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * FindIntersections
     * PARAMETERS
     * Ray
     * RETURN VALUE
     * List<Point3D>
     * MEANING
     * calculates the intersections of ray and quadrangle and returns all the points
     **************************************************/
    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        var result = _tril1.FindIntersections(ray);
        result.addAll(_tril2.FindIntersections(ray));
        return result;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _tril1.getNormal(point).normalize();
    }

    /*************************************************
     * FUNCTION
     * getLimit
     * RETURN VALUE
     * Limits
     * MEANING
     * returns the coordinates limits to create box
     **************************************************/

    @Override
    public Limits getLimits()
    {
        var limits1 = _tril1.getLimits();
        var limits2 = _tril2.getLimits();

        var maxX =  Math.max( limits1.getxMaxLimit(), limits2.getxMaxLimit());
        var minX =  Math.max( limits1.getxMinLimit(), limits2.getxMinLimit());
        var maxY =  Math.max( limits1.getyMaxLimit(), limits2.getyMaxLimit());
        var minY =   Math.max( limits1.getyMinLimit(), limits2.getyMinLimit());
        var maxZ =  Math.max( limits1.getzMaxLimit(), limits2.getzMaxLimit());
        var minZ =   Math.max( limits1.getzMinLimit(), limits2.getzMinLimit());

        var limits = new Limits(maxX,minX,maxY,minY,maxZ,minZ);
        return limits;
    }


}



