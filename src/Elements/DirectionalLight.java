
/*************************************************
 * class
 * DirectionalLight
 * represents the direction of the light
 * extends light and implements the ligh source

 **************************************************/
package Elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class DirectionalLight extends Light implements LightSource {
    private Vector _direction;
    // ***************** Constructors ********************** //
    public DirectionalLight(Color color, Vector direction){
        super(color);
        _direction = direction;
    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(Point3D point){return getIntensity();}
    public Vector getDirection(){return new Vector(_direction);}
    public void setDirection(Vector _direction){_direction = _direction;}

    /*************************************************
     * FUNCTION
     * getL
     * PARAMETERS
     * Point#D
     * RETURN VALUE
     * Vector
     * L is the vector that connects the light source
       To a certain point
     **************************************************/
    @Override
    public Vector getL(Point3D point){return getDirection();}
}
