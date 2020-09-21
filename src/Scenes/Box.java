package Scenes; /*************************************************
 * Class
 * Box
 * PARAMETERS
 * has a list of geometries and the limits of the box
 * this will be beneficial for accelerating the rendering process
 **************************************************/
//package renderer;
//package Scenes;

import geometries.Geometry;
import geometries.Plane;
import geometries.RadialGeometry;
import primitives.Limits;
import primitives.Ray;
import primitives.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static primitives.Util.swap;




public class Box {


  private   ArrayList<Geometry> _geometries;//list of geometries
  private   Limits _limits;//the limits of the box

    // ***************** Constructors ********************** //



    public Box(ArrayList<Geometry> geometries) {
        this._geometries = geometries;
        this.setLimits();
    }

    public Box()
    {}


    // ***************** Getters/Setters ********************** //

    public ArrayList<Geometry> getGeometries() {
        return _geometries;
    }

    public void setGeometries(ArrayList<Geometry> geometries) {
        this._geometries = geometries;
    }

    public Limits getLimits() {
        return _limits;
    }

    public void setLimits(Limits limits) {
        this._limits = limits;
    }

    // ***************** Operations ********************** //

    /*************************************************
     * Class
     * setLimits
     * PARAMETERS
     * none
     * MEANING
     * calculates the limits for the box
     **************************************************/
    private void setLimits()
    {
        List<Double> maxXsList = new ArrayList<>() ;
        for (Geometry geometry:_geometries)
            maxXsList.add(geometry.getLimits().getxMaxLimit());

        List<Double> minXsList = new ArrayList<>();
        for (Geometry geometry:_geometries)
            minXsList.add(geometry.getLimits().getxMinLimit());

        List<Double> maxYsList = new ArrayList<>();
        for (Geometry geometry:_geometries)
            maxYsList.add(geometry.getLimits().getyMaxLimit());

        List<Double> minYsList = new ArrayList<>();
        for (Geometry geometry:_geometries)
            minYsList.add(geometry.getLimits().getyMinLimit());

        List<Double> maxZsList = new ArrayList<>();
        for (Geometry geometry:_geometries)
            maxZsList.add(geometry.getLimits().getzMaxLimit());

        List<Double> minZsList = new ArrayList<>();
        for (Geometry geometry:_geometries)
            minZsList.add(geometry.getLimits().getzMinLimit());


        var maxX = Collections.max(maxXsList);
        var minX = Collections.min(minXsList);
        var maxY = Collections.max(maxYsList);
        var minY = Collections.min(minYsList);
        var maxZ = Collections.max(maxZsList);
        var minZ = Collections.min(minZsList);

       var limits = new Limits(maxX,minX,maxY,minY,maxZ,minZ);
      _limits = limits;
    }



    /*************************************************
     * Class
     * intersect
     * PARAMETERS
     * ray
     * MEANING
     * checks if a ray intersects with the box
     **************************************************/
 public boolean intersect( Ray r)
  {
    double tmin = ( _limits.getxMinLimit() - r.getPOO().getX().getCoordinate()) / r.getDirection().getHead().getX().getCoordinate();
    double tmax = (_limits.getxMaxLimit() - r.getPOO().getX().getCoordinate()) / r.getDirection().getHead().getX().getCoordinate();

    if (tmin > tmax)
      tmax = swap(tmin, tmin = tmax);


    double tymin =  (_limits.getyMinLimit() - r.getPOO().getY().getCoordinate()) / r.getDirection().getHead().getY().getCoordinate();
    double tymax = ( _limits.getyMaxLimit() - r.getPOO().getY().getCoordinate()) / r.getDirection().getHead().getY().getCoordinate();

    if (tymin > tymax)
     tymax = swap(tymin, tymin = tymax);

    if ((tmin > tymax) || (tymin > tmax))
      return false;

    if (tymin > tmin)
      tmin = tymin;

    if (tymax < tmax)
      tmax = tymax;

    double tzmin = (_limits.getzMinLimit() - r.getPOO().getZ().getCoordinate()) / r.getDirection().getHead().getZ().getCoordinate();
    double tzmax = (_limits.getzMaxLimit() - r.getPOO().getZ().getCoordinate()) / r.getDirection().getHead().getZ().getCoordinate();

    if (tzmin > tzmax)
     tzmax =  swap(tzmin, tzmin = tzmax);

    if ((tmin > tzmax) || (tzmin > tmax))
      return false;

    if (tzmin > tmin)
      tmin = tzmin;

    if (tzmax < tmax)
      tmax = tzmax;

    return true;
  }
}
