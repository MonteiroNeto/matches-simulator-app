package com.example.simulator.ui;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MatchesApi matchesApi;

    MatchesAdapter adapter;





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

        matchesApi.getMatches().enqueue(new Callback<List<Matches>>() {
            @Override
            public void onResponse(Call<List<Matches>> call, Response<List<Matches>> response) {
                if (response.isSuccessful()){
                    List<Matches> matches = response.body();
                    Log.i("INFO SIMULATOR","Deu certo!*******************"+matches.size());
                    adapter = new MatchesAdapter(matches);
                    binding.rvSimulator.setAdapter(adapter);



                }else {
                    showErroMensage();

                    Log.i("INFO ERRO","ERRO***********************");
                }
            }

            @Override
            public void onFailure(Call<List<Matches>> call, Throwable t) {
             //   showErroMensage();
            }
        });
    }



    private void setupMatchesRefresh() {
        //TODO: atualizar as partidas com o metodo Swipper.
    }

    private void setupMatchesFloatActionButton() {
        //TODO: criar evento de click e simulação de partidas.
    }


    private void showErroMensage() {
        Snackbar.make(binding.fabSimulator,"erro",Snackbar.LENGTH_LONG).show();
    }
}
