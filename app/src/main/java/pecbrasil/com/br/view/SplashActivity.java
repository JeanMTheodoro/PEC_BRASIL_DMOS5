package pecbrasil.com.br.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pecbrasil.com.br.R;
import pecbrasil.com.br.dao.PreposicaoDao;
import pecbrasil.com.br.model.Preposicao;
import pecbrasil.com.br.sharedpreferencies.Preferencias;

public class SplashActivity extends AppCompatActivity {

    private Preferencias mPreferencias;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mPreferencias = new Preferencias(getApplication());

        if (mPreferencias.recuperarLista() > 0) {

            progressBar.setVisibility(true ? View.VISIBLE : View.GONE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    PreposicaoDao mPreposicaoDao = new PreposicaoDao(getApplicationContext());
                    List<Preposicao> dadosPreposicoes = new ArrayList();
                    mPreposicaoDao = new PreposicaoDao(getApplicationContext());
                    dadosPreposicoes.addAll(mPreposicaoDao.recuperaTodos());
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);
                    it.putExtra("dados", (Serializable) dadosPreposicoes);
                    startActivity(it);
                    finish();

                }
            }, 5000);

            progressBar.setVisibility(false ? View.VISIBLE : View.GONE);
        } else {

            if (verificarConexao()) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {

                Toast.makeText(this, "Dispositivo conecta", Toast.LENGTH_SHORT).show();
            }


        }

    }

    private boolean verificarConexao() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}