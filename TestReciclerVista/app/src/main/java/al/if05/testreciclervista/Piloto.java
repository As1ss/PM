package al.if05.testreciclervista;

public class Piloto {
    private String nombre;
    private int posicion;

    public Piloto() {

    }

    public Piloto(String nombre, int posicion) {
        this.nombre=nombre;
        this.posicion=posicion;
    }

    public int getPosicion() {
        return posicion;
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }




}
