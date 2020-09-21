/*************************************************
 * class
 * Point2D
 * represents a point in a Two-dimensional space -(x,y)
 * Contains two Coordinates (x,y)
 **************************************************/
package primitives;
import static jdk.nashorn.internal.objects.NativeMath.round;
import static primitives.Util.*;


public class Point2D  {

    protected Coordinate _x;
    protected Coordinate _y;

    // ***************** Constructors ********************** //

    public Point2D(){}

    public Point2D (double x, double y)
    {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
    }

    public Point2D(Coordinate x, Coordinate y)
    {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
    }

    public Point2D(Point2D point2D){
        _x = point2D._x;
        _y = point2D._y;
    }
    // ***************** Getters/Setters ********************** //
    public Coordinate getX(){return _x;};
    public Coordinate getY(){return _y;};
    public void setX(Coordinate x){_x = x;}
    public void setY(Coordinate y){_y = y;}
    // ***************** Administration ******************** //



    /*************************************************
     * FUNCTION
     * compareTo
     * PARAMETERS
     * Point2D
     * RETURN VALUE
     * 0 if they're same. else -1
     * MEANING
     * This functions compares values of 2 Point2D
     **************************************************/
    public int compareTo(Point2D point2D)
    {
        if(this._x.getCoordinate()==point2D._x.getCoordinate() && this._y.getCoordinate()==point2D._y.getCoordinate() )
            return 0;
        return 1;
    }

    @Override
    public String toString()
    {
        return  "("+ String.format("%.2f" , _x.getCoordinate()) + ", " + String.format("%.2f" , _y.getCoordinate());
    }



}
