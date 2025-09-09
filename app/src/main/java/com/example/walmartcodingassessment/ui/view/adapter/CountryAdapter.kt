package com.example.walmartcodingassessment.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartcodingassessment.data.model.CountryItem
import com.example.walmartcodingassessment.databinding.LayoutCountryBinding

class CountryAdapter :
    ListAdapter<CountryItem, CountryAdapter.CountryViewHolder>(CountryDiffUtil()) {

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
        fun bind(country: CountryItem) {
            binding.tvCountryName.text = "${country.name}, ${country.region}"
            binding.tvCountryCode.text = country.code
            binding.tvCapitol.text = country.capital
        }
    }
}

class CountryDiffUtil : DiffUtil.ItemCallback<CountryItem>() {
    override fun areItemsTheSame(
        oldItem: CountryItem,
        newItem: CountryItem
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.region == newItem.region && oldItem.code == newItem.code && oldItem.capital == newItem.capital
    }

    override fun areContentsTheSame(
        oldItem: CountryItem,
        newItem: CountryItem
    ): Boolean {
        return oldItem == newItem
    }
}
