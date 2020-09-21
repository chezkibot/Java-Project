/*************************************************
 * Class
 * renderer
 * has several functions
 * does all the actions to create a scene
 * by using the functions
 **************************************************/
package renderer;

import Elements.LightSource;
import Scenes.Scene;
//import elements.LightSource;
import geometries.FlatGeometry;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
//import scene.Scenes;

import java.awt.*;
import java.util.*;
import java.util.List;




public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;

    public boolean multipleRefractionRaysInUse = false;
    public boolean multipleReflectionRaysInUse = false;
            ;

    private static class GeoPoint {
        public Geometry geometry;
        public Point3D point;
    }

    // ***************** Constructors ********************** //

    public Render(ImageWriter imageWriter, Scene scene) {
        _imageWriter = new ImageWriter(imageWriter);
        _scene = new Scene(scene);
    }

    // ***************** Operations ******************** //
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*************************************************
     * FUNCTION
     * constructReflectedRayList
     * MEANING
     * The function calculates the ray created from the reflected light ray sended from
     * light source. (normal to the geometry * Ray direction), than adding epsilon vector.

     **************************************************/
    private List<Ray> constructReflectedRayList(Vector normal, Point3D point, Ray inRay) {

        Ray main = constructReflectedRay(normal, point, inRay);
        List<Ray> list = new ArrayList<>();
        list.add(main);

        Point3D p = main.getPOO();
        Vector rayDirection = main.getDirection();
        list.add(new Ray (p, rayDirection.add(new Vector (createRandomOffset(), createRandomOffset(),0))));
        list.add(new Ray (p, rayDirection.add(new Vector (createRandomOffset(), createRandomOffset(),0))));
        list.add(new Ray (p, rayDirection.add(new Vector (createRandomOffset(), createRandomOffset(),0))));
        list.add(new Ray (p, rayDirection.add(new Vector (createRandomOffset(), createRandomOffset(),0))));
        list.add(new Ray (p, rayDirection.add(new Vector (createRandomOffset(), createRandomOffset(),0))));

        return list;
    }

    /*************************************************
     * FUNCTION
     * constructRefractedRayList
     * MEANING
     * The function calculates the refracted ray from the light ray sended from
     * light source. (normal to the geometry * Ray direction), than adding epsilon vector.
     * because we want multiple rays to solve glossy surfaces - we add 4 more rays, adding
     * to each random offset (
     **************************************************/
    private List<Ray> constructRefractedRayList(Geometry geometry, Point3D point, Ray inRay){
        List<Ray> rays = new ArrayList<>();
        Vector eps = geometry.getNormal(point);
        double angle = eps.dotProduct(inRay.getDirection());
        eps = angle <0 ? eps.scale(-2) : eps.scale(2);
        Point3D p = point.add(eps);
        Ray mainRay = new Ray(p, inRay.getDirection());
        rays.add(mainRay);
        rays.add(new Ray (p, inRay.getDirection().add(new Vector (createRandomOffset(), createRandomOffset(),0))));
        rays.add(new Ray (p, inRay.getDirection().add(new Vector (createRandomOffset(), createRandomOffset(),0))));
        rays.add(new Ray (p, inRay.getDirection().add(new Vector (createRandomOffset(), createRandomOffset(),0))));
        rays.add(new Ray (p, inRay.getDirection().add(new Vector (createRandomOffset(), createRandomOffset(),0))));
        rays.add(new Ray (p, inRay.getDirection().add(new Vector (createRandomOffset(), createRandomOffset(),0))));

        return rays;
    }

    /*************************************************
     * FUNCTION
     * createRandomOffset
     * PARAMETERS
     * None
     * MEANING
     * gets a random number in the range of 0-0.023
     * will be used for glossy surfaces
     **************************************************/

    private Double createRandomOffset() {
        Double offset = 0.023;
        Random rand = new Random();
        return (rand.nextDouble()*offset*2 - offset);
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*************************************************
     * FUNCTION
     * renderImage
     * PARAMETERS
     * None
     * MEANING
     * renders the scene into image
     **************************************************/

    public void renderImage() {
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            for (int j = 0; j < _imageWriter.getWidth(); j++) {

                if(j == 350 && i == 50)
                    j = j;
                if(j == 200 && i ==270 )
                    j = j;

                Ray ray = _scene.get_camera().constructRayThroughPixel(
                        _imageWriter.getNx(),
                        _imageWriter.getNy(),
                        j,
                        i,
                        _scene.get_screenDistance(),
                        _imageWriter.getWidth(),
                        _imageWriter.getHeight()
                );

               Map.Entry<Geometry, Point3D> entry = findClosestIntersection(ray);

                if (entry == null)
                    _imageWriter.writePixel(j , i ,_scene.get_backGround());
                else {
                    _imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(),ray, 0));
                }
            }
        }
    }

    /*************************************************
     * FUNCTION
     * renderImageWithBoxing
     * PARAMETERS
     * None
     * MEANING
     * renders the scene into image using the boxing method
     * where it calculates all the intersection with th boxes
     * this accelerates the rendering of the image
     **************************************************/
    public void renderImageWithBoxing() {

        _scene.set_geometriesInAllBoxes(_scene.get_geometries()); // need for boxes acceleration
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            for (int j = 0; j < _imageWriter.getWidth(); j++) {

                if(j == 350 && i == 50)
                    j = j;


                Ray ray = _scene.get_camera().constructRayThroughPixel(
                        _imageWriter.getNx(),
                        _imageWriter.getNy(),
                        j,
                        i,
                        _scene.get_screenDistance(),
                        _imageWriter.getWidth(),
                        _imageWriter.getHeight()
                );

                var interesectedBox = _scene.getIntersectedBox(ray);

                if(interesectedBox == null)
                    _imageWriter.writePixel(j , i ,_scene.get_backGround());
                else {
                    _scene.set_geometries(interesectedBox.getGeometries()); // need for boxes acceleration get only geometries in boxes
                    Map.Entry<Geometry, Point3D> entry = findClosestIntersection(ray);
                    _scene.set_geometries(_scene.get_geometriesInAllBoxes()); // need for boxes acceleration, get back all geometries in scene

                    if (entry == null)
                        _imageWriter.writePixel(j, i, _scene.get_backGround());
                    else {
                        _imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(), ray, 0));
                    }
                }
            }
        }
    }






    /*************************************************
     * FUNCTION
     * getSceneRayIntersections
     * PARAMETERS
     * Ray
     * RETURN VALUE
     * Map<Geometry, List<Point3D>>
     * MEANING
     * returns a list of all the intersection of ray and scene
     **************************************************/
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray){
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();

        int i=0;
        while (geometries.hasNext())
        {
            i++;
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);

            if(! geometryIntersectionPoints.isEmpty())
            intersectionPoints.put(geometry ,geometryIntersectionPoints);
        }


        return intersectionPoints;
    }



    /*************************************************
     * FUNCTION
     * getClosestPoint
     * PARAMETERS
     * Map<Geometry, List<Point3D>> intersectionPoints
     * RETURN VALUE
     * Map<Geometry, Point3D>
     * MEANING
     * returns the closest point of the intersection
     **************************************************/
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints)
    {
        double distance = Double.MAX_VALUE;
        Point3D P0 = _scene.get_camera().get_P0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

        for(Map.Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet()) {
            for (Point3D point : entry.getValue())
                if (P0.distance(point) < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = P0.distance(point);
                }
        }
        return minDistancePoint;
    }

    /*************************************************
     * FUNCTION
     * getClosestPoint
     * PARAMETERS
     * ray
     * RETURN VALUE
     * Map<Geometry, Point3D>
     * MEANING
     * returns the closest point of the intersection
     **************************************************/
    private Map<Geometry, Point3D> getClosestPoint(Ray ray)
    {
        Map<Geometry, List<Point3D>> entry = getSceneRayIntersections(ray);
        return getClosestPoint(entry);
    }


    /*************************************************
     * FUNCTION
     * calcColor
     * PARAMETERS
     * geometry, point3D, ray, int (for level of recursion)
     * RETURN VALUE
     * color
     * MEANING
     * Gets a point and calculates the color of the point
     * by taking into account all factors
     **************************************************/
    private Color calcColor(Geometry geometry,  Point3D point, Ray inRay, int level){

        if(level == RECURSION_LEVEL)
            return new Color(0,0,0);

     Color ambientLight = _scene.get_ambientLight().getIntensity();
     Color emissionLight = geometry.get_emmission();

     Iterator<LightSource> lights = _scene.getLightsIterator();

        var sumOfReflectedLights = new Color(0,0,0);

     while (lights.hasNext()) {
         LightSource light = lights.next();
    if(!occluded(light, point, geometry)) {

    Color diffuseLight = CalcDiffusiveComp(geometry.get_material().get_Kd(),
            geometry.getNormal(point), light.getL(point), light.getIntensity(point));

    Color specularLight = calcSpecularComp(geometry.get_material().get_Ks(),
            new Vector(point, _scene.get_camera().get_P0()),
            geometry.getNormal(point),
            light.getL(point),
            geometry.get_nShininess(),
            light.getIntensity(point)
    );

// sumOfReflectedLights   =  addColor(sumOfReflectedLights, addColor(diffuseLight, specularLight));
        sumOfReflectedLights   =   addColor(diffuseLight, specularLight);

    }
     }

        Color reflectedLight = new Color(0,0,0);
        Color refractedLight = new Color(0,0,0);


     // begin of recursive calls
        // Recursive call for reflected ray
        ////////////////////////////////////////////////////////////////////////////////////////////******************

        if(multipleReflectionRaysInUse)
        {
            List<Ray> reflectedRays = constructReflectedRayList(geometry.getNormal(point), point, inRay);
            for(Ray ray: reflectedRays)
            {
                Map.Entry<Geometry,Point3D> reflectedEntry = findClosestIntersection(ray);
                if(reflectedEntry!=null)
                {
                    Color temp = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), ray, level + 1);
                    temp = multiColorByNumber(temp,geometry.get_material().get_Kr());
                    double numOfRaysFraction = (1.0/reflectedRays.size());
                    temp = multiColorByNumber(temp, numOfRaysFraction );
                    reflectedLight = addColor(reflectedLight,temp);
                }
            }
        }

        else {
            ////////////////////////////////////////////////////////////////////////////////////////////*************


            Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
            var reflectedEntry = findClosestIntersection(reflectedRay);
            if (reflectedEntry != null) {
                Color reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
                double kr = geometry.get_material().get_Kr();
                reflectedLight = multiColorByNumber(reflectedColor, kr);
            }
        }

       //recursive call for refracted rays
/////////////////////////////////////////////////////////////////////////////////////////////////////
        if(multipleRefractionRaysInUse)
        {
            // Getting a List of Rays created by constructRefractedRayList function
            List<Ray> refractedRays = constructRefractedRayList(geometry, point, inRay);
            // iterating the list, adding the color of each ray's intersections points
            for(Ray refractedRay: refractedRays)
            {
                Map.Entry<Geometry, Point3D> refractedEntry = findClosestIntersection(refractedRay);

                if (refractedEntry != null) {
                    Color refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
                    refractedColor = multiColorByNumber(refractedColor,geometry.get_material().get_Kt());
                    double numOfRaysFraction = (1.0/refractedRays.size());
                    refractedLight= multiColorByNumber(refractedColor, numOfRaysFraction );
                }
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////
        else {


            var refractedRay = constructRefractedRay(geometry, point, inRay);
            var refractedEntry = findClosestIntersection(refractedRay);
            if (refractedEntry != null) {
                Color refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
                double kt = geometry.get_material().get_Kt();
                refractedLight = multiColorByNumber(refractedColor, kt);
            }
        }

        // end of recursive calls


    var allLightsButReflectedAndRefracted =   addColor( addColor(ambientLight,emissionLight) ,sumOfReflectedLights );
    var refrectedAndReflacted = addColor(reflectedLight,refractedLight);
    var result =   addColor(allLightsButReflectedAndRefracted, refrectedAndReflacted);
    if(result.getRed() < 20 && result.getRed() < 20 && result.getRed() < 20)
         result = result;
return result;
    }


    /*************************************************
     * FUNCTION
     * constructRefractedRay
     * PARAMETERS
     * geometry, Point3D, ray
     * RETURN VALUE
     * Ray
     * MEANING
     * returns a refracted ray
     **************************************************/
   private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay)
   {
       Vector normal = geometry.getNormal(point);

     var p =  point.add(normal.scale(-2));

       if (geometry instanceof FlatGeometry)
       {
           return new Ray (p, inRay.getDirection());
       }
       else
           {
           return new Ray (p, inRay.getDirection());
       }
   }

    /*************************************************
     * FUNCTION
     * constructReflectedRay
     * PARAMETERS
     * Vector, Point3D, ray
     * RETURN VALUE
     * Ray
     * MEANING
     * returns a ray that is a reflection of a primitive one
     **************************************************/
    private  Ray constructReflectedRay(Vector normal, Point3D point, Ray ray)
    {

        var A = normal.scale( ray.getDirection().dotProduct(normal)* (-2));
        var result = new Ray(point.add(normal), ray.getDirection().add(A).normalize()) ;
        return result;

/*
        Vector l = ray.getDirection().normalize();

       normal = normal.scale(-2 * l.dotProduct(normal));
       l = l.add(normal);

        Vector R = new Vector(l).normalize();

       point = point.add(normal);

        Ray reflectedRay = new Ray(point, R);

        return reflectedRay;
*/
    }

    /*************************************************
     * FUNCTION
     * addColor
     * PARAMETERS
     * two Colors
     * RETURN VALUE
     * Color
     * MEANING
     * returns color after adding one to another
     **************************************************/
    private Color addColor(Color colorA, Color colorB)
    {
        int R = colorA.getRed() + colorB.getRed();
        if (R > 255)
            R = 255;

        int G = colorA.getGreen() + colorB.getGreen();
        if (G > 255)
            G = 255;

        int B = colorA.getBlue() + colorB.getBlue();
        if (B > 255)
            B = 255;

        Color result = new Color (R, G, B);

        return result;
    }

    /*************************************************
     * FUNCTION
     * writeToImage
     * PARAMETERS
     * NOne
     * RETURN VALUE
     * void
     * MEANING
     * write to image
     **************************************************/
    public void writeToImage()
    {
        _imageWriter.writeToimage();
    }

    /*************************************************
     * FUNCTION
     * printGrid
     * PARAMETERS
     * interval
     * RETURN VALUE
     * void
     * MEANING
     * prints the grid of the image
     **************************************************/
    public void printGrid(int interval){

        int height = _imageWriter.getHeight();
        int width = _imageWriter.getWidth();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, 255, 255, 255);

            }
        }
    }

    /*************************************************
     * FUNCTION
     * findClosestIntersection
     * PARAMETERS
     * ray
     * RETURN VALUE
     * Map.Entry<Geometry, Point3D>
     * MEANING
     * finds the closest intersection of a ray
     **************************************************/
    private Map.Entry<Geometry, Point3D> findClosestIntersection(Ray ray) {

        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

        if (intersectionPoints.size() == 0)
            return null;

        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Map.Entry<Geometry, Point3D> entry = null;
        if(!closestPoint.isEmpty())
         entry = closestPoint.entrySet().iterator().next();

        return entry;

    }

    /*************************************************
     * FUNCTION
     * calcSpecularComp
     * PARAMETERS
     * double ks, shininess, Vector: V, N, L,  Color lightintesity
     * RETURN VALUE
     * Color
     * MEANING
     * calculates the light the object returns
     **************************************************/
    private Color calcSpecularComp( double ks, Vector V, Vector N, Vector L,double shininess, Color lightInensity )
    {
      V =  V.normalize();
      N =  N.normalize();
      L = L.normalize();


       Vector R = L.add(N.scale(-2 * L.dotProduct(N))).normalize();

        double k = 0;
        if (V.dotProduct(R) > 0) // prevents glowing at edges
            k = ks * Math.pow(V.dotProduct(R), shininess);

        var result = multiColorByNumber(lightInensity, k);
        return result;
    }

    /*************************************************
     * FUNCTION
     * CalcDiffusiveComp
     * PARAMETERS
     * double kd, Vector: N, L,  Color intensityLight
     * RETURN VALUE
     * Color
     * MEANING
     * The spread of light and its effect on the color of
     * the object when the light hits it from a certain direction
     **************************************************/
    private Color CalcDiffusiveComp(double kd, Vector N, Vector L, Color  intensityLight)
    {
        var k = Math.abs(kd * N.normalize().dotProduct(L.normalize()));
        var result = multiColorByNumber(intensityLight, k);
        return result;
    }


    /*************************************************
     * FUNCTION
     * multiColorByNumber
     * PARAMETERS
     * color, double k
     * RETURN VALUE
     * Color
     * MEANING
     * multiplies color by a number
     **************************************************/
    private Color multiColorByNumber(Color color, double k)
    {
        var result = new Color((int) (color.getRed() * k),
                (int) (color.getGreen() * k) ,
                (int) (color.getBlue() * k)
                );
        return  result;
    }


    /*************************************************
     * FUNCTION
     * occluded
     * PARAMETERS
     * Light, Point3D, Geometry
     * RETURN VALUE
     * boolean
     * MEANING
     * checks for a point if it's shadowed
     **************************************************/
    private boolean occluded(LightSource light, Point3D point, Geometry geometry) {

        Vector lightDirection = light.getL(point).scale(-1).normalize();


        int b =0;
        if(point.getX().getCoordinate()< 373.0 && point.getX().getCoordinate() > 372
                && point.getY().getCoordinate()<-52 && point.getY().getCoordinate()>-53)
            b =0;

        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point)).scale(2);
       // epsVector.scale(2);
       geometryPoint = geometryPoint.add(epsVector);

        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);

        // Flat geometry cannot self intersect
        if (geometry instanceof FlatGeometry){
            intersectionPoints.remove(geometry);
        }


        for (Map.Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet()) {
            if (entry.getKey().get_material().get_Kt() == 0)
                return true;

        }
        return false;
    }

}

