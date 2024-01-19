package al.if05.bbddtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    private Context mContext;
      public SQLHelper(@Nullable Context context) {
        super(context, "AlumnosBBDD.db", null, 1);
        mContext=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(mContext,"Base de datos creada",Toast.LENGTH_SHORT).show();
        db.execSQL( "CREATE TABLE Alumnos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "edad INTEGER)");

        ContentValues values = new ContentValues();
        values.put("nombre","Alexis");
        values.put("edad","29");
        db.insert("Alumnos",null,values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
