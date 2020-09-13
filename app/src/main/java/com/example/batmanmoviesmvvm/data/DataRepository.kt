package com.example.batmanmoviesmvvm.data

import com.example.batmanmoviesmvvm.data.api.ApiService
import com.example.batmanmoviesmvvm.data.db.AppDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val dao: AppDao,
    private val apiService: ApiService
) {

}