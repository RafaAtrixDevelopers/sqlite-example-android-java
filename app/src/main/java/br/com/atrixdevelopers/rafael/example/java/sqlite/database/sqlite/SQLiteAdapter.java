package br.com.atrixdevelopers.rafael.example.java.sqlite.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.atrixdevelopers.rafael.example.java.sqlite.model.Contato;

/**
 * Atrix Developers
 *
 * @author Rafael de Azeredo
 */
public class SQLiteAdapter {

    private SQLiteHelper sqLiteHelper;

    public SQLiteAdapter(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public void insertContato(Contato contato) {

        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", contato.getNome());
        contentValues.put("idade", contato.getIdade());

        db.insert("contato", null, contentValues);
        db.close();
    }

    public ArrayList<Contato> getAllContato() {

        ArrayList<Contato> listaContato = new ArrayList<>();

        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        Cursor c = db.rawQuery(SQLiteQuery.SELECT_ALL_CONTATO, null);
        while(c.moveToNext()) {
            Contato contato = new Contato();
            contato.setId(c.getInt(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setIdade(c.getInt(c.getColumnIndex("idade")));

            listaContato.add(contato);
        }

        c.close();
        db.close();

        return listaContato;
    }

    public void updateContato(Contato contato) {

        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", contato.getId());
        contentValues.put("nome", contato.getNome());
        contentValues.put("idade", contato.getIdade());

        db.update("contato", contentValues, "id=" + contato.getId(), null);
        db.close();
    }

    public void deleteContato(int id) {

        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        db.delete("contato", "id=" + id, null);
        db.close();
    }
}
