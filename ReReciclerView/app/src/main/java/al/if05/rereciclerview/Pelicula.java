package al.if05.rereciclerview;

public class Pelicula {
    private String titulo;
    private String autor;
    private int puntuacion;

    public Pelicula(String newTitulo,String newAutor,int newPuntuacion){
        this.titulo=newTitulo;
        this.autor=newAutor;
        this.puntuacion=newPuntuacion;

    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
