package com.geektech.youtubeapi.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

        @GET("playlists")
    fun getPlaylists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResult: Int
    ): Response<Playlist>
   @GET("playlistItems")
   fun  getPlaylistItems(
       @Query("key") apiKey: String,
       @Query("part") part: String,
       @Query("playlistId") playlistId:String,
       @Query("maxResults") maxResult: Int
   ):Response<PLaylistItem>
}