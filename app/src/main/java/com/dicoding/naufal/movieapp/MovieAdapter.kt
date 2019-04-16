package com.dicoding.naufal.movieapp

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MovieAdapter(var list: MutableList<Movie>?, var listener: MovieListener?) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context)
                .inflate(R.layout.item_movie, p0, false)
        )
    }

    override fun getItemCount(): Int {
        list?.size?.let {
            return it
        } ?: run {
            return 0
        }
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(list?.get(p1), listener)
    }

    fun addMovie(movie: Movie) {
        list?.add(movie)
        notifyDataSetChanged()
    }

    fun deleteMovie(movie: Movie) {
        list?.remove(movie)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val txtMovieName = view.findViewById<TextView?>(R.id.txtMovieName)
        private val txtMovieYear = view.findViewById<TextView?>(R.id.txtMovieYear)
        private val btnDelete = view.findViewById<AppCompatImageButton?>(R.id.btnDelete)
        private val linearLayout = view.findViewById<ConstraintLayout>(R.id.linearLayout)

        fun bindItem(movie: Movie?, listener: MovieListener?) {
            txtMovieName?.text = movie?.title
            txtMovieYear?.text = movie?.year?.toString()

            btnDelete?.setOnClickListener {
                movie?.let { m ->
                    listener?.onDeleteClick(m)
                }
            }

            linearLayout?.setOnClickListener {
                movie?.let { m ->
                    listener?.onMovieClick(m)
                }
            }


        }
    }

    interface MovieListener {
        fun onDeleteClick(movie: Movie)
        fun onMovieClick(movie: Movie)
    }
}