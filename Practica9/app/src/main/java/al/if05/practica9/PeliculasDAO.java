package al.if05.practica9;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PeliculasDAO implements CRUDPeliculas  {

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
    public List<Pelicula> readAll() {

        List<Pelicula> peliculas = new ArrayList<Pelicula>();

        SQLiteDatabase sqlDB = sqlHelper.getReadableDatabase();


        Cursor c = sqlDB.rawQuery("SELECT * FROM PELICULA", null);

        while (c.moveToNext()) {
            Pelicula pelicula = new Pelicula();
            pelicula.setId(c.getInt(0));
            pelicula.setTitulo(c.getString(1));
            pelicula.setDirector(c.getString(2));
            pelicula.setAno(c.getString(3));
            pelicula.setActores(new String[]{c.getString(4)});
            pelicula.setSinopsis(c.getString(5));
            pelicula.setImagenFondo(c.getString(6));
            pelicula.setPuntuacion(c.getInt(7));
            pelicula.setVista((c.getInt(8) ==0) ? false : true); //Exrpresion ternaria para indicar el campo true o false
            peliculas.add(pelicula);
        }

        return peliculas;
    }


    @Override
    public void create(Pelicula pelicula) {

    }

    @Override
    public void update(Pelicula pelicula) {
        int vista = (pelicula.getVista()) ? 1 : 0;

        SQLiteDatabase sqlDB = sqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("puntuacion",pelicula.getPuntuacion());
        values.put("vista",vista);

        int resultado = sqlDB.update("pelicula",values,"id ="+pelicula.getId(),null);

        Log.d("RESULTADO","RESULTADO: "+resultado);

    }

    @Override
    public void delete(Pelicula pelicula) {

    }
}
