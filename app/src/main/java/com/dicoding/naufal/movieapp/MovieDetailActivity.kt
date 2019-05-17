package com.dicoding.naufal.movieapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val intent = getIntent()
        val title = intent.getStringExtra("title")
        val year = intent.getIntExtra("year", 0)
        movie = Movie(title, year)
        setUp()

    }

    fun setUp() {
        //findviewbyid
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
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
