package al.if05.practica9;

public interface CRUDPeliculas {

    public Pelicula read(int id);
    public void create(Pelicula pelicula);
    public void update(Pelicula pelicula);
    public void delete(Pelicula pelicula);
}
