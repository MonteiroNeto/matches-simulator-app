package com.example.simulator.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simulator.R;
import com.example.simulator.domain.Matches;
import com.example.simulator.domain.Place;
import com.example.simulator.ui.DetailActivity;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MyViewHolder> {

    List<Matches> matches;

    public List<Matches> getMatch() {
        return matches;
    }

    public MatchesAdapter(List<Matches> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_itens, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Matches item = matches.get(position);
        holder.teamHome.setText(item.getTemHome().getName());
        holder.allTeam.setText(item.getAllTeam().getName());

        //Gliden para usar a imagem da web
        Context context = holder.itemView.getContext();
        Glide.with(context).load(item.getTemHome().getImage()).circleCrop().into(holder.ivHome);//imagem Redonda
        Glide.with(context).load(item.getAllTeam().getImage()).into(holder.ivAllTeam);//imagem original recebida da Web

        //setar o placar depois que o botao for precionado
        if (item.getTemHome().getScrore() != null && item.getAllTeam().getScrore() != null ){
            holder.placarTeamHome.setText(String.valueOf(item.getTemHome().getScrore()));
            holder.placarAllTeam.setText(String.valueOf(item.getAllTeam().getScrore()));

        }

        //Abrir Tela de detalhe e passar o item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.Extras.EXTRAS_INTENT_PUT,item);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView teamHome;
        TextView allTeam;
        TextView placarTeamHome;
        TextView placarAllTeam;
        ImageView ivHome;
        ImageView ivAllTeam;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            teamHome = itemView.findViewById(R.id.tvNameTeamCasa);
            allTeam = itemView.findViewById(R.id.tvNameTeamFora);
            ivHome = itemView.findViewById(R.id.ivCasa);
            ivAllTeam = itemView.findViewById(R.id.ivFora);
            placarTeamHome = itemView.findViewById(R.id.tvScoreTeamCasa);
            placarAllTeam = itemView.findViewById(R.id.tvScoreTeamFora);

        }
    }
}
