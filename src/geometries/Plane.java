/*************************************************
 * class
 * Plane
 * represents a plane
 * CONTAINS a point 3D and normal

 **************************************************/
package geometries;

import primitives.Limits;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;


public class Plane extends Geometry implements FlatGeometry {

    private Vector _normal;
    private Point3D _Q;

    // ***************** Constructors ********************** //
    public Plane(){}
    public Plane (Plane plane){
        _normal = plane._normal;
    }
    public Plane (Vector normal, Point3D q){
        _normal = normal.normalize();
        _Q = q;
    }
    // ***************** Getters/Setters ********************** //


    public Vector getNormal(Point3D point){
    return _normal;
    }

    public void set_normal(Vector _normal) {
        this._normal = _normal.normalize();
    }

    public Point3D get_Q() {
        return _Q;
    }

    public void set_Q(Point3D _Q) {
        this._Q = _Q;
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
     * calculates the intersections of ray and plane and returns all the points
     **************************************************/

    @Override
    public List<Point3D> FindIntersections(Ray ray){

        List<Point3D> intersectionPoint = new ArrayList<Point3D>(1);

        Vector N = _normal;
        Vector V = ray.getDirection();

        double t = (N.dotProduct(new Vector(get_Q(),ray.getPOO()))* -1)/
                N.dotProduct(ray.getDirection());

        if(t > 0) {
            V = V.scale(t);

            Point3D P = ray.getPOO().add(V);
            intersectionPoint.add(P);
        }
        ///

        return intersectionPoint;
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
        return new Limits();
    }

}
