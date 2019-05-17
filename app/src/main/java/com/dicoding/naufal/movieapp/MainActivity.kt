package com.dicoding.naufal.movieapp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.InputMethodManager.RESULT_UNCHANGED_SHOWN
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {

    var movieAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    private fun setUp() {
        //buat objek adapter + masukin data pertama
        movieAdapter = MovieAdapter(movieData){
            val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
            intent.putExtra("title", it.title)
            intent.putExtra("year", it.year)
            startActivity(intent)
        }

        recyclerMovieList?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,
                LinearLayoutManager.VERTICAL, false)

            adapter = movieAdapter
        }
    }

    fun addMovieClick(view: View?) {
        //validasi
        if(editMovie?.text.isNullOrEmpty() || editYear?.text.isNullOrEmpty()){
            Toast.makeText(this, "Isi semua kolom",
                Toast.LENGTH_SHORT).show()
        } else {
            //membuat object movie baru
            val movie = Movie(
                editMovie?.text.toString(),
                editYear?.text.toString().toInt()
            )

            //add movie to list in adapter
            movieAdapter?.addMovie(movie)

            //hide keyboard
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(
                currentFocus.windowToken,
                InputMethodManager.RESULT_UNCHANGED_SHOWN
            )

            //clear form
            editMovie?.text?.clear()
            editYear?.text?.clear()


            movieAdapter?.list?.size?.minus(1)?.let {
                recyclerMovieList.smoothScrollToPosition(it)
            }

        }
    }
}