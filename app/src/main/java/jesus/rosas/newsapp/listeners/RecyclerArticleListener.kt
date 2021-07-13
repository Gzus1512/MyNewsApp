package jesus.rosas.newsapp.listeners

import jesus.rosas.newsapp.model.Article

interface RecyclerArticleListener {
    fun onClick(flight: Article, position: Int)
}