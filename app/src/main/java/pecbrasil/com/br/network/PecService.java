package pecbrasil.com.br.network;

import java.util.List;

import pecbrasil.com.br.model.DadosPec;
import pecbrasil.com.br.model.Preposicao;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PecService {

    @GET("proposicoes?ordem=ASC&ordenarPor=id")
    Call<DadosPec> getdados();
}
