package com.example.batmanmoviesmvvm.di

import android.app.Application
import androidx.room.Room
import com.example.batmanmoviesmvvm.data.api.ApiService
import com.example.batmanmoviesmvvm.data.db.AppDatabase
import com.example.batmanmoviesmvvm.data.db.AppDao
import com.example.batmanmoviesmvvm.utils.Constants.Companion.BASE_URL
import com.example.batmanmoviesmvvm.utils.Constants.Companion.DB_NAME
import com.example.batmanmoviesmvvm.utils.Constants.Companion.REQUEST_TIMEOUT_DURATION
import com.example.batmanmoviesmvvm.utils.Network
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("Accept", "application/json")
            requestBuilder.addHeader("Cache-control", "no-cache")
            requestBuilder.addHeader("Content-Type", "application/json")
            requestBuilder.method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .cache(null)
            .connectTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
            .build()
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

    @Provides
    fun provideNetwork(app: Application): Network {
        return Network(app)
    }

}
