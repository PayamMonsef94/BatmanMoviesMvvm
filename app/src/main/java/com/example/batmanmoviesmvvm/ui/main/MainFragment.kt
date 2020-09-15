package com.example.batmanmoviesmvvm.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.batmanmoviesmvvm.R
import com.example.batmanmoviesmvvm.base.BaseFragment
import com.example.batmanmoviesmvvm.databinding.FragmentMainBinding
import com.example.batmanmoviesmvvm.di.Injectable
import com.example.batmanmoviesmvvm.utils.EventObserver
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var adapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        adapter = MovieAdapter(viewModel)
        binding.mainRecycler.adapter = adapter

        viewModel.openDetailEvent.observe(viewLifecycleOwner, EventObserver {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            findNavController().navigate(action)
        })
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_main
    }

}