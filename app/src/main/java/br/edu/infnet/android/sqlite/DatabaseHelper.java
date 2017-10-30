package br.edu.infnet.android.sqlite;

/**
 * Created by pm on 19/10/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*Classe de facilidade ao banco de dados */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Nome da tabela
    public static final String TABLE_NAME = "ALUNO";

    // Colunas a criar
    public static final String _ID = "_id";
    public static final String NOME = "nome";
    public static final String SOBRENOME = "sobrenome";

    // Nome do banco
    static final String DB_NAME = "infnet.db";

    // Versao do banco
    static final int DB_VERSION = 1;

    // Query de criacao de tabela
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " TEXT NOT NULL, " + SOBRENOME + " TEXT);";

    /* contrutor*/
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /*criacao de table */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    /* caso tenhamos upgrade, deleta a tabela e recria*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        switch (newVersion){
            case 1:
                onCreate(db);
            case 3:
                db.execSQL("ALTER TABLE "+ TABLE_NAME + "  ADD COLUMN text3 TEXT");
                break;
            case 4:
                db.execSQL("ALTER TABLE "+ TABLE_NAME + "  ADD COLUMN text4 TEXT");
                break;
            case 5:
                db.execSQL("ALTER TABLE "+ TABLE_NAME + "  ADD COLUMN text5 TEXT");
                break;
        }

    }
}
