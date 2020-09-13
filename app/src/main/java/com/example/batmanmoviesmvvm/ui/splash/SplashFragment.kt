package com.example.batmanmoviesmvvm.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.batmanmoviesmvvm.R
import com.example.batmanmoviesmvvm.di.Injectable
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class SplashFragment : Fragment(), Injectable {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timer("Splash", false).schedule(2500) {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)

        }
    }

}