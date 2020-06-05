package com.example.project_work

import android.annotation.SuppressLint
import  android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieListAdapter(var movies: List<Movie>?= null)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies?.get(position))
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    fun clearAll(){
        (movies as? ArrayList<Movie>)?.clear()
        notifyDataSetChanged()
        Log.d("afd", "cclear")
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val photo:ImageView = itemView.findViewById(R.id.main_poster)
        private val title:TextView = itemView.findViewById(R.id.title)
        private val overview:TextView = itemView.findViewById(R.id.genre)
        private val rating:TextView = itemView.findViewById(R.id.rating)

        fun bind(movie: Movie?) {
            Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500${movie?.poster_path}").into(photo)
            title.text = movie?.title
            overview.text = movie?.overview
            rating.text = movie?.vote_average.toString()
        }
    }
}