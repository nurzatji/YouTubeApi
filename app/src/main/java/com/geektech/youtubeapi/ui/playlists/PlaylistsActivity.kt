package com.geektech.youtubeapi.ui.playlists

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.core.view.isVisible

import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.core.network.result.Status
import com.geektech.youtubeapi.core.ui.BaseActivity

import com.geektech.youtubeapi.databinding.ActivityPlaylistsBinding
import com.geektech.youtubeapi.data.remote.Playlist
import com.geektech.youtubeapi.ui.detail.DetailActivity
import com.geektech.youtubeapi.ui.playlists.adapter.PlaylistsAdapter

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    private lateinit var adapter: PlaylistsAdapter
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun isInternet(): Boolean {

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    }

    override fun initViews() {
        super.initViews()
         adapter = PlaylistsAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.getPlaylistViewMode().observe(this){

            binding.recyclerView.adapter = adapter
            binding.progressBar.isVisible = false
            when(it.status){
                Status.SUCCESS -> {
                    adapter.addList(it.data?.items!! as List<Playlist.Item>)
                }
                Status.ERROR ->{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false

                }
                Status.LOADING ->{
binding.progressBar.isVisible = true
                }
            }

        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun onClick(item: Playlist.Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(ID, item.id)
        intent.putExtra("title", item.snippet?.title)
        intent.putExtra("desc", item.snippet?.description)
        startActivity(intent)
    }

    override fun isConnection() {
        super.isConnection()
        if (!isInternet()){
            binding.included.included.isVisible = true
        }
    }

    companion object {
        const val ID = "ID"
    }

}
