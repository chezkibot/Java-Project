/*************************************************
 * abstract class
 * Light
 * represents light - uses java's color
 **************************************************/
package Elements;

import java.awt.*;

public abstract class Light {
    protected Color _color;
    // ***************** Constructors ********************** //
    public Light(){_color = null;}
    public Light (Color color){_color = color;}
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(){return _color;}

}
