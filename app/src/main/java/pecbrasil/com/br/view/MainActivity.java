package pecbrasil.com.br.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import pecbrasil.com.br.R;
import pecbrasil.com.br.adapter.MyAdapter;
import pecbrasil.com.br.dao.PreposicaoDao;
import pecbrasil.com.br.exeption.InsercaoDatabaseSqlite;
import pecbrasil.com.br.model.DadosPec;
import pecbrasil.com.br.model.Preposicao;
import pecbrasil.com.br.network.ClientRetrofit;
import pecbrasil.com.br.network.PecService;
import pecbrasil.com.br.sharedpreferencies.Preferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Preposicao> dadosPreposicoes = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private PreposicaoDao mPreposicaoDao;
    private Preferencias mPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferencias = new Preferencias(getApplication());

        if(mPreferencias.recuperarLista() > 0){
            Intent i = getIntent();
            dadosPreposicoes =  (List<Preposicao>)
                    i.getSerializableExtra("dados");
        }else {
            buscarDados();
        }

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        myAdapter = new MyAdapter(this, dadosPreposicoes);
        recyclerView.setAdapter(myAdapter);

    }

    private void buscarDados() {

        //dadosPreposicoes.clear();

        PecService service = ClientRetrofit.getRetrofit().create(PecService.class);
        Call<DadosPec> call = service.getdados();
        call.enqueue(new Callback<DadosPec>() {
            @Override
            public void onResponse(Call<DadosPec> call, Response<DadosPec> response) {

                if (response.isSuccessful()) {

                    dadosPreposicoes.addAll(response.body().proposicao);
                    metodo();
                }

            }

            @Override
            public void onFailure(Call<DadosPec> call, Throwable t) {

            }
        });


    }

    public void metodo() {

        popularBanco();
        mPreferencias.salvarLista(dadosPreposicoes.size());
        recyclerView.getAdapter().notifyDataSetChanged();


    }

    public void popularBanco() {

        mPreposicaoDao = new PreposicaoDao(getApplicationContext());
        for (Preposicao p : dadosPreposicoes) {

            try {

                mPreposicaoDao.adicionar(p);

            } catch (NullPointerException e) {

                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();

            }catch (InsercaoDatabaseSqlite e){

                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();

            }
        }
    }


}