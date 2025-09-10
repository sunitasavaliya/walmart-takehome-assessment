package com.example.walmartcodingassessment.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmartcodingassessment.databinding.ActivityMainBinding
import com.example.walmartcodingassessment.domain.model.Country
import com.example.walmartcodingassessment.ui.view.uistate.UIState
import com.example.walmartcodingassessment.ui.view.adapter.CountryAdapter
import com.example.walmartcodingassessment.ui.viewmodel.CountryViewModel
import com.example.walmartcodingassessment.ui.viewmodel.CountryViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CountryViewModel

    private val countryAdapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory = CountryViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(CountryViewModel::class.java)

        setUpRecyclerview()
        observeViewModel()
    }

    private fun setUpRecyclerview() {
        binding.rvCountries.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = countryAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UIState.Loading -> {
                            setUpViewVisibility(
                                rvCountriesVisibility = View.GONE,
                                tvErrorVisibility = View.GONE,
                                progressBarVisibility = View.VISIBLE,
                                noDataVisibility = View.GONE

                            )
                        }

                        is UIState.Success -> {
                            if((it.data).isEmpty()){
                                setUpViewVisibility(
                                    rvCountriesVisibility = View.GONE,
                                    tvErrorVisibility = View.GONE,
                                    progressBarVisibility = View.GONE,
                                    noDataVisibility = View.VISIBLE
                                )
                            }
                            else{
                                setUpViewVisibility(
                                    rvCountriesVisibility = View.VISIBLE,
                                    tvErrorVisibility = View.GONE,
                                    progressBarVisibility = View.GONE,
                                    noDataVisibility = View.GONE
                                )
                                countryAdapter.submitList(it.data)
                            }
                        }

                        is UIState.Error -> {
                            setUpViewVisibility(
                                rvCountriesVisibility = View.GONE,
                                tvErrorVisibility = View.VISIBLE,
                                progressBarVisibility = View.GONE,
                                noDataVisibility = View.GONE
                            )
                            binding.tvError.text = it.message
                        }
                    }
                }
            }
        }
    }

    private fun setUpViewVisibility(
        rvCountriesVisibility: Int,
        tvErrorVisibility: Int,
        progressBarVisibility: Int,
        noDataVisibility: Int
    ) {
        binding.apply {
            rvCountries.visibility = rvCountriesVisibility
            tvError.visibility = tvErrorVisibility
            progressBar.visibility = progressBarVisibility
            tvNoData.visibility = noDataVisibility
        }
    }
}