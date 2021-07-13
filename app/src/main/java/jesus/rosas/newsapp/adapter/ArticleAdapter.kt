package jesus.rosas.newsapp.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jesus.rosas.newsapp.R
import jesus.rosas.newsapp.listeners.RecyclerArticleListener
import jesus.rosas.newsapp.model.Article
import jesus.rosas.newsapp.model.inflate
import kotlinx.android.synthetic.main.card_layout.view.*

class ArticleAdapter(private val articles: List<Article>, private val listener: RecyclerArticleListener)
    : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(article: Article, listener: RecyclerArticleListener) = with(itemView){
            newsTitle.text = article.title
            newsAuthor.text = article.author
            newsDescription.text = article.description
            itemImage.setImageResource(article.imgResource)

            setOnClickListener { listener.onClick(article, adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.card_layout))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(articles[position], listener)

    override fun getItemCount() = articles.size
}