package com.example.streammovies

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cronocode.moviecatalog.services.MovieApiService
import com.example.streammovies.adapter.PostAdapter
import com.example.streammovies.response.PostModel
import com.example.streammovies.services.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvMoviesList)

        val serviceGenerator = ServiceGenerator.buildService(MovieApiService::class.java)
        val call = serviceGenerator.getPosts()

        call.enqueue(object : Callback<ArrayList<PostModel>> {
            override fun onResponse(Call: Call<ArrayList<PostModel>>, response: Response<ArrayList<PostModel>>) {
                if(response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = PostAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(Call: Call<ArrayList<PostModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("Error", t.message.toString())
            }
        })
    }
}