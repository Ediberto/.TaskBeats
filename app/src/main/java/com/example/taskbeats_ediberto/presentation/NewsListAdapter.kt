package com.example.taskbeats_ediberto.presentation

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
//import androidx.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.taskbeats_ediberto.R
import com.example.taskbeats_ediberto.data.News

class NewsListAdapter : ListAdapter<News, NewsListViewHolder>(NewsListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        //CRIAR UM VIEWHOLDER
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        //RETORNAR UM VIEWHOLDER PARA CRIAR UM NOVO VIEWHOLDER
        return NewsListViewHolder(view)
    }
    //ATRELAR O ITEM
    //DEVEMOS RECUPERAR O ÍTEM DA POSIÇÃO DA LISTA
    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val news = getItem(position)
        //PASSAR O item e chamar a função bind() descrita abaixo passando
        // O click
        holder.bind(news) //CRIAR A FUNÇÃO bind() abaixo
    }
    companion object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.titlo == newItem.titlo &&
                    oldItem.imgUrl == newItem.imgUrl
        }
    }
}
class NewsListViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val tvTitlo = view.findViewById<TextView>(R.id.tv_news_titlo)
    private val imgNews = view.findViewById<ImageView>(R.id.iv_news)

    fun bind(
        news: News
    ) {
        tvTitlo.text = news.titlo
        imgNews.load(news.imgUrl) {
            transformations(RoundedCornersTransformation(radius = 12f))
        }
    }
}