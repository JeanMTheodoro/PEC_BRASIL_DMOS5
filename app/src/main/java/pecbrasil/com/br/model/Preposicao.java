package pecbrasil.com.br.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Preposicao implements Serializable {

   /* @SerializedName("id")
    private int id;*/

    @SerializedName("uri")
    private String uri;

    @SerializedName("siglaTipo")
    private String siglaTipo;

    @SerializedName("ano")
    private String ano;

    @SerializedName("ementa")
    private String ementa;



    public Preposicao(String uri, String sigla, String ano, String ementa) {
        //this.id = id;
        this.uri = uri;
        this.siglaTipo = sigla;
        this.ano = ano;
        this.ementa = ementa;
    }

    public Preposicao(){ }

    /*public int getId() {
        return id;
    }*/

    public String getUri() {
        return uri;
    }

    public String getSiglaTipo() {
        return siglaTipo;
    }

    public String getAno() {
        return ano;
    }

    public String getEmenta() {
        return ementa;
    }
}
