package com.example.newscroll.ui.CategoryNews

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newscroll.R
import com.example.newscroll.Room.LikeNews
import com.example.newscroll.Utils.ItemDecoration
import com.example.newscroll.Utils.SwipeHelperCallback
import com.example.newscroll.model.Status
import com.example.newscroll.ui.dashboard.News
import com.example.newscroll.ui.dashboard.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.android.synthetic.main.fragment_category_news.*
import kotlinx.android.synthetic.main.fragment_category_news.rvNewsList
import kotlinx.android.synthetic.main.fragment_category_news.srl
import kotlinx.android.synthetic.main.fragment_dashboard.*

@AndroidEntryPoint
class CategoryNewsFragment : Fragment(R.layout.fragment_category_news) {

    private lateinit var newsAdapter : NewsAdapter
    private val categoryNewsViewModel by viewModels<CategoryNewsViewModel>()
    private val args : CategoryNewsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryToolbar.title = args.title

        newsAdapter = NewsAdapter{ news -> insert(news) }
        val swipeHelperCallback = SwipeHelperCallback().apply {
            setClamp(200f)
        }

        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
        itemTouchHelper.attachToRecyclerView(rvNewsList)

        rvNewsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            addItemDecoration(ItemDecoration())
            setOnTouchListener {_, _ ->
                swipeHelperCallback.removePreviousClamp(this)
                false
            }
        }

        categoryToolbar.setOnMenuItemClickListener{menuItem ->
            when(menuItem.itemId) {
                R.id.btnBack -> {
                    findNavController().popBackStack()
                }
                else -> false
            }
        }
    }

    private fun insert(news: News) {
        categoryNewsViewModel.insert(
            LikeNews(
                id = null,
                platForm = news.platForm,
                thumbnailUrl = news.thumbnailUrl,
                title = news.title,
                description = news.description,
                url = news.url
            )
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoryNewsViewModel.newsList.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    srl.isRefreshing = false
                    newsAdapter.setNewsList(it.data!!)
                }
                Status.ERROR -> {
                    srl.isRefreshing = false
                    Toast.makeText(requireContext(), it.message!!, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    srl.isRefreshing = true
                }
            }
        })
    }

}