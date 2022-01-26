package com.example.katalogfilm_byfadli.ui.movie

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

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listOfMovies: List<MovieEntity> = Collections.emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MovieEntity>) {
        listOfMovies = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listOfMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listOfMovies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = movie.date
                tvItemScoreValue.text = movie.voteAverage.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, movie)
                    itemView.context.startActivity(intent)
                }
                tvItemGenre.text = movie.genreIds
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    ).transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgPoster)
            }
        }
    }
}