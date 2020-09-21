/*************************************************
 * class
 * LightSource
 * represents LightSource - for the source of light
 **************************************************/
package Elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public interface LightSource {
    public abstract Color getIntensity(Point3D point);
     public abstract Vector getL(Point3D point); // light to point vector
}
