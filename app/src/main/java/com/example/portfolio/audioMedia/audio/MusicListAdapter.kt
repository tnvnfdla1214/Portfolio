package com.example.portfolio.audioMedia.audio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Book
import com.example.domain.model.Music
import com.example.portfolio.R
import com.example.portfolio.databinding.ItemMusicBinding

class MusicListAdapter(
    private val musics: List<Music>,
) : RecyclerView.Adapter<MusicListAdapter.MusicListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListViewHolder {
        return MusicListViewHolder(
            ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: MusicListViewHolder, position: Int) {
        holder.bind(musics[position])
    }

    override fun getItemCount(): Int = musics.size

    private var onItemClickListener: ((Book) -> Unit)? = null
    fun setOnItemClickListener(listener: (Book) -> Unit) {
        onItemClickListener = listener
    }

    class MusicListViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(music: Music) {
            binding.run {
                title.text = music.title
                artist.text = music.artist
            }
            Glide.with(itemView)
                .load(music.albumArt)
                .error(R.drawable.music_off)
                .into(binding.albumArt)
        }
    }
}