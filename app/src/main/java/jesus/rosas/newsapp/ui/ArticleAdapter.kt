package jesus.rosas.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jesus.rosas.newsapp.databinding.CardLayoutBinding
import jesus.rosas.newsapp.listeners.RecyclerArticleListener
import jesus.rosas.newsapp.model.Article

class ArticleAdapter(
    private val articles: List<Article>,
    private val listener: RecyclerArticleListener
) : RecyclerView.Adapter<ArticlesViewHolder>() {

    private var _binding: CardLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {

        _binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) =
        holder.bind(articles[position], listener)

    override fun getItemCount() = articles.size

}