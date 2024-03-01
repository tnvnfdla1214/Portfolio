package com.example.portfolio.audioMedia.audio

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.portfolio.R
import com.example.portfolio.audioMedia.audio.model.PlayTrack
import com.example.portfolio.databinding.ItemMusicBinding

class MusicListAdapter(
    private val tracks: List<PlayTrack> = emptyList(),
    private val onMusicSelected: (PlayTrack) -> Unit,
) : RecyclerView.Adapter<MusicListAdapter.MusicListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListViewHolder {
        return MusicListViewHolder(
            ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: MusicListViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    override fun getItemCount(): Int = tracks.size

//    private var onItemClickListener: ((PlayTrack) -> Unit)? = null
//    fun setOnItemClickListener(listener: (PlayTrack) -> Unit) {
//        onItemClickListener = listener
//    }

    inner class MusicListViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(track: PlayTrack) {
            binding.run {
                title.text = track.title
                artist.text = track.artist
                root.setOnClickListener {
                    track.playState = !track.playState
                    togglePlaying(track.playState)
                    onMusicSelected.invoke(track)
                }
            }
            Glide.with(itemView)
                .load(track.albumArt)
                .error(R.drawable.music_off)
                .into(binding.albumArt)
        }

        private fun togglePlaying(playState: Boolean) {
            Log.d("qweqwe","playState : " + playState)
            binding.playPause.setImageResource(
                if (playState) R.drawable.ic_pause else R.drawable.ic_play,
            )
        }
    }
}