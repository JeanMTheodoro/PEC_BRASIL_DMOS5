package pecbrasil.com.br.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "proposicoes.db";


    public static final String TABLE_NAME_PROPOSICAO = "contatos";
    public static final String COLUNM_URI = "uri";
    public static final String COLUMN_SIGLA = "sigla";
    public static final String COLUMN_EMENTA = "ementa";
    public static final String COLUMN_ANO = "ano";

    public SqLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;

        sql = "CREATE TABLE " + TABLE_NAME_PROPOSICAO + " (";
        sql += COLUNM_URI + " TEXT NOT NULL, ";
        sql += COLUMN_SIGLA + " TEXT NOT NULL, ";
        sql += COLUMN_EMENTA+ " TEXT NOT NULL, ";
        sql += COLUMN_ANO + " TEXT NOT NULL); ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
