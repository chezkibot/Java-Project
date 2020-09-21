
/*************************************************
 * class
 * Material
 * represents a type of Material
 * CONTAINS (all Doubles) values for:
 * _Kd - Diffusion attenuation coefficient
 * _Ks - Specular attenuation coefficient
 * _Kr - Reflection coefficient (1 for mirror)
 * _Kt - Refraction coefficient (1 for transparent)
 * _n - Refraction index
 **************************************************/


package primitives;

public class Material {


    private double _Kd; // Diffusion attenuation coefficient
    private double _Ks; // Specular attenuation coefficient
    private double _Kr; // Reflection coefficient (1 for mirror)
    private double _Kt; // Refraction coefficient (1 for transparent)
    private double _n1; // Refraction index
    private double _n2; // Refraction index


    // ***************** Constructors ********************** //
    public Material()
    {
        _Kd = 1;
        _Ks = 1;
        _Kr = 0;
        _Kt = 0;
        _n1 = 1;
        _n2 =1;
    }
    public Material(Material material){
        _Kd = material._Kd;
        _Ks = material._Ks;
        _Kr = material._Kr;
        _Kt = material._Kt;
        _n1 = material._n1;
        _n2 = material._n2;
    }

    public Material(double _n1, double _Kd, double _Ks, double _Kr, double _Kt) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._Kr = _Kr;
        this._Kt = _Kt;
        this._n1 = _n1;
    }

// ***************** Getters/Setters ********************** //

    public void set_material(double _n1, double _Kd, double _Ks, double _Kr, double _Kt){
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._Kr = _Kr;
        this._Kt = _Kt;
        this._n1 = _n1;
    }

    public double get_Kd() {
        return _Kd;
    }

    public void set_Kd(double _Kd) {
        this._Kd = _Kd;
    }

    public double get_Ks() {
        return _Ks;
    }

    public void set_Ks(double _Ks) {
        this._Ks = _Ks;
    }

    public double get_Kr() {
        return _Kr;
    }

    public void set_Kr(double _Kr) {
        this._Kr = _Kr;
    }

    public double get_Kt() {
        return _Kt;
    }

    public void set_Kt(double _Kt) {
        this._Kt = _Kt;
    }

    public double get_n1() {
        return _n1;
    }

    public void set_n1(double _n1) {
        this._n1 = _n1;
    }

    public double get_n2() {
        return _n2;
    }

    public void set_n2(double _n2) {
        this._n1 = _n2;
    }
}
