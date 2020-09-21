/*************************************************
 * class
 * Triangle
 * represents a Triangle
 * CONTAINS a 3 vertices (Point 3D's)

 **************************************************/
package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Triangle extends Geometry implements FlatGeometry {


    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;
    // ***************** Constructors ********************** //
    public Triangle(){}
    public Triangle(Triangle triangle){
        _p1 = triangle._p1;
        _p2 = triangle._p2;
        _p3 = triangle._p3;
    }
    public Triangle(Point3D p1, Point3D p2, Point3D p3){
        _p1 = p1; _p2 = p2; _p3 = p3;
    }
   // public Triangle(Map<String, String> attributes);
    // ***************** Getters/Setters ********************** //
    public Point3D get_p1() {
        return _p1;
    }

    public void set_p1(Point3D _p1) {
        this._p1 = _p1;
    }

    public Point3D get_p2() {
        return _p2;
    }

    public void set_p2(Point3D _p2) {
        this._p2 = _p2;
    }

    public Point3D get_p3() {
        return _p3;
    }

    public void set_p3(Point3D _p3) {
        this._p3 = _p3;
    }

    // ***************** Administration ******************** //

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Triangle))return false;

        return _p1.equals(((Triangle) obj).get_p1())
                && _p2.equals(((Triangle) obj).get_p2())
                && _p3.equals(((Triangle) obj).get_p3());
    }

    @Override
    public String toString(){
        return "p1 is: " +_p1.toString() + "p2 is: " + _p2.toString() + "p3 is: " + _p3.toString() ;
    }


    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * getNormal
     * PARAMETERS
     * Point3D
     * RETURN VALUE
     * vector
     * MEANING
     * calculates and returns normal for point3d on triangle
     **************************************************/

    public Vector getNormal(Point3D point){
        Vector vector1 = new Vector(_p1,_p2);
        Vector vector2 = new Vector(_p1,_p3);
        Vector result = vector1.crossProduct(vector2).normalize();

        result = result.scale(-1);
        if(result.getHead().getX().getCoordinate() == -0 )
            result.getHead().setX(new Coordinate(0));
         return result.normalize();
    }

    /*************************************************
     * FUNCTION
     * FindIntersections
     * PARAMETERS
     * Ray
     * RETURN VALUE
     * List<Point3D>
     * MEANING
     * calculates the intersections of ray and triangle and returns all the points
     **************************************************/
    public List<Point3D> FindIntersections(Ray ray)
    {
        List<Point3D> intersectionPoints = new ArrayList<>(1);

        Plane plane = new Plane(getNormal(null), _p3);
        if(plane.FindIntersections(ray).isEmpty())
            return intersectionPoints;

        // checking if the point is inside the triangle
        Point3D p0 = ray.getPOO();
        Point3D P = plane.FindIntersections(ray).get(0);
        Vector P_P0 = new Vector(p0, P);




        Vector N1 =  (new Vector( p0,_p1)).crossProduct(new Vector(p0, _p2)).normalize();
        Vector N2 =  (new Vector( p0,_p2)).crossProduct(new Vector(p0, _p3)).normalize();
        Vector N3 =  (new Vector( p0,_p3)).crossProduct(new Vector(p0, _p1)).normalize();

        if(N1.dotProduct(P_P0)>0 && N2.dotProduct(P_P0)>0 && N3.dotProduct(P_P0)>0)
            intersectionPoints.add(P);

        if(N1.dotProduct(P_P0)<0 && N2.dotProduct(P_P0)<0 && N3.dotProduct(P_P0)<0)
            intersectionPoints.add(P);

        return intersectionPoints;
    }


    /*************************************************
     * FUNCTION
     * IsOnTheSamePlane
     * PARAMETERS
     * Triangle
     * RETURN VALUE
     * boolean
     * MEANING
     * checks if two triangles are on the same plane
     **************************************************/
    public boolean IsOnTheSamePlane( Triangle _tril2 )
    {
        Vector v = new Vector(this.get_p1(), _tril2.get_p2());
        boolean isVertical = Util.isZero( v.dotProduct(this.getNormal(null)));
        return isVertical;
    }

    /*************************************************
     * FUNCTION
     * getLimit
     * RETURN VALUE
     * Limits
     * MEANING
     * returns the coordinates limits to create box for the geometry
     **************************************************/
    @Override
    public Limits getLimits() {
    var maxX =  Math.max(_p3.getX().getCoordinate(), Math.max(_p1.getX().getCoordinate(),_p2.getX().getCoordinate()));
        var minX =  Math.min(_p3.getX().getCoordinate(), Math.max(_p1.getX().getCoordinate(),_p2.getX().getCoordinate()));
        var maxY =  Math.max(_p3.getY().getCoordinate(), Math.max(_p1.getY().getCoordinate(),_p2.getY().getCoordinate()));
        var minY =  Math.min(_p3.getY().getCoordinate(), Math.max(_p1.getY().getCoordinate(),_p2.getY().getCoordinate()));
        var maxZ =  Math.max(_p3.getZ().getCoordinate(), Math.max(_p1.getZ().getCoordinate(),_p2.getZ().getCoordinate()));
        var minZ =  Math.min(_p3.getZ().getCoordinate(), Math.max(_p1.getZ().getCoordinate(),_p2.getZ().getCoordinate()));

        var limits = new Limits(maxX,minX,maxY,minY,maxZ,minZ);
        return limits;
    }
}
