package modelo;

public class Direccion {
    String calle;
    String poblacion;
    String cp;

    public Direccion(){
    }

    public Direccion(String calle, String poblacion, String cp) {
        this.calle = calle;
        this.poblacion = poblacion;
        this.cp = cp;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return calle + ","+ poblacion +","+ cp;
    }

    



}
