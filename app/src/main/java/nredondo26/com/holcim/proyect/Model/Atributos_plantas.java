package nredondo26.com.holcim.proyect.Model;

public class Atributos_plantas {

    String nombre;
    String imagen;
    String getidplanta;


    public Atributos_plantas(String nombre, String imagen, String getidplanta ) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getGetidplanta() {
        return getidplanta;
    }

    public void setGetidplanta(String getidplanta) {
        this.getidplanta = getidplanta;
    }
}
