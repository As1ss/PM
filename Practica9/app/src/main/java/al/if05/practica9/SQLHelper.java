package al.if05.practica9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="peliculas.db";
    public static final String TABLE_NAME="pelicula";
    public static final String ID ="id";
    public static final String TITLE="titulo";
    public static final String DIRECTOR="director";
    public static final String YEAR="ano";
    public static final String ACTORS="actores";
    public static final String SYNOPSIS="sinopsis";
    public static final String IMAGE="imagen";
    public static final String SCORE="puntuacion";
    public static final String VIEW="vista";


    private static Context mContext;

    public SQLHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        mContext=context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (\n" +
                "\t" + ID + "\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t" + TITLE + "\tTEXT,\n" +
                "\t" + DIRECTOR + "\tTEXT,\n" +
                "\t" + YEAR + "\tINTEGER,\n" +
                "\t" + ACTORS + "\tTEXT,\n" +
                "\t" + SYNOPSIS + "\tTEXT,\n" +
                "\t" + IMAGE + "\tTEXT,\n" +
                "\t" + SCORE + "\tINTEGER,\n" +
                "\t" + VIEW + "\tINTEGER\n" +
                ")");

        String insertP0 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP0) + "', " +
                "'" + mContext.getString(R.string.directorP0) + "', " +
                mContext.getString(R.string.anoP0) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP0)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP0) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP0);

        String insertP1 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP1) + "', " +
                "'" + mContext.getString(R.string.directorP1) + "', " +
                mContext.getString(R.string.anoP1) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP1)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP1) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP1);
        String insertP2 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP2) + "', " +
                "'" + mContext.getString(R.string.directorP2) + "', " +
                mContext.getString(R.string.anoP2) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP2)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP2) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP2);
        String insertP3 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP3) + "', " +
                "'" + mContext.getString(R.string.directorP3) + "', " +
                mContext.getString(R.string.anoP3) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP3)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP3) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP3);

        String insertP4 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP4) + "', " +
                "'" + mContext.getString(R.string.directorP4) + "', " +
                mContext.getString(R.string.anoP4) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP4)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP4) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP4);
        String insertP5 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP5) + "', " +
                "'" + mContext.getString(R.string.directorP5) + "', " +
                mContext.getString(R.string.anoP5) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP5)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP5) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP5);

        String insertP6 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP6) + "', " +
                "'" + mContext.getString(R.string.directorP6) + "', " +
                mContext.getString(R.string.anoP6) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP6)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP6) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP6);

        String insertP7 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP7) + "', " +
                "'" + mContext.getString(R.string.directorP7) + "', " +
                mContext.getString(R.string.anoP7) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP7)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP7) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP7);
        String insertP8 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP8) + "', " +
                "'" + mContext.getString(R.string.directorP8) + "', " +
                mContext.getString(R.string.anoP8) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP8)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP8) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP8);

        String insertP9 = "INSERT INTO " + SQLHelper.TABLE_NAME + " (" +
                SQLHelper.TITLE + ", " +
                SQLHelper.DIRECTOR + ", " +
                SQLHelper.YEAR + ", " +
                SQLHelper.ACTORS + ", " +
                SQLHelper.SYNOPSIS + ", " +
                SQLHelper.IMAGE + ", " +
                SQLHelper.SCORE + ", " +
                SQLHelper.VIEW +
                ") VALUES (" +
                "'" + mContext.getString(R.string.tituloP9) + "', " +
                "'" + mContext.getString(R.string.directorP9) + "', " +
                mContext.getString(R.string.anoP9) + ", " +
                "'" + TextUtils.join(",", mContext.getResources().getStringArray(R.array.actoresP9)) + "', " +
                "'" + mContext.getString(R.string.sinopsisP9) + "', " +
                "'" + "abierto_hasta_el_amanecer" + "', " +
                0 + ", " +  // Añade una coma aquí para separar los dos valores enteros
                0 +  // Los valores enteros no necesitan coma al final
                ")";

// Ejecutar la consulta de inserción
        db.execSQL(insertP9);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Context getContext() {
        return mContext;
    }
}
