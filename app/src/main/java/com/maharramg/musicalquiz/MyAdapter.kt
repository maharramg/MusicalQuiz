package com.maharramg.musicalquiz

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val dataList: List<Data>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = dataList[position]

        holder.title.text = currentData.title

        holder.artist.text = currentData.artist.name

        Picasso.get().load(currentData.album.cover).into(holder.image)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, Details::class.java).apply {
                // Pass song data to the details activity
                putExtra("SONG_TITLE", currentData.title)
                putExtra("SONG_ARTIST", currentData.artist.name)
                putExtra("SONG_IMAGE_URL", currentData.album.cover)
                putExtra("SONG_PREVIEW_URL", currentData.preview)
            }
            context.startActivity(intent)
        }

    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val title: TextView
        val artist: TextView

        init {
            image = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.musicTitle)
            artist = itemView.findViewById(R.id.musicArtist)
        }
    }

}