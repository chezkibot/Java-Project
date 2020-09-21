/*************************************************
 * abstract class
 * RadialGeometry
 * represents geometries that contain radius
 **************************************************/
package geometries;

public abstract class RadialGeometry extends Geometry {


    protected double _radius;

    // ***************** Constructors ********************** //
    public RadialGeometry(RadialGeometry other) {
        _radius = other._radius;
    }

    public RadialGeometry(double radius) {
        _radius = radius;
    }
// ***************** Getters/Setters ********************** //

    public double get_radius() {
        return _radius;
    }

    public void set_radius(double _radius) {
        this._radius = _radius;
    }

// ***************** Administration ******************** //

    public int compareTo(RadialGeometry other) {
        if (_radius == other._radius)
            return 0;
        return 1;
    }

    public String toString() {
        return "the radius is: " + _radius;
    }
// ***************** Operations ******************** //
}




