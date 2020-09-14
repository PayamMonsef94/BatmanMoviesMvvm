package com.example.batmanmoviesmvvm.di

import com.example.batmanmoviesmvvm.ui.main.MainFragment
import com.example.batmanmoviesmvvm.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment



}
