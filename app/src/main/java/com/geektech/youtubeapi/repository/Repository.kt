package com.geektech.youtubeapi.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData

import com.geektech.youtubeapi.BuildConfig
import com.geektech.youtubeapi.core.network.RetrofitClient
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.data.remote.ApiService
import com.geektech.youtubeapi.data.remote.PLaylistItem
import com.geektech.youtubeapi.data.remote.Playlist
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()

    }

    fun getPlaylists(): LiveData<Resource<Playlist>> = liveData(Dispatchers.IO) {


        emit(Resource.loading())


        val response = apiService.getPlaylists(
            BuildConfig.API_KEY, "contentDetails,snippet", "UCWOA1ZGywLbqmigxE4Qlvuw", 30
        )
        emit(
            if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                response.message(), response.body(), response.code()
            )
        )
    }



    fun getPlaylistItem(
        playlistId: String
    ): LiveData<Resource<PLaylistItem>> = liveData(Dispatchers.IO) {
      emit(Resource.loading())

        val response= apiService.getPlaylistItems(
            BuildConfig.API_KEY, part = "snippet,contentDetails", playlistId, 30
        )
        emit(if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
            response.message(),response.body(),response.code()
        ))
    }


    }




