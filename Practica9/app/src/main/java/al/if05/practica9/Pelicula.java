package al.if05.practica9;

import java.io.Serializable;

public class Pelicula implements Serializable {

    private int id;
    private String titulo;
    private String director;
    private String ano;
    private String[] actores;
    private String sinopsis;
    private String imagenFondo;
    private int puntuacion;

    private boolean vista;

    public Pelicula() {

    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public String getAno() {
        return ano;
    }

    public String[] getActores() {
        return actores;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getImagenFondo() {
        return imagenFondo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setActores(String[] actores) {
        this.actores = actores;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setImagenFondo(String imagenFondo) {
        this.imagenFondo = imagenFondo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    public String getActoresString(String[] actores) {
        StringBuilder actoresString = new StringBuilder();

        for (int i = 0; i < actores.length; i++) {
            actoresString.append(actores[i]);

            // Añadir coma si no es la última iteración
            if (i < actores.length - 1) {
                actoresString.append(", ");
            }
        }
        return actoresString.toString();
    }

    public int getId() {
        return id;
    }
    public boolean getVista(){
        return vista;
    }
    public void setVista(boolean vista){

        this.vista=vista;
    }

    public void setId(int id) {
        this.id = id;
    }

}
