/*************************************************
 * class
 * SpotLight
 * represents a spot light for area
 * contains a vector for the direction
 * and inherits from PointLight
 **************************************************/
package Elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class SpotLight extends PointLight {
    private Vector _direction;

    // ***************** Constructor ********************** //

    public SpotLight(Color color, Point3D position, Vector direction,
                     double kc, double kl, double kq){
        super(color,position,kc,kl,kq);
        _direction = direction.normalize();
    }


    // ***************** Getters/Setters ********************** //

    /*************************************************
     * FUNCTION
     * getIntensity
     * parameters
     * Point3D
     * RETURN VALUE
     * intensity of light
     **************************************************/
    public Color getIntensity(Point3D point)
    {
        Vector l = getL(point).normalize();

        double k = Math.abs(_direction.dotProduct(l));
        if (k > 1) k = 1; // doesn't allow light magnification

        return new Color((int)(super.getIntensity(point).getRed() * k),
                (int)(super.getIntensity(point).getGreen() * k),
                (int)(super.getIntensity(point).getBlue()  * k));
    }
}
