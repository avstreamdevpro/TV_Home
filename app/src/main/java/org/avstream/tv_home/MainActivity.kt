package org.avstream.tv_home

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : FragmentActivity() {

    lateinit var moviesFragment: MoviesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesFragment = MoviesFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.movies_fragment, moviesFragment)
        transaction.commit()

        val gson = Gson()
        val i: InputStream = assets.open("movies.json")
        val br = BufferedReader(InputStreamReader(i))
        val dataList: DataModel = gson.fromJson(br, DataModel::class.java)

        moviesFragment.bindData(dataList)
        moviesFragment.setOnContentSelectedListener {
            updateBanner(it)
        }
    }

    private fun updateBanner(detail: Detail) {

        txtTitle.text = detail.title
        description.text = detail.overview
        val url = "https://www.themoviedb.org/t/p/w780" + detail.backdrop_path
        Glide.with(this).load(url).into(img_banner)
    }
}