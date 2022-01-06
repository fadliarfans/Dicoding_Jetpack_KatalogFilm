package com.example.katalogfilm_byfadli.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.data.TvShowEntity
import com.example.katalogfilm_byfadli.databinding.ItemTvShowBinding
import com.example.katalogfilm_byfadli.ui.detail_tvshow.DetailTvShowActivity
import com.example.katalogfilm_byfadli.utils.GlobalFunctions

class TvShowAdapter(private val listOfTvShows: List<TvShowEntity>) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listOfTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listOfTvShows.size

    inner class TvShowViewHolder(private val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(TvShow: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = TvShow.name
                tvItemDate.text = TvShow.firstAirDate.toString().subSequence(0, 4)
                tvItemScoreValue.text = TvShow.voteAverage.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_DATA, TvShow.id)
                    itemView.context.startActivity(intent)
                }
                tvItemGenre.text = GlobalFunctions.generateGenre(TvShow.genreIds)
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${TvShow.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    ).transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgPoster)
            }
        }
    }
}