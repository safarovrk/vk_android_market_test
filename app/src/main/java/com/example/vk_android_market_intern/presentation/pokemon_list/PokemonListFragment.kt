package com.example.vk_android_market_intern.presentation.pokemon_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.vk_android_market_intern.R
import com.example.vk_android_market_intern.data.response.ResponseStates
import com.example.vk_android_market_intern.databinding.FragmentPokemonListBinding
import com.example.vk_android_market_intern.domian.pokemon_list.Pokemon
import com.example.vk_android_market_intern.presentation.pokemon_details.PokemonDetailsViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PokemonListFragment : Fragment() {

    companion object {
        private const val VIEW_FLIPPER_LIST = 0
        private const val VIEW_FLIPPER_ERROR = 1
        private const val VIEW_FLIPPER_LOADING = 2
    }

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by createViewModelLazy(
        PokemonListViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    @Inject
    lateinit var adapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
        setStateObserver()
        val recyclerView = binding.listRecyclerView

        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.pokemons_list_divider)
            ?.let { dividerItemDecoration.setDrawable(it) }

        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.adapter = adapter

        adapter.itemClickListener = this::navigateToDetails
    }

    private fun setListeners() {
        binding.errorRefreshButton.setOnClickListener {
            viewModel.onLoadData()
        }
    }

    private fun setStateObserver() {
        viewModel.listState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseStates.Success -> {
                    val list = mutableListOf<Pokemon>()
                    state.data.forEach { pokemon -> list.add(pokemon) }
                    adapter.submitList(list)
                    binding.viewFlipper.displayedChild = VIEW_FLIPPER_LIST
                }

                is ResponseStates.Failure -> {
                    binding.unexpectedErrorDescription.text = state.e.message
                    binding.viewFlipper.displayedChild = VIEW_FLIPPER_ERROR
                }

                is ResponseStates.Loading -> {
                    binding.viewFlipper.displayedChild = VIEW_FLIPPER_LOADING
                }
            }
        }
    }

    private fun navigateToDetails(name: String) {
        findNavController().navigate(
            PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(name)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}