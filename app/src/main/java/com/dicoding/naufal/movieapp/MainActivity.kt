package com.dicoding.naufal.movieapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), MovieAdapter.MovieListener{

    //deklarasi variable
    var editMovie: EditText? = null
    var editYear: EditText? = null
    var recycleMovie: RecyclerView? = null

    //nampung item movie
    var listMovie: MutableList<Movie>? = null
    var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    override fun onDeleteClick(movie: Movie) {
        adapter?.deleteMovie(movie)
    }

    override fun onMovieClick(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    fun setUp() {
        //findviewbyid
        editMovie = findViewById(R.id.editMovie)
        editYear = findViewById(R.id.editYear)
        recycleMovie = findViewById(R.id.recyclerMovieList)

        listMovie = mutableListOf(
            Movie("Spiderman Home Coming", 2018),
            Movie("Avenger End Game", 2019),
            Movie("Captain Marvel", 2019),
            Movie("ABC", 1998),
            Movie("DEF", 2018),
            Movie("GHI", 2019),
            Movie("JKL", 2019),
            Movie("MNO", 1998)
        )

        adapter = MovieAdapter(listMovie, this)

        recycleMovie?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleMovie?.adapter = adapter
    }

    fun addMovieClick(view: View?) {
        if (editMovie?.text.toString().isEmpty() == true || editYear?.text?.toString()?.isEmpty() == true) {
            view?.let {
                Snackbar.make(it, "Tidak boleh ada field yang kosong.", Snackbar.LENGTH_SHORT).show()
            }
        } else {
            val movie = Movie(editMovie?.text?.toString(), editYear?.text?.toString()?.toInt())
            adapter?.addMovie(movie)
            view?.let {
                Snackbar.make(it, "Data berhasil ditambahkan.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}