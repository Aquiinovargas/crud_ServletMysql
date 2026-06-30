package modelos;

/**
 *
 * @author aquinojr
 */
public class Alumno {

    private int NL;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private double DDI;
    private double DWI;
    private double ECBD;

    public Alumno() {
    }

    public Alumno(int NL, String nombre, String apPaterno, String apMaterno) {
        this.NL = NL;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
    }

    public Alumno(int NL, String nombre, String apPaterno, String apMaterno, double DDI, double DWI, double ECBD) {
        this.NL = NL;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.DDI = DDI;
        this.DWI = DWI;
        this.ECBD = ECBD;
    }

    public int getNL() {
        return NL;
    }

    public void setNL(int NL) {
        this.NL = NL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public double getDDI() {
        return DDI;
    }

    public void setDDI(double DDI) {
        this.DDI = DDI;
    }

    public double getDWI() {
        return DWI;
    }

    public void setDWI(double DWI) {
        this.DWI = DWI;
    }

    public double getECBD() {
        return ECBD;
    }

    public void setECBD(double ECBD) {
        this.ECBD = ECBD;
    }

    public double getPromedio() {
        return (DDI + DWI + ECBD) / 3.0;
    }

}