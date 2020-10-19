package pecbrasil.com.br.sharedpreferencies;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String NOME_ARQUIVO = "tamanho.preferencias";
    private final String CHAVE_NOME = "lista";

    public Preferencias(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0);
    }

    public void salvarLista(int tamanhoLista){
        editor = preferences.edit();
        editor.putInt(CHAVE_NOME, tamanhoLista);
        editor.apply();
    }

    public  int recuperarLista(){
        return preferences.getInt(CHAVE_NOME, 0);
    }
}
