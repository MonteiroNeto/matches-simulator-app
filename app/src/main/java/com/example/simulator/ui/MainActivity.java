package com.example.simulator.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simulator.data.MatchesApi;
import com.example.simulator.databinding.ActivityMainBinding;
import com.example.simulator.domain.Matches;
import com.example.simulator.ui.adapter.MatchesAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MatchesApi matchesApi;

    MatchesAdapter adapter;
    List<Matches> matches = new ArrayList<>(Collections.EMPTY_LIST);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setupHttpClient();
        setupMatchesLists();
        setupMatchesRefresh();
        setupMatchesFloatActionButton();

    }

    //Setup retrofit apartir de uma url // MatchesApi é uma interface criada para tratar os dados
    private void setupHttpClient() {
        String API_BASE_URL = "https://monteironeto.github.io/matches-simulator-api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        matchesApi = retrofit.create(MatchesApi.class);
    }


    private void setupMatchesLists() {
        LinearLayoutManager layout = new LinearLayoutManager(this);
        binding.rvSimulator.setLayoutManager(layout);
        adapter = new MatchesAdapter(matches);
        binding.rvSimulator.setAdapter(adapter);

        findMatchesFromApi();
    }

    private void setupMatchesRefresh() {
        //para quando atualizar
        binding.swiperSimulator.setOnRefreshListener(this::findMatchesFromApi);
    }

    private void findMatchesFromApi() {
        //fazer aparecer o item de carregamento
        binding.swiperSimulator.setRefreshing(true);

        matchesApi.getMatches().enqueue(new Callback<List<Matches>>() {
            @Override
            public void onResponse(Call<List<Matches>> call, Response<List<Matches>> response) {
                if (response.isSuccessful()) {
                    matches = response.body();
                    Log.i("INFO SIMULATOR", "Deu certo!*******************" + matches.size());
                    adapter = new MatchesAdapter(matches);
                    binding.rvSimulator.setAdapter(adapter);

                } else {
                    showErroMensage();
                    Log.i("INFO ERRO", "ERRO***********************");
                }

                binding.swiperSimulator.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Matches>> call, Throwable t) {
                //   showErroMensage();
                showErroMensage();
                binding.swiperSimulator.setRefreshing(false);
            }
        });
    }


    private void setupMatchesFloatActionButton() {
        binding.fabSimulator.setOnClickListener(view -> {

            //meio Segundo | conceito add do JetPack
            view.animate().rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Random random = new Random();

                    for (int i = 0; i < adapter.getItemCount(); i++) {
                        Matches match = adapter.getMatch().get(i);

                        //utilizar a class Random para gerar de forma automatica o plcacar
                        match.getTemHome().setScrore(random.nextInt(match.getTemHome().getStars() + 1) );
                        match.getAllTeam().setScrore(random.nextInt(match.getAllTeam().getStars() + 1) );

                        //informar ao adapter que o item da lista mudou
                        adapter.notifyItemChanged(i);
                    }
                }

            });

        });
    }


    private void showErroMensage() {
        Snackbar.make(binding.fabSimulator, "erro", Snackbar.LENGTH_LONG).show();
    }
}
