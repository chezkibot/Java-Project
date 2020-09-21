/*************************************************
 * class
 * Limits
 * contains limit for geometry/s
 * double value for Coordinates on x,y,x
 * two for each (x for 1 to 7,y from 3,8, z from 9,-4)
 **************************************************/

package primitives;

import geometries.Geometry;

import java.util.ArrayList;

public class Limits {


    private double xMaxLimit;
    private double xMinLimit;
    private double yMaxLimit;
    private double yMinLimit;
    private double zMaxLimit;
    private double zMinLimit;


    // ***************** Constructors ********************** //

    public Limits()
    {
        this.xMaxLimit = Double.MAX_VALUE;
        this.xMinLimit = Double.MIN_VALUE;
        this.yMaxLimit = Double.MAX_VALUE;
        this.yMinLimit = Double.MIN_VALUE;
        this.zMaxLimit = Double.MAX_VALUE;
        this.zMinLimit = Double.MIN_VALUE;
    }

    public Limits(double xMaxLimit, double xMinLimit, double yMaxLimit, double yMinLimit, double zMaxLimit, double zMinLimit) {
        this.xMaxLimit = xMaxLimit;
        this.xMinLimit = xMinLimit;
        this.yMaxLimit = yMaxLimit;
        this.yMinLimit = yMinLimit;
        this.zMaxLimit = zMaxLimit;
        this.zMinLimit = zMinLimit;
    }

// ***************** Getters/Setters ********************** //


    public double   getxMaxLimit() {
        return xMaxLimit;
    }

    public void setxMaxLimit(int xMaxLimit) {
        this.xMaxLimit = xMaxLimit;
    }

    public double getxMinLimit() {
        return xMinLimit;
    }

    public void setxMinLimit(int xMinLimit) {
        this.xMinLimit = xMinLimit;
    }

    public double getyMaxLimit() {
        return yMaxLimit;
    }

    public void setyMaxLimit(int yMaxLimit) {
        this.yMaxLimit = yMaxLimit;
    }

    public double getyMinLimit() {
        return yMinLimit;
    }

    public void setyMinLimit(int yMinLimit) {
        this.yMinLimit = yMinLimit;
    }

    public double getzMaxLimit() {
        return zMaxLimit;
    }

    public void setzMaxLimit(int zMaxLimit) {
        this.zMaxLimit = zMaxLimit;
    }

    public double getzMinLimit() {
        return zMinLimit;
    }

    public void setzMinLimit(int zMinLimit) {
        this.zMinLimit = zMinLimit;
    }

}












