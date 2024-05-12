package com.example.vk_android_market_intern.presentation.pokemon_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.vk_android_market_intern.R
import com.example.vk_android_market_intern.databinding.ListPokemonItemBinding
import com.example.vk_android_market_intern.domian.pokemon_list.Pokemon

class PokemonListViewHolder private constructor(
    private val binding: ListPokemonItemBinding,
    private val onClick: ((name: String) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, onClick: ((name: String) -> Unit)?): PokemonListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListPokemonItemBinding.inflate(layoutInflater, parent, false)
            return PokemonListViewHolder(binding, onClick)
        }
    }

    fun bind(item: Pokemon) {
        binding.name.text = item.name.replaceFirstChar { it.uppercaseChar() }
        Glide.with(binding.image)
            .load(item.sprite)
            .transform(
                MultiTransformation(
                    CenterCrop(),
                    RoundedCorners(16)
                )
            )
            .placeholder(R.drawable.pikachu_loader_insets)
            .into(binding.image)
        binding.root.setOnClickListener {
            onClick?.let { onClick -> onClick(item.name) }
        }
    }
}