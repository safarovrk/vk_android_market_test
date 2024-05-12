package com.example.vk_android_market_intern.presentation.pokemon_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.vk_android_market_intern.R
import com.example.vk_android_market_intern.data.response.ResponseStates
import com.example.vk_android_market_intern.databinding.FragmentPokemonDetailsBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PokemonDetailsFragment : Fragment() {

    companion object {
        private const val VIEW_FLIPPER_DETAILS = 0
        private const val VIEW_FLIPPER_ERROR = 1
        private const val VIEW_FLIPPER_LOADING = 2
    }

    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: PokemonDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by createViewModelLazy(
        PokemonDetailsViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater)
        binding.materialToolbar.title = args.name.replaceFirstChar { it.uppercaseChar() }
        viewModel.currentPokemonName.value = args.name
        viewModel.onLoadData(args.name)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
        setStateObserver()
    }

    private fun setListeners() {
        binding.errorRefreshButton.setOnClickListener {
            viewModel.currentPokemonName.value?.let { name -> viewModel.onLoadData(name) }
        }
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setStateObserver() {
        viewModel.detailState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseStates.Success -> {
                    binding.height.text = getString(
                        R.string.height,
                        state.data.height
                    )
                    binding.weight.text = getString(
                        R.string.weight,
                        state.data.weight
                    )
                    binding.stats.text = getString(
                        R.string.stats,
                        state.data.stats
                    )
                    Glide.with(binding.imageFront)
                        .load(state.data.frontSprite)
                        .transform(
                            MultiTransformation(
                                CenterCrop(),
                                RoundedCorners(16)
                            )
                        )
                        .placeholder(R.drawable.pikachu_loader_insets)
                        .into(binding.imageFront)
                    Glide.with(binding.imageBack)
                        .load(state.data.backSprite)
                        .transform(
                            MultiTransformation(
                                CenterCrop(),
                                RoundedCorners(16)
                            )
                        )
                        .placeholder(R.drawable.pikachu_loader_insets)
                        .into(binding.imageBack)

                    binding.viewFlipper.displayedChild = VIEW_FLIPPER_DETAILS
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
