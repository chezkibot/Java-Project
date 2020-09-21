
/*************************************************
 * class
 * vector
 * represents a vector
 * Contains a Point3D that represts head of vector
 **************************************************/

package primitives;
import static primitives.Util.*;


public class Vector  {

    //head of vector\
    private Point3D _head;


    // ***************** Constructors ********************** //

    public Vector(){}
    public Vector(Point3D head)
    {
        _head = head;
    }

    public Vector(Vector vector)
    {
        _head = vector._head;
    }

    public Vector(double xHead, double yHead, double zHead)
    {
        _head = new Point3D(xHead,yHead,zHead);
    }

    public Vector(Point3D p1, Point3D p2){
        Coordinate xHead = p2._x.subtract(p1._x);
        Coordinate  yHead = p2._y.subtract(p1._y);
        Coordinate zHead = p2._z.subtract(p1._z);

        _head = new Point3D(xHead.getCoordinate(),yHead.getCoordinate(),zHead.getCoordinate());
    }


    // ***************** Getters/Setters ********************** //

    public Point3D getHead(){return _head;}
    public void setHead(Point3D head){_head = head;}


    // ***************** Administration ******************** //
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Vector))return false;

        return _head.equals(((Vector)obj)._head);
    }

    public int compareTo(Vector vector){
        if(equals(vector))
            return 0;
        return 1;
    }

    public String toString()
    {
        return _head.toString();
    }


    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * add
     * PARAMETERS
     * vector
     * RETURN VALUE
     * modifies the vector
     * MEANING
     * This functions add a vector to another
     **************************************************/
    public Vector add (Vector vector ){
       return new Vector( _head.add(vector));
    }

    /*************************************************
     * FUNCTION
     * subtract
     * PARAMETERS
     * vector - to substract from
     * RETURN VALUE
     * modifies the vector
     * MEANING
     * This functions substracts vector from another
     **************************************************/
    public Vector subtract (Vector vector){
        Vector newVector = new Vector(_head.subtract(vector));

        if(isZero(newVector.length()))
            throw new ArithmeticException();
        return  newVector;
    }
    /*************************************************
     * FUNCTION
     * scale
     * PARAMETERS
     * double (scalingFactor)
     * RETURN VALUE
     * modifies the vector
     * MEANING
     * This functions scales the vector by given scaling factor
     **************************************************/
    public Vector scale(double scalingFactor){
        if(scalingFactor == 0)
            throw new ArithmeticException();

        return new Vector( _head._x.scale(scalingFactor).getCoordinate(),
                _head._y.scale(scalingFactor).getCoordinate(),
                _head._z.scale(scalingFactor).getCoordinate());
    }
    /*************************************************
     * FUNCTION
     * crossProduct
     * PARAMETERS
     * vector
     * RETURN VALUE
     * new vector
     * MEANING
     * returns vector - cross product calculation between vectors
     **************************************************/

    public Vector crossProduct(Vector vector){

        double x1 = this.getHead().getX().getCoordinate();
        double y1 = this.getHead().getY().getCoordinate();
        double z1 = this.getHead().getZ().getCoordinate();

        double x2 = vector.getHead().getX().getCoordinate();
        double y2 = vector.getHead().getY().getCoordinate();
        double z2 = vector.getHead().getZ().getCoordinate();

        Vector result = new Vector(new Point3D(
                new Coordinate( usubtract(uscale(y1, z2), uscale(z1, y2))),
                new Coordinate( usubtract(uscale(z1, x2), uscale(x1, z2))),
                new Coordinate( usubtract(uscale(x1, y2), uscale(y1, x2)))));
        if(isZero(result.length()))
            throw new ArithmeticException();
        return result;
    }

    /*************************************************
     * FUNCTION
     * length
     * PARAMETERS
     * none
     * RETURN VALUE
     * nubmer
     * MEANING
     * returns caluclation for length of vector
     **************************************************/

    public double length(){
        var sum =  Math.pow(_head._x.getCoordinate(),2 ) + Math.pow(_head._y.getCoordinate(),2 ) + Math.pow(_head._z.getCoordinate(),2 );
        return Math.sqrt(sum);
    }

    /*************************************************
     * FUNCTION
     * normalize
     * PARAMETERS
     * none
     * RETURN VALUE
     * modifies the vector
     * MEANING
     * normalizes the vector (so length of it will be 1)
     **************************************************/
    public Vector normalize(){
        // Throws exception if length = 0
        if (isZero(length()))
            throw new ArithmeticException();

        var a  = _head._x.getCoordinate()/length();
        var b  = _head._y.getCoordinate()/length();
        var c  = _head._z.getCoordinate()/length();

        Vector result = new Vector(a,b,c);
        return result;
    }

    /*************************************************
     * FUNCTION
     * dotProduct
     * PARAMETERS
     * vector
     * RETURN VALUE
     * number
     * MEANING
     * calculates dot product between vectors (X1*x2 +Y1*Y2 +Z1*Z2)
     **************************************************/
    public double dotProduct(Vector vector){
        var xHead = _head._x.scale(vector._head._x.getCoordinate()).getCoordinate();
        var yHead = _head._y.scale(vector._head._y.getCoordinate()).getCoordinate();
        var zHead = _head._z.scale(vector._head._z.getCoordinate()).getCoordinate();

        return xHead + yHead + zHead;
    }

}