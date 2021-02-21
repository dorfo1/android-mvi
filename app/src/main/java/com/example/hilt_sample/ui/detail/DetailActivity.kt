package com.example.hilt_sample.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.hilt_sample.R
import com.example.hilt_sample.core.base.BaseActivity
import com.example.hilt_sample.model.Rocket
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : BaseActivity<DetailIntent, DetailAction, DetailState, DetailViewModel>() {

    companion object{
        const val ROCKET_KEY = "ROCKET"
    }

    private val detailViewModel : DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val rocket = intent.getParcelableExtra<Rocket>(ROCKET_KEY)
        dispatchIntent(DetailIntent.DisplayRocket(rocket))
    }

    override fun getViewModel(): DetailViewModel = detailViewModel

    override fun handleState(it: DetailState) {
       when(it){
           is DetailState.Success -> {
               Glide.with(image).load(it.rocket.photos[1]).into(image)
               tvTitle.text = getString(R.string.rocket_title,it.rocket.name,it.rocket.kg)
               description.text = it.rocket.description
           }
           DetailState.Error -> {
               lifecycleScope.launch {
                   Toast.makeText(applicationContext,getString(R.string.detail_error_message),Toast.LENGTH_LONG).show()
                   delay(5000L)
                   this@DetailActivity.finish()
               }
           }
       }
    }
}

