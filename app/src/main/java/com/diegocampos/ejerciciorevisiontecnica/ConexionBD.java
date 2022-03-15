package com.diegocampos.ejerciciorevisiontecnica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionBD extends SQLiteOpenHelper {


    public ConexionBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table registros(codigoIngreso String primary key, fechaIngreso text, horaIngreso text, " +
                "patente text, documentacion text, direccion text, frenos text, llantas text," +
                "suspension text, alineacion text, kitSeguridad text, cinturones text," +
                "luces text, puertas text, vidrios text, tuboEscape text, gases text," +
                "observaciones text, imagenRev1 text, imagenRev2 text, imagenRev3 text, imagenRev4 text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
