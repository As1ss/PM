package al.if05.practica9;

public class PeliculasDAO implements CRUDPeliculas {

    private SQLHelper sqlHelper;

    public PeliculasDAO() {


    }

    public PeliculasDAO(SQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }


    @Override
    public Pelicula read(int id) {
        return null;
    }

    @Override
    public void create(Pelicula pelicula) {

    }

    @Override
    public void update(Pelicula pelicula) {

    }

    @Override
    public void delete(Pelicula pelicula) {

    }
}
