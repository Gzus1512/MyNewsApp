package jesus.rosas.newsapp.ui

import androidx.recyclerview.widget.RecyclerView
import jesus.rosas.newsapp.databinding.CardLayoutBinding
import jesus.rosas.newsapp.listeners.RecyclerArticleListener
import jesus.rosas.newsapp.model.Article

class ArticlesViewHolder(private var binding: CardLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, listener: RecyclerArticleListener) =
        with(binding) {

            newsTitle.text = article.title
            newsAuthor.text = article.author
            newsDescription.text = article.description

            itemImage.setImageResource(article.imgResource)
            cardView.setOnClickListener { listener.onClick(article, adapterPosition) }
        }
}