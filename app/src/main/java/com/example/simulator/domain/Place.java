package com.example.simulator.domain;

import com.google.gson.annotations.SerializedName;

public class Place {
    @SerializedName("nome")
    private String name;
    @SerializedName("imagem")
    private String imagem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
