/*************************************************
 * class
 * Point 3D
 * represents a point in a three-dimensional space -(x,y,z)
 * Contains 3 Coordinates (x,y,z)
 * inherits from Point2D
 **************************************************/


package primitives;

import static jdk.nashorn.internal.objects.NativeMath.round;
import static primitives.Util.isZero;
import static primitives.Util.usubtract;

public class Point3D extends Point2D {


    protected Coordinate _z;//coordinate for z {x,y was inherited}

    // ***************** Constructors ********************** //
    public Point3D(){}

    public Point3D(Coordinate x, Coordinate y, Coordinate z)
    {
        super(x,y);
        _z = new Coordinate(z);
    }

    public Point3D(double x, double y, double z)
    {
        super(x,y);
        _z = new Coordinate(z);
    }

    public Point3D(Point3D point3D)
    {
        _x = new Coordinate(point3D._x) ;
        _y = new Coordinate(point3D._y);
        _z = new Coordinate(point3D._z);
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getZ(){return _z;}

    public void setZ(Coordinate z){_z = new Coordinate(z);}

    // ***************** Administration ******************** //

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Point3D))return false;

        return _x.equals(((Point3D)obj).getX())
                 && _y.equals(((Point3D)obj).getY())
                 && _z.equals(((Point3D)obj).getZ());
    }

    public int compareTo(Point3D point3D)
    {
       if(equals(point3D))
           return 0;
       return 1;
    }

    public String toString(){
        return super.toString()+ ", " + String.format("%.2f", _z.getCoordinate()) + ")";
    }

    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * add
     * PARAMETERS
     * vector
     * RETURN VALUE
     * modifies the point
     * MEANING
     * This functions add a vector to a Point3D
     **************************************************/

    public Point3D add(Vector vector){

        return new Point3D(
                _x.add(vector.getHead()._x).getCoordinate(),
                _y.add(vector.getHead()._y).getCoordinate(),
                _z.add(vector.getHead()._z).getCoordinate()
        );
    }

    /*************************************************
     * FUNCTION
     * subtract
     * PARAMETERS
     * vector
     * RETURN VALUE
     * modifies the point
     * MEANING
     * This functions substracts vector from a Point3D
     **************************************************/
    public Point3D subtract(Vector vector)
    {
       var x = _x.subtract(vector.getHead()._x);
      var  y = _y.subtract(vector.getHead()._y);
       var z = _z.subtract(vector.getHead()._z);
        return new Point3D(x.getCoordinate(),y.getCoordinate(),z.getCoordinate());
    }


    /*************************************************
     * FUNCTION
     * distance
     * PARAMETERS
     * Point3D
     * RETURN VALUE
     * a number
     * MEANING
     * This functions calculates the distance between 2 Point3D's
     **************************************************/
    public double distance(Point3D point) {
        var xDistance = Math.pow((_x.subtract(point._x)).getCoordinate(), 2);
        var yDistance = Math.pow((_y.subtract(point._y)).getCoordinate(), 2);
        var zDistance = Math.pow((_z.subtract(point._z)).getCoordinate(), 2);

        var result = Math.sqrt(xDistance + yDistance + zDistance);
        return result;
    }
}