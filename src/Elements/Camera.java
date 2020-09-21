/*************************************************
 * class
 * Camera
 * represents a virtual camera
 * CONTAINS:
 *     A three-dimensional point that is the projection center.
 *	   3 Direction vectors

 **************************************************/
package Elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;


import java.util.Map;

public class Camera {

    //Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;

    //Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;
    // ***************** Constructors ********************** //
    public Camera(){
        _P0 = new Point3D(0, 0, 10);
        _vUp = new Vector(1.0, 0.0, 0.0);
        _vTo = new Vector(0.0, 0.0, -1.0);

        _vRight = _vUp.crossProduct(_vTo);
    }

    public Camera (Camera camera){
        _P0 = camera.get_P0();
        _vUp = camera.get_vUp();
        _vTo = camera.get_vTo();
        _vRight = camera.get_vRight();
    }
    public Camera (Point3D P0, Vector vUp, Vector vTo){
        _P0 = new Point3D(P0) ;
        _vUp = new Vector(vUp) ;
        _vTo = new Vector(vTo) ;

        _vRight = _vUp.crossProduct(_vTo);
        //_vUp = _vTo.crossProduct(_vRight);

        _vTo.normalize();
        _vUp.normalize();
        _vRight.normalize();
    }

    //   public Camera (Map<String, String>attributes(;

// ***************** Getters/Setters ********************** //

    public Point3D get_P0() { return _P0; }
    public Vector get_vRight() {return _vRight;}
    public Vector get_vUp() { return _vUp;}
    public Vector get_vTo() {return _vTo;}

    public void set_P0(Point3D _P0) { this._P0 = _P0;}
    public void set_vUp(Vector _vUp) { this._vUp = _vUp;}
    public void set_vTo(Vector _vTo) {this._vTo = _vTo;}

    // ***************** Administration ********************** //
    @Override
    public String toString(){
        return "vto: " + _vTo + "\n"+
                "vup: " + _vUp + "\n" +
                "vRight: " + _vRight + "\n";
    }
    // ***************** Operations ******************** //

    /*************************************************
     * FUNCTION
     * constructRayThroughPixel
     * PARAMETERS
     * info of the screen(pixel,width,height)
     * RETURN VALUE
     * ray
     * MEANING
     * cconstructing ray between camera and the intersection point
     **************************************************/
    public Ray constructRayThroughPixel (int Nx, int Ny,
                                         double x, double y,
                                         double screenDist,
                                         double screenWidth,
                                         double screenHeight)
    {

        // Calculating the image center
        Vector vToward = _vTo.scale(screenDist);
        Point3D Pc = _P0.add(vToward);

        // Calculating x-y ratios
        double Rx = screenWidth  / Nx;
        double Ry = screenHeight / Ny;

        // Calculating P - the intersection point
        Vector vRight = new Vector(_vRight);
        Vector vUp = new Vector(_vUp);

        double xDistance = ((x - (Nx/2.0)) * Rx + 0.5 * Rx);
        double yDistance = ((y - (Ny/2.0)) * Ry + 0.5 * Ry);



        if(!isZero(xDistance))
            vRight = vRight.scale(xDistance);

        if(!isZero(yDistance))
            vUp = vUp.scale(yDistance);

        Point3D p;
        if(!isZero(xDistance) && !isZero(yDistance))
            p = Pc.add(vRight).subtract(vUp);
        else if(!isZero(xDistance) && isZero(yDistance))
           p = Pc.add(vRight);
        else if(isZero(xDistance) && !isZero(yDistance))
            p = Pc.subtract(vUp);
        else // both are zero
               p = new Point3D(Pc);


        // constructing ray between P0 and the intersection point
        Vector directionVector = new Vector(_P0, p).normalize();

        // returning the constructed ray
       return new Ray(p, directionVector); //////////////////// todo: need to check if ok
      //  return new Ray(_P0, directionVector);
    }


   /*
    public Ray constructRayThroughPixel (int Nx, int Ny, double x, double y,
                                         double screenDist, double screenWidth,
                                         double screenHeight){

        // Calculating the image center
        Vector vToward = new Vector(_vTo);
        vToward.scale(screenDist);

        Point3D Pc = new Point3D(_P0);
        Pc.add(vToward);

        // Calculating x-y ratios
        double Rx = screenWidth  / Nx;
        double Ry = screenHeight / Ny;

        // Calculating P - the intersection point
        Vector vRight = new Vector(_vRight);
        Vector vUp = new Vector(_vUp);

        vRight.scale(((x - (Nx/2.0)) * Rx + 0.5 * Rx));
        vUp.   scale(((y - (Ny/2.0)) * Ry + 0.5 * Ry));

        vRight.subtract(vUp);

        Pc.add(vRight);

        Point3D P = new Point3D(Pc);

        // constructing ray between P0 and the intersection point
        Vector ray = new Vector(_P0, P);
        ray.normalize();

        // returning the constructed ray
        return new Ray(P, ray);

    }
    */
}
