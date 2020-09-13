package com.example.batmanmoviesmvvm.di

import android.app.Application
import androidx.room.Room
import com.example.batmanmoviesmvvm.data.api.ApiService
import com.example.batmanmoviesmvvm.data.db.AppDatabase
import com.example.batmanmoviesmvvm.data.db.AppDao
import com.example.batmanmoviesmvvm.utils.Constants.Companion.BASE_URL
import com.example.batmanmoviesmvvm.utils.Constants.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppDao(db: AppDatabase): AppDao {
        return db.appDao()
    }

}
