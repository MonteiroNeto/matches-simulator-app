package com.example.simulator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.simulator.databinding.ActivityDetailBinding
import com.example.simulator.domain.Matches

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding

    object Extras{
        const  val EXTRAS_INTENT_PUT = "intent_extra"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Matches>(Extras.EXTRAS_INTENT_PUT)?.let {

            Glide.with(this).load(it.place.imagem).into(binding.ivPlaceEstadio)
            supportActionBar?.setTitle(it.place.name)


            binding.tvDescriptionScreenDetail.setText(it.description)

            //CASA
            Glide.with(this).load(it.temHome.image).into(binding.ivCasaScreenDetail)
            binding.rbStarCasa.rating = it.temHome.stars.toFloat()
            binding.tvNameTeamCasaScreenDetail.text = it.temHome.name
            if (it.temHome.scrore != null) {
                binding.tvScoreTeamCasaScreenDetail.text = it.temHome.scrore.toString()
            }


            //FORA
            Glide.with(this).load(it.allTeam.image).into(binding.ivForaScreenDetail)
            binding.rbStarFora.rating = it.allTeam.stars.toFloat()
            binding.tvNameTeamForaScreenDetail.text = it.allTeam.name
            if (it.allTeam.scrore != null) {
                binding.tvScoreTeamForaScreenDetail.text = it.allTeam.scrore.toString()
            }
        }
    }
}