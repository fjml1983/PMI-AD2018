package martinez.fernando.interfazusuarioviewsbasicosregusr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BaseDatos.db";

    public BaseDatosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_crear_tablas =
                "CREATE TABLE " + BaseDatosContract.TablaUsuario.TABLE_NAME +
                " (" + BaseDatosContract.TablaUsuario._ID + " INTEGER PRIMARY KEY," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_USERNAME + " TEXT," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_PASSWORD + " TEXT," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_NOMBRE_PILA + " TEXT," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_GENERO + " TEXT," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_TECNOLOGIAS + " TEXT," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_INSTITUCION + " TEXT," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_NOTIFICACIONES + " INTEGER," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_PUBLICIDAD + " INTEGER," +
                        BaseDatosContract.TablaUsuario.COLUMN_NAME_FECHA_NACIMIENTO + " INTEGER" +
                " )";
        db.execSQL(query_crear_tablas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
