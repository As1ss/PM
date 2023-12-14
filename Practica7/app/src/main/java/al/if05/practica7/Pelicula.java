package al.if05.practica7;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;

public class Pelicula implements Serializable {
    private String titulo;
    private String director;
    private String ano;
    private String[] actores;
    private String sinopsis;
    private Drawable imagenFondo;

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

    public Drawable getImagenFondo() {
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

    public void setImagenFondo(Drawable imagenFondo) {
        this.imagenFondo = imagenFondo;
    }
}
