package com.example.simulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    @SerializedName("nome")
    val name:String,
    @SerializedName("forca")
    val stars:Int,
    @SerializedName("imagem")
    val image:String,

    //implementado depois para receber o valor do placa depois que precionar o botao
    var scrore:Int?
        ):Parcelable