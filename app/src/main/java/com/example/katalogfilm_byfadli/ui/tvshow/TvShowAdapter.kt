package com.example.katalogfilm_byfadli.ui.tvshow

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.data.MovieEntity
import com.example.katalogfilm_byfadli.databinding.ItemMovieBinding
import com.example.katalogfilm_byfadli.ui.detail.DetailActivity
import java.util.*

class TvShowAdapter :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listOfTvShows: List<MovieEntity> = Collections.emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MovieEntity>) {
        listOfTvShows = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listOfTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listOfTvShows.size

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: MovieEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemDate.text = tvShow.date
                tvItemScoreValue.text = tvShow.voteAverage.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, tvShow)
                    itemView.context.startActivity(intent)
                }
                tvItemGenre.text = tvShow.genreIds
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${tvShow.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    ).transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgPoster)
            }
        }
    }
}