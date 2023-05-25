package com.example.streammovies.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.streammovies.R
import com.example.streammovies.VideoPlayerActivity
import com.example.streammovies.response.PostModel
import com.squareup.picasso.Picasso

class PostAdapter(private var postModel: ArrayList<PostModel>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        context = parent.context
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(postModel[position])
    }

    override fun getItemCount(): Int {
        return postModel.size
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val mvTitle: TextView = itemView.findViewById(R.id.movieTitle)
        private val mvPoster: ImageView = itemView.findViewById(R.id.moviePoster)
        private val mvOverview: TextView = itemView.findViewById(R.id.movieOverview)
        private val shareButton: Button = itemView.findViewById(R.id.shareButton)

        fun bindView(postModel: PostModel) {
            mvTitle.text = postModel.title
            mvOverview.text = postModel.overview
            Picasso.get()
                .load(postModel.poster_path)
                .into(mvPoster)

            mvPoster.setOnClickListener {
                val intent = Intent(context, VideoPlayerActivity::class.java)
                intent.putExtra("video", postModel.video)
                context.startActivity(intent)
            }

            shareButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, postModel.overview)

                intent.type = "text/plain"
                context.startActivity(Intent.createChooser(intent, "Share using..."))
            }
        }
    }
}

