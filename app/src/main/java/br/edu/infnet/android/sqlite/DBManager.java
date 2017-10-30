package br.edu.infnet.android.sqlite;

/**
 * Created by pm on 19/10/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/* classe de gerenciamento de banco */
public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DBManager(Context c) {
        context = c;
    }

    /* metodo de abertura do db
    caso nao exista, cria
    se tiver versao nova, atualiza
     */
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    /*insercao de dados via contentvalues*/
    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NOME, name);
        contentValue.put(DatabaseHelper.SOBRENOME, desc);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    /* consulta da tabela */
    public Cursor fetch() {
        String[] columns = new String[] {
                DatabaseHelper._ID, DatabaseHelper.NOME, DatabaseHelper.SOBRENOME
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /* atualizando conteudo de um registro da tabela */

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NOME, name);
        contentValues.put(DatabaseHelper.SOBRENOME, desc);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    /*remove registro da tabela */
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
