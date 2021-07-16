package jesus.rosas.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import jesus.rosas.newsapp.R
import jesus.rosas.newsapp.databinding.FragmentLocalBinding
import jesus.rosas.newsapp.listeners.RecyclerArticleListener
import jesus.rosas.newsapp.model.Article
import jesus.rosas.newsapp.model.toast

class LocalFragment : Fragment(), RecyclerArticleListener {

    private val list: ArrayList<Article> by lazy { getArticles() }

    private val layoutManager by lazy { LinearLayoutManager(requireContext()) }
    private var _binding: FragmentLocalBinding? = null
    private val binding get() = _binding!!


    private fun getArticles(): ArrayList<Article> {
        return object : ArrayList<Article>() {
            init {
                add(Article("John", "Local", "Body Local news", R.drawable.jacarandas))
                add(Article("Manuel", "Country", "Body Country news", R.drawable.country))
                add(
                    Article(
                        "Sebastian",
                        "International",
                        "Body International news",
                        R.drawable.laterre
                    )
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocalBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    private fun init() {

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = ArticleAdapter(list, this)
        binding.recyclerView.layoutManager = layoutManager
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LocalFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(flight: Article, position: Int) {

        requireActivity().toast("Let´s go to ${flight.title}!")
    }
}