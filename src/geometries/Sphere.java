/*************************************************
 * class
 * sphere
 * represents a sphere in 3D
 * CONTAINS a 3D point for the center of the sphere and radius

 **************************************************/

package geometries;

import primitives.Limits;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sphere extends RadialGeometry {

    private Point3D _center;
    // ***************** Constructors ********************** //
    public Sphere(){
        super(0);
    }
    public Sphere (Sphere sphere){
        super(sphere._radius);
        _center = sphere._center;
    }
    public Sphere(double radius, Point3D center){
        super(radius);
        _center = center;
    }
   // public Sphere(Map<String, String> attributes);
    // ***************** Getters/Setters ********************** //
    public Point3D getCenter(){
        return _center;
    }
    public void setCenter(Point3D center){
        _center = center;
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
     * calculates the intersections of ray and sphere and returns all the points
     **************************************************/

    public List<Point3D> FindIntersections(Ray ray)
    {
        List<Point3D> intersectionsPoints = new ArrayList<Point3D>(2);

        Vector L = new Vector(ray.getPOO(), _center);
        double tm = L.dotProduct(ray.getDirection());
        double d = Math.sqrt(Math.pow(L.length(),2) -Math.pow(tm,2));
        if(d > _radius)
            return intersectionsPoints;

        double th = Math.sqrt(Math.pow(_radius, 2) - Math.pow(d, 2));
        double t1 = tm - th;
        double t2 = tm + th;

        if(t1 > 0)
        {
            Vector v1 = ray.getDirection().scale(t1);
            intersectionsPoints.add(new Point3D(ray.getPOO().add(v1)));
        }

        if(t2 > 0)
        {
            Vector v2 = ray.getDirection().scale(t2);
            intersectionsPoints.add(new Point3D(ray.getPOO().add(v2)));
        }

        return intersectionsPoints;
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
    public Vector getNormal(Point3D point){
        Vector V = new Vector(_center, point).normalize();
        return V;
    }

    /*************************************************
     * FUNCTION
     * getLimit
     * RETURN VALUE
     * Limits
     * MEANING
     * returns the coordinates limits to create box for x's y's and z's
     **************************************************/
    @Override
    public Limits getLimits() {
        var limits = new Limits(
                 _center.getX().getCoordinate() + _radius,
                _center.getX().getCoordinate() - _radius,
                _center.getY().getCoordinate() + _radius,
                _center.getY().getCoordinate() - _radius,
                _center.getZ().getCoordinate() + _radius,
                _center.getZ().getCoordinate() - _radius);
        return limits;
    }
}
