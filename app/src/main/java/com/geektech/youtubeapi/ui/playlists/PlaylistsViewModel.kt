package com.geektech.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.geektech.youtubeapi.App
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.Playlist


class PlaylistsViewModel: BaseViewModel() {


    fun getPlaylistViewMode(): LiveData<com.geektech.youtubeapi.core.network.result.Resource<Playlist>> {
        return App.repository.getPlaylists()
    }
//
//        val data = MutableLiveData<Resource<Playlist>>()
//
//        data.value = Resource.loading()
//            apiService.getPlaylists(BuildConfig.API_KEY, "contentDetails,snippet", "UCWOA1ZGywLbqmigxE4Qlvuw",30)
//            .enqueue(object : Callback<Playlist>{
//                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
//                    if (response.isSuccessful){
//                        data.value = Resource.success(response.body())
//                    }
//                }
//
//                override fun onFailure(call: Call<Playlist>, t: Throwable) {
//                    data.value = Resource.error(t.message.toString(),null)
//                    print(t.stackTrace)
//                }
//            })
//
//        return data
//    }
}