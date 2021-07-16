package jesus.rosas.newsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jesus.rosas.newsapp.R
import jesus.rosas.newsapp.adapter.ArticleAdapter
import jesus.rosas.newsapp.listeners.RecyclerArticleListener
import jesus.rosas.newsapp.model.Article
import jesus.rosas.newsapp.model.toast

class LocalFragment : Fragment() {

    private val list: ArrayList<Article> by lazy { getArticles() }

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private val layoutManager by lazy { LinearLayoutManager(context) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private fun getArticles(): ArrayList<Article>{
        return object: ArrayList<Article>() {
            init {
                add(Article("John", "Local", "Body Local news" , R.drawable.jacarandas))
                add(Article("Manuel", "Country", "Body Country news" , R.drawable.country))
                add(Article("Sebastian", "International", "Body International news" , R.drawable.laterre))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_local, container, false)

        recycler = rootView.recyclerView as RecyclerView
        setRecyclerView()

        return rootView
    }

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = (ArticleAdapter(list, object:
            RecyclerArticleListener {
            override fun onClick(article: Article, position: Int) {
                activity?.toast("Let´s go to ${article.title}!")
            }

        }))
        recycler.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LocalFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}