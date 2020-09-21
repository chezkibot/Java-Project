/*************************************************
 * Class
 * scene
 * PARAMETERS
 * contains all the entities that create the scene

 **************************************************/
package Scenes;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.LightSource;
import geometries.Geometry;
import primitives.Ray;
import Scenes.Box;


import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Scene {
    private String _sceneName;
    private Color _backGround;
    private AmbientLight _ambientLight ;
    private Camera _camera;
    private double _screenDistance;
    private List<Geometry> _geometries;



    private List<Geometry> _geometriesInAllBoxes;
    private List<LightSource> _lights; //= new ArrayList<LightSource>();
    private List<Box> _boxes;




// ***************** Constructors ********************** //


    public Scene(){
        _backGround = new Color(0, 0, 0);
        _ambientLight = new AmbientLight();
        _camera = new Camera();
        _screenDistance = 100;
        _geometries = new ArrayList<>();
        _lights = new ArrayList<LightSource>();
        _boxes = new ArrayList<Box>();
    }

    public Scene(String sceneName, AmbientLight aLight, Color background,
                 Camera camera, double screenDistance, List<LightSource> lights){

        _sceneName = sceneName;
        _backGround =  background;
        _ambientLight = new AmbientLight(aLight);
        _camera = new Camera(camera);
        _screenDistance = screenDistance;
        _geometries = new ArrayList<>();
        _lights = lights;
        _boxes = new ArrayList<Box>();
    }

    public Scene (Scene scene){
        _backGround = scene.get_backGround();
        _ambientLight = scene.get_ambientLight();
        _geometries = scene.get_geometries();
        _camera = scene.get_camera();
        _screenDistance = scene._screenDistance;
        _lights = scene._lights;
        _boxes = scene.get_boxes();
    }

    // ***************** Getters/Setters ********************** //

    public void set_boxes(List<Box> boxes){_boxes = boxes;}

    public List<Box> get_boxes(){return _boxes;}

    public String get_sceneName() {
        return _sceneName;
    }

    public void set_sceneName(String _sceneName) {
        this._sceneName = _sceneName;
    }

    public Color get_backGround() {
        return _backGround;
    }

    public void set_backGround(Color _backGround) {
        this._backGround = _backGround;
    }

    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    public void set_ambientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public Camera get_camera() {
        return _camera;
    }

    public void set_camera(Camera _camera) {
        this._camera = _camera;
    }

    public double get_screenDistance() {
        return _screenDistance;
    }

    public void set_screenDistance(double _screenDistance) {
        this._screenDistance = _screenDistance;
    }

    public List<Geometry> get_geometries() {
        return _geometries;
    }

    public void set_geometries(List<Geometry> _geometries) {
        this._geometries = _geometries;
    }

    public List<LightSource> get_lights() {
        return _lights;
    }

    public void set_lights(List<LightSource> _lights) {
        this._lights = _lights;
    }

    public List<Geometry> get_geometriesInAllBoxes() {
        return _geometriesInAllBoxes;
    }

    public void set_geometriesInAllBoxes(List<Geometry> _geometriesInAllBoxes) {
        this._geometriesInAllBoxes = _geometriesInAllBoxes;
    }

    // ***************** Administration ******************** //

    @Override
    public String toString() {
        return "Scenes{" +
                "_sceneName='" + _sceneName + '\'' +
                ", _backGround=" + _backGround +
                ", _ambientLight=" + _ambientLight +
                ", _camera=" + _camera +
                ", _screenDistance=" + _screenDistance +
                ", _geometries=" + _geometries +
                '}';
    }




    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * addGeometry
     * PARAMETERS
     * Geometry
     * RETURN VALUE
     * void
     * MEANING
     * adds a geometry to the scene
     **************************************************/
    public void addGeometry(Geometry geometry){
        _geometries.add(geometry);
    }


    public Iterator<Geometry> getGeometriesInAllBoxesIterator() {return _geometriesInAllBoxes.iterator();}

    /*************************************************
     * FUNCTION
     * getGeometriesIterator
     * PARAMETERS
     * None
     * RETURN VALUE
     * Iterator<Geometry>
     * MEANING
     * returns iterator for the geometries
     **************************************************/
    public Iterator<Geometry> getGeometriesIterator(){
        return _geometries.iterator();
    }


   // private Iterator<Geometry> getAll


    public Iterator<Box> getBoxesIterator() {
        return _boxes.iterator();
    }

    /*************************************************
     * FUNCTION
     * addLight
     * PARAMETERS
     * LightSource
     * RETURN VALUE
     * void
     * MEANING
     * adds a light to the scene
     **************************************************/
    public void addLight(LightSource light) {
        _lights.add(light);
    }

    /*************************************************
     * FUNCTION
     * getLightsIterator
     * PARAMETERS
     * None
     * RETURN VALUE
     * Iterator<LightSource>
     * MEANING
     * returns iterator for the Lights
     **************************************************/
    public Iterator<LightSource> getLightsIterator(){
        return _lights.iterator();
    }


    /*************************************************
     * FUNCTION
     * getIntersectedBox
     * PARAMETERS
     * ray
     * RETURN VALUE
     * Box
     * MEANING
     * returns if the given ray intersects with the box -
     * return the box
     **************************************************/

    public Box getIntersectedBox(Ray ray)
    {
        Iterator<Box> boxes = getBoxesIterator();
        while (boxes.hasNext())
        {
            Box box = boxes.next();
            if(box.intersect(ray))
                return box;
        }
        return null;
    }

}
