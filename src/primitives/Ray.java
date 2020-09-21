
/*************************************************
 * class
 * Ray
 * represents a ray - point of origin (3D) and a direction to where it goes
 * CONTAINS:
 * Point3D - for the point of origin
 * vector - for the direction of the ray
 **************************************************/

package primitives;

public class Ray {

    // Point of origin
    private Point3D _POO;
    // Ray direction
    private Vector _direction;

    // ***************** Constructors ********************** //
    public Ray(){
        _POO = new Point3D(0,0,0);
        _direction = new Vector(0,0,-1);
    }
    public Ray(Ray ray){
        this._POO = new Point3D(ray.getPOO());
        this._direction = ray.getDirection().normalize();
    }
    public Ray(Point3D poo, Vector direction){
        this._POO = new Point3D(poo);
        this._direction = new Vector(direction).normalize() ;
    }
    // ***************** Getters/Setters ********************** //

    public void setPOO(Point3D POO){
        this._POO = POO;
    }
    public void setDirection(Vector direction){
        _direction = direction.normalize();
    }
    public Vector getDirection(){
        return _direction;
    }
    public Point3D getPOO(){
        return new Point3D(_POO);
    }
}
