package pecbrasil.com.br.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pecbrasil.com.br.R;
import pecbrasil.com.br.model.Preposicao;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Preposicao> preposicaos;

    public MyAdapter(Context context, List<Preposicao> preposicaos) {
        this.context = context;
        this.preposicaos = preposicaos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pec, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

       holder.sigla.setText(preposicaos.get(position).getSiglaTipo());
       holder.ementa.setText(preposicaos.get(position).getEmenta());
       holder.ano.setText(preposicaos.get(position).getAno());

       holder.verMais.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse(preposicaos.get(position).getUri()));
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return preposicaos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView sigla;
        private TextView ementa;
        private TextView ano;
        private Button verMais;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sigla = itemView.findViewById(R.id.textView_sigla);
            ementa = itemView.findViewById(R.id.textView_description);
            ano = itemView.findViewById(R.id.tetxView_ano);
            verMais = itemView.findViewById(R.id.button_vermais);
        }
    }
}
