package com.example.simulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Matches (
    @SerializedName("descricao")
    val description:String,
    @SerializedName("estadio")
    val place:Place,
    @SerializedName("casa")
    val temHome:Team,
    @SerializedName("fora")
    val allTeam: Team
        ):Parcelable