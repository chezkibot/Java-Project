/*************************************************
 * class
 * PointLight
 * represents PointLight - represents a PointLight
 * contains a point3D for the position
 * and parameters for the color
 **************************************************/
package Elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class PointLight extends Light implements LightSource {
    Point3D _position;
    double _Kc, _Kl, _Kq;
    // ***************** Constructors ********************** //


    public PointLight(Color color, Point3D _position, double _Kc, double _Kl, double _Kq) {
        _color = color;
        this._position = _position;
        this._Kc = _Kc;
        this._Kl = _Kl;
        this._Kq = _Kq;
    }

    // ***************** Getters/Setters ********************** //
    @Override
    public Color getIntensity(Point3D point)
    {
        double distance = _position.distance(point);

        double k = 1/(_Kc + _Kl*distance + _Kq*Math.pow(distance, 2));

        if (k > 1) k = 1;

        return new Color((int)(_color.getRed() * k),
                (int)(_color.getGreen() * k),
                (int)(_color.getBlue() * k));
    }
    /*************************************************
     * FUNCTION
     * getL
     * PARAMETERS
     * Point#D
     * RETURN VALUE
     * boolean
     * Vector
     * L is the vector that connects the light source
     To a certain point
     **************************************************/

    @Override
    public Vector getL(Point3D point) { return new Vector (_position, point); }

}
