package com.example.vk_android_market_intern.presentation.pokemon_list

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_android_market_intern.domian.pokemon_list.Pokemon
import javax.inject.Inject

class PokemonListAdapter @Inject constructor() : RecyclerView.Adapter<PokemonListViewHolder>() {

    var itemClickListener: ((name: String) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(pokemons: List<Pokemon>) {
        differ.submitList(pokemons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder.from(parent, itemClickListener)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}