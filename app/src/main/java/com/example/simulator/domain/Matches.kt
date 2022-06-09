package com.example.simulator.domain

import com.google.gson.annotations.SerializedName

data class Matches (
    @SerializedName("descricao")
    val description:String,
    @SerializedName("estadio")
    val place:Place,
    @SerializedName("casa")
    val temHome:Team,
    @SerializedName("fora")
    val allTeam: Team
        )