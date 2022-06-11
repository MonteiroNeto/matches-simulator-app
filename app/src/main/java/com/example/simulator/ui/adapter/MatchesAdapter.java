package com.example.simulator.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simulator.R;
import com.example.simulator.domain.Matches;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MyViewHolder> {
    List<Matches> matches;

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
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView teamHome;
        TextView allTeam;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            teamHome = itemView.findViewById(R.id.tvNameTeamCasa);
            allTeam = itemView.findViewById(R.id.tvNameTeamFora);

        }
    }
}
