package com.geektech.youtubeapi.ui.detail

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.PlaylistDetailModel
import com.geektech.youtubeapi.core.network.result.Status
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.data.remote.PLaylistItem
import com.geektech.youtubeapi.data.remote.Playlist
import com.geektech.youtubeapi.databinding.ActivityDetailBinding
import com.geektech.youtubeapi.ui.detail.adapter.DetailAdapter
import com.geektech.youtubeapi.ui.playlists.adapter.PlaylistsAdapter


class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private lateinit var adapter: DetailAdapter

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]


    }


    override fun isInternet(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?


        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    }

    override fun initViewModel() {
        super.initViewModel()
        val getIntentId =
            intent.getStringExtra(ID)

        val getIntentTitle =
            intent.getStringExtra("title")

        val getIntentDesc =
            intent.getStringExtra("desc")
        viewModel.getPlaylistItem(getIntentId!!).observe(this) {

            binding.recyclerview.adapter = adapter
            binding.progressBar.isVisible = false
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.addList(it.data?.items!! as List<PLaylistItem.Item>)
                    binding.tvTitle.text = getIntentTitle
                    binding.description.text = getIntentDesc
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false

                }
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }

        }
    }


    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        adapter = DetailAdapter()
        binding.recyclerview.adapter = adapter
    }


    companion object {
        const val ID = "ID"


    }
}


