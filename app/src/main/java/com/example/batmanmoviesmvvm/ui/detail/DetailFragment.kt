package com.example.batmanmoviesmvvm.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.batmanmoviesmvvm.R
import com.example.batmanmoviesmvvm.base.BaseFragment
import com.example.batmanmoviesmvvm.databinding.FragmentDetailBinding
import com.example.batmanmoviesmvvm.di.Injectable
import javax.inject.Inject

class DetailFragment : BaseFragment<FragmentDetailBinding>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DetailViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: DetailFragmentArgs by navArgs()
        viewModel.getMovieDetail(safeArgs.movieId)

        binding.viewModel = viewModel
        binding.detailIvBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.executePendingBindings()
        viewModel.detail.observe(viewLifecycleOwner, {
            try {
                binding.detailProgressMetascore.setProgress(it.Metascore.toFloat())
            } catch (e: NumberFormatException){
                binding.detailProgressMetascore.setProgress(0F)
            }
            binding.detailProgressImdb.setProgress(it.imdbRating.toFloat() * 10)
        })

    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_detail
    }

}