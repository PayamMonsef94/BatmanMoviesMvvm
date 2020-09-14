package com.example.batmanmoviesmvvm.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.batmanmoviesmvvm.R
import com.example.batmanmoviesmvvm.base.BaseFragment
import com.example.batmanmoviesmvvm.databinding.FragmentDetailBinding
import com.example.batmanmoviesmvvm.databinding.FragmentMainBinding
import com.example.batmanmoviesmvvm.di.Injectable
import com.example.batmanmoviesmvvm.utils.EventObserver
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

        /*viewModel.openDetailEvent.observe(viewLifecycleOwner, EventObserver {
            Log.i("ttt", "onViewCreated: $it")
        })
*/
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_detail
    }

}