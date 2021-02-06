package com.example.newscroll.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.rvNewsList
import kotlinx.android.synthetic.main.fragment_dashboard.srl
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        srl.setOnRefreshListener {
            homeViewModel.onRefresh()
        }
    }

    private fun insert(news: News) {
        homeViewModel.insert(
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

        homeViewModel.newsList.observe(viewLifecycleOwner, Observer {
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