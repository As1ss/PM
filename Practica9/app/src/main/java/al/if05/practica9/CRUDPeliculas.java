package al.if05.practica9;

import java.util.List;

public interface CRUDPeliculas {

    public Pelicula read(int id);
    public List<Pelicula> readAll();
    public void create(Pelicula pelicula);
    public void update(Pelicula pelicula);
    public void delete(Pelicula pelicula);
}
