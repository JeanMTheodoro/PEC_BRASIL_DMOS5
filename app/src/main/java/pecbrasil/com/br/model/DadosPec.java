package pecbrasil.com.br.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DadosPec {

    @SerializedName("dados")
    public List<Preposicao> proposicao;
}
