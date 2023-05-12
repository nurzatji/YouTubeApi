package com.geektech.youtubeapi.ui.detail

import androidx.lifecycle.LiveData
import com.geektech.youtubeapi.App
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.PLaylistItem
import com.geektech.youtubeapi.data.remote.Playlist

class DetailViewModel: BaseViewModel() {

    fun getPlaylistItem(playlistId: String): LiveData<com.geektech.youtubeapi.core.network.result.Resource<PLaylistItem>> {
        return App.repository.getPlaylistItem(playlistId)
    }

}