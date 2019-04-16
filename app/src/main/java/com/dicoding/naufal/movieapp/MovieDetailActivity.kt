package com.dicoding.naufal.movieapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView

class MovieDetailActivity : AppCompatActivity() {

    var txtMovieName : TextView? = null
    var txtMovieYear : TextView? = null
    var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val intent = getIntent()
        movie = intent.getParcelableExtra("movie")

        setUp()

    }

    fun setUp() {
        //findviewbyid
        txtMovieName = findViewById(R.id.txtMovieName)
        txtMovieYear = findViewById(R.id.txtMovieYear)

        txtMovieName?.text = movie?.title
        txtMovieYear?.text = movie?.year.toString()

        //untuk menampilkan tombol back di toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            //set tombol back untuk kembali ke activity sebelumnya
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
