package com.example.simulator.data;

import com.example.simulator.domain.Matches;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesApi {
    @GET("matches.json")
    Call<List<Matches>> getMatches();
}
