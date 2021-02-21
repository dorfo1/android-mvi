package com.example.hilt_sample.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hilt_sample.R
import com.example.hilt_sample.core.base.*
import com.example.hilt_sample.core.extensions.addOnTextChange
import com.example.hilt_sample.ui.main.adapter.RocketAdapter
import com.example.hilt_sample.model.Rocket
import com.example.hilt_sample.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity<MainIntent, MainAction, MainState, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModels()

    private val adapter: RocketAdapter by lazy {
        RocketAdapter(::onRocketClicked)
    }

    private fun onRocketClicked(rocket: Rocket) {
        dispatchIntent(MainIntent.DetailRockets(rocket))
    }

    override fun getViewModel(): MainViewModel = mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvRockets.adapter = adapter
        rvRockets.layoutManager = LinearLayoutManager(this)


        dispatchIntent(MainIntent.FetchRockets)

        search.apply {
            addOnTextChange {
                if (it.toString().isEmpty()) dispatchIntent(MainIntent.ClearFilter)
                else dispatchIntent(MainIntent.FilterRockets(it.toString()))
            }

        }


    }

    override fun handleState(it: MainState) {
        showLoading(it is MainState.LoadingState)
        showError(it is MainState.Error)

        when (it) {
            is MainState.ResultRockets -> { adapter.items = it.rockets }
            is MainState.RocketDetail -> navigateToDetail(it.rocket)
            else -> { }
        }
    }

    private fun navigateToDetail(rocket: Rocket) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ROCKET_KEY,rocket)
        startActivity(intent)
    }

    private fun showLoading(it: Boolean) {
        progress.visibility = if (it) View.VISIBLE else View.GONE
    }

    private fun showError(it: Boolean) {
        if (it) Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
    }
}

