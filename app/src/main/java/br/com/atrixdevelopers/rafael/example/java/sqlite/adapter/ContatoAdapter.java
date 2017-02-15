package br.com.atrixdevelopers.rafael.example.java.sqlite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.atrixdevelopers.rafael.example.java.sqlite.R;
import br.com.atrixdevelopers.rafael.example.java.sqlite.database.sqlite.SQLiteAdapter;
import br.com.atrixdevelopers.rafael.example.java.sqlite.model.Contato;

/**
 * Atrix Developers
 *
 * @author Rafael de Azeredo
 */
public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoHolder> {

    private Context context;
    private ArrayList<Contato> listaContatos;
    private SQLiteAdapter sqLiteAdapter;
    private ContatoActionListener contatoActionListener;

    public interface ContatoActionListener {
        void onEditar(Contato contato, int position);
        void onDeletar(Contato contato, int position);
    }

    public ContatoAdapter(Context context, ArrayList<Contato> listaContatos) {
        this.context = context;
        this.listaContatos = listaContatos;
        this.sqLiteAdapter = new SQLiteAdapter(context);
    }

    public void setContatoActionListener(ContatoActionListener contatoActionListener) {
        this.contatoActionListener = contatoActionListener;
    }

    @Override
    public ContatoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contato, parent, false);
        return new ContatoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContatoHolder holder, int position) {
        Contato contato = listaContatos.get(position);

        holder.txtNome.setText(contato.getNome());
        holder.txtIdade.setText(String.format(context.getString(R.string.label_idade), contato.getIdade()));
    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }

    public void changeList(ArrayList<Contato> listaContatos) {
        this.listaContatos = listaContatos;
        notifyDataSetChanged();
    }

    public void removeContato(int position) {
        listaContatos.remove(position);
        notifyItemRemoved(position);
    }

    class ContatoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtNome;
        TextView txtIdade;
        ImageButton btnEditar;
        ImageButton btnDeletar;

        ContatoHolder(View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.txt_nome);
            txtIdade = (TextView) itemView.findViewById(R.id.txt_idade);
            btnEditar = (ImageButton) itemView.findViewById(R.id.btn_editar);
            btnDeletar = (ImageButton) itemView.findViewById(R.id.btn_deletar);

            btnEditar.setOnClickListener(this);
            btnDeletar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(R.id.btn_editar == v.getId()) {
                if (contatoActionListener != null) {
                    contatoActionListener.onEditar(listaContatos.get(getAdapterPosition()), getAdapterPosition());
                }
            }

            if(R.id.btn_deletar == v.getId()) {
                if (contatoActionListener != null) {
                    contatoActionListener.onDeletar(listaContatos.get(getAdapterPosition()), getAdapterPosition());
                }
            }
        }
    }
}
