package com.geektech.youtubeapi.ui.detail

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.base.BaseActivity
import com.geektech.youtubeapi.databinding.ActivityDetailBinding
import com.geektech.youtubeapi.ui.playlists.PlaylistsActivity.Companion.ID

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        getExtra()
    }

    private fun getExtra() {
        val getIntent =
        intent.getStringExtra(ID)
        Toast.makeText(this, getIntent, Toast.LENGTH_SHORT).show()
    }


}