package br.com.atrixdevelopers.rafael.example.java.sqlite.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Atrix Developers
 *
 * @author Rafael de Azeredo
 */
class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sqlite_example_java.db";

    SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteQuery.CREATE_TABLE_CONTATO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion == 1) {
            //db.execSql(...);
        }

        if(oldVersion == 2) {
            //db.execSql(...);
        }

        if(oldVersion == 3) {
            //db.execSql(...);
        }
    }
}
