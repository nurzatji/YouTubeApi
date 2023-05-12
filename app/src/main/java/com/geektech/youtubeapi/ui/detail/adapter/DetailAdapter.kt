package com.geektech.youtubeapi.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geektech.youtubeapi.data.remote.PLaylistItem
import com.geektech.youtubeapi.databinding.ItemPlaylistBinding
import com.geektech.youtubeapi.loadImage

class DetailAdapter :
    Adapter<DetailAdapter.DetailViewHolder>() {

    private var list = ArrayList<PLaylistItem.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<PLaylistItem.Item>) {
        this.list = list as ArrayList<PLaylistItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class DetailViewHolder(private val binding: ItemPlaylistBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: PLaylistItem.Item) {
            with(binding) {
                tvPlaylistName.text = item.snippet?.title
                tvNumberOfVideos.text = item.contentDetails?.itemCount.toString() + " video series"
                ivPlaylist.loadImage(item.snippet?.thumbnails?.default?.url!!)
            }
        }
    }
}