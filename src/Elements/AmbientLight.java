/*************************************************
 * class
 * AmbientLight
 * source of environmental lighting
 * is represented by a light source that is constant
 * in its intensity and constant in color, and affects all
 * Objects in the scene evenly

 **************************************************/

package Elements;

import primitives.Coordinate;

import java.awt.*;
import java.util.*;

import static primitives.Util.isZero;
import static primitives.Util.usubtract;

public class AmbientLight extends Light {
    private  double _Ka = 0.1;

    // ***************** Constructors ********************** //
    public AmbientLight(){
        _color = new Color(255,255,255);
    }
    public AmbientLight(AmbientLight aLight){
        super(aLight.getColor());
    }

    public AmbientLight(Color color, double ka) {
        _Ka = ka;
        _color = color;
    }

    public AmbientLight(int r, int g, int b){
        super(new Color(r,g,b));
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Coordinate))return false;

        return isZero(usubtract(_Ka, ((AmbientLight)obj)._Ka)) &&
                _color.equals(((AmbientLight)obj)._color) ;
    }

    public int compareTo(Object obj) {
        if( equals(obj))
            return 0;
        return 1;
    }

    @Override
    public String toString()
    {
        return String.format( "ka is: {0}, and color is: {1}",_Ka ,_color);
    }

   // public AmbientLight(Map<String, String> attributes);
    // ***************** Getters/Setters ********************** //
    public Color getColor(){return _color;}
    public void setColor(Color color){_color = color;}
    public double getKa(){return _Ka;}

    /*************************************************
     * FUNCTION
     * getIntensity
     * RETURN VALUE
     * intensity of light
     **************************************************/
    public Color getIntensity()
    {
        return new Color((int)(_color.getRed() * _Ka),
                (int)(_color.getGreen() * _Ka),
                (int)(_color.getBlue() * _Ka)
        );
    }




}
