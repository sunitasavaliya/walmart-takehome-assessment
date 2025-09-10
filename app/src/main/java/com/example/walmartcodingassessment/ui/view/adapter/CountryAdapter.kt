package com.example.walmartcodingassessment.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartcodingassessment.databinding.LayoutCountryBinding
import com.example.walmartcodingassessment.domain.model.Country

class CountryAdapter :
    ListAdapter<Country, CountryAdapter.CountryViewHolder>(CountryDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val binding =
            LayoutCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {
        val country = getItem(position)
        holder.bind(country)
    }

    class CountryViewHolder(private val binding: LayoutCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.tvCountryName.text = country.region.takeIf { it.isNotEmpty() }
                ?.let { "${country.name}, ${country.region}" } ?: run { country.name }
            binding.tvCountryCode.text = country.code
            binding.tvCapitol.text = country.capital
        }
    }
}

class CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(
        oldItem: Country,
        newItem: Country
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.region == newItem.region && oldItem.code == newItem.code && oldItem.capital == newItem.capital
    }

    override fun areContentsTheSame(
        oldItem: Country,
        newItem: Country
    ): Boolean {
        return oldItem == newItem
    }
}
