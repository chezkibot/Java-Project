
/*************************************************
 * class
 * Coordinate
 * represents a value on number line
 * double value for Coordinate
 **************************************************/
package primitives;
import static primitives.Util.*;


public  class Coordinate {


    private double _coordinate;//value of number on number line

    // ***************** Constructors ********************** //
    public Coordinate(){}

    public Coordinate(double coordinate){
        _coordinate = coordinate;
    }

    public Coordinate(Coordinate coordinate){
        _coordinate = coordinate.getCoordinate();
    }

    // ***************** Getters/Setters ********************** //
    public double getCoordinate(){
        return _coordinate;
    }

    public void setcoordinate(double coordinate){_coordinate = coordinate;}

    // ***************** Administration ******************** //
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Coordinate))return false;

        return isZero(usubtract(_coordinate, ((Coordinate)obj)._coordinate));
    }

    public int compareTo(Object obj) {
        if(equals(obj))
            return 0;
        return 1;
    }


    @Override
    public String toString()
    {
        return "" + _coordinate;
    }

    // ***************** Operations ******************** //
    public Coordinate  add(Coordinate other){
        return  new Coordinate(uadd(_coordinate, other.getCoordinate()));
    }

    /*************************************************
     * FUNCTION
     * subtract
     * PARAMETERS
     * Coordinate - to substract
     * RETURN VALUE
     * A Coordinate : the new coordinate result of Coordinate minus the other
     * MEANING
     * This functions returns a new coordinate multiplied by a number
     **************************************************/
    public Coordinate subtract(Coordinate other){
        return  new Coordinate(usubtract(_coordinate, other.getCoordinate()));
    }


    /*************************************************
     * FUNCTION
     * scale
     * PARAMETERS
     * double - a number
     * RETURN VALUE
     * A Coordinate : the new coordinate multiplied by num
     * MEANING
     * This functions returns a new coordinate multiplied by a number
     **************************************************/
    public Coordinate scale() {
        return scale();
    }

    /*************************************************
     * FUNCTION
     * scale
     * PARAMETERS
     * double - a number to scale
     * RETURN VALUE
     * A Coordinate : the new coordinate multiplied by num
     * MEANING
     * This functions returns a new coordinate multiplied by a number
     **************************************************/
    public Coordinate scale(double num) {
        return new Coordinate(uscale(_coordinate, num));
    }


    /*************************************************
     * FUNCTION
     * multiply
     * PARAMETERS
     * Coordinate
     * RETURN VALUE
     * A Coordinate: the new coordinate multiplied by num
     * MEANING
     * This functions returns a new coordinate multiplied by other Coordinate
     **************************************************/
    public Coordinate multiply(Coordinate other) {
        return new Coordinate(uscale(_coordinate, other.getCoordinate()));
    }


}