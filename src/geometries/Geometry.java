/*************************************************
 * package
 * Geometry
 * represents a geometry
 * CONTAINS values for material, shininess and color
 * all geometries will extend this

 **************************************************/
package geometries;

import primitives.*;

import java.awt.*;
import java.util.List;

// todo: check this class shape
public abstract class Geometry {


    private Material _material = new Material();
    private double _nShininess = 1;
    private Color _emmission = new Color(0, 0, 0);


    // ***************** Constructors ********************** //


// ***************** Getters/Setters ********************** //

    public Material get_material() {
        return _material;
    }

    public void set_material(Material _material) {
        this._material = _material;
    }

    public void set_material(double _n1, double _Kd, double _Ks, double _Kr, double _Kt){
        _material.set_material(_n1,_Kd,_Ks,_Kr,_Kt);
    }

    public double get_nShininess() {
        return _nShininess;
    }

    public void set_nShininess(double _nShininess) {
        this._nShininess = _nShininess;
    }

    public Color get_emmission() {
        return _emmission;
    }

    public void set_emmission(Color _emmission) {
        this._emmission = _emmission;
    }

    public void setKs(double ks) {
        _material.set_Ks(ks);
    }

    public void setKd(double kd) {
        _material.set_Kd(kd);
    }

    public void setKr(double Kr) {
        _material.set_Kr(Kr);
    }

    public void setKt(double Kt) {
        _material.set_Kt(Kt);
    }

    // ***************** Administration ******************** //
    /*
        @Override
        public int compareTo
        public String toString
        */

    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * FindIntersections
     * PARAMETERS
     * Ray
     * RETURN VALUE
     * List<Point3D>
     * MEANING
     * calculates the intersections of ray and shape
     **************************************************/

    public abstract List<Point3D> FindIntersections(Ray ray);
    /*************************************************
     * FUNCTION
     * FindIntersections
     * PARAMETERS
     * Ray
     * RETURN VALUE
     * List<Point3D>
     * MEANING
     * calculates the intersections of ray and shape
     **************************************************/

    public abstract Vector getNormal(Point3D point);

    /*************************************************
     * FUNCTION
     * getLimit
     * RETURN VALUE
     * Limits
     * MEANING
     * returns the coordinates limits to create box
     **************************************************/
    public abstract Limits getLimits();
}

