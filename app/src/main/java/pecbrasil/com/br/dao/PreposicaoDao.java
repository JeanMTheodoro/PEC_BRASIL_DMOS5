package pecbrasil.com.br.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pecbrasil.com.br.exeption.InsercaoDatabaseSqlite;
import pecbrasil.com.br.model.Preposicao;

public class PreposicaoDao {

    private SQLiteDatabase mSqLiteDatabase;
    private SqLiteHelper mHelper;

    public PreposicaoDao(Context context) {
        mHelper = new SqLiteHelper(context);
    }

    public void adicionar(Preposicao preposicao) throws NullPointerException, InsercaoDatabaseSqlite {
        if (preposicao == null) {
            throw new NullPointerException();
        }

        ContentValues valores = new ContentValues();
        valores.put(SqLiteHelper.COLUNM_URI, preposicao.getUri());
        valores.put(SqLiteHelper.COLUMN_SIGLA, preposicao.getSiglaTipo());
        valores.put(SqLiteHelper.COLUMN_EMENTA, preposicao.getEmenta());
        valores.put(SqLiteHelper.COLUMN_ANO, preposicao.getAno());
        mSqLiteDatabase = mHelper.getWritableDatabase();

        if(mSqLiteDatabase.insert(SqLiteHelper.TABLE_NAME_PROPOSICAO, null, valores) == -1){
            throw new InsercaoDatabaseSqlite("Erro ao adicionar Contato");
        }

        mSqLiteDatabase.close();
    }

    public List<Preposicao> recuperaTodos(){

        List<Preposicao> mContatoList;
        Preposicao mPreposicao;
        Cursor mCursor;

        mContatoList = new ArrayList<>();

        String colunas[] = new String[]{
                SqLiteHelper.COLUNM_URI,
                SqLiteHelper.COLUMN_SIGLA,
                SqLiteHelper.COLUMN_EMENTA,
                SqLiteHelper.COLUMN_ANO
        };

        mSqLiteDatabase = mHelper.getReadableDatabase();

        mCursor = mSqLiteDatabase.query(
                SqLiteHelper.TABLE_NAME_PROPOSICAO,
                colunas,
                null,
                null,
                null,
                null,
                SqLiteHelper.COLUMN_ANO
        );

        while (mCursor.moveToNext()){
            mPreposicao = new Preposicao(
                    mCursor.getString(0),
                    mCursor.getString(1),
                    mCursor.getString(2),
                    mCursor.getString(3)
            );



            mContatoList.add(mPreposicao);
        }

        mCursor.close();
        mSqLiteDatabase.close();
        return mContatoList;
    }
}
