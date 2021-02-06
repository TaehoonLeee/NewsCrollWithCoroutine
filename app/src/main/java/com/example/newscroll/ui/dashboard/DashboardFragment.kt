package com.example.newscroll.ui.dashboard

import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newscroll.R
import com.example.newscroll.Room.LikeNews
import com.example.newscroll.Utils.ItemDecoration
import com.example.newscroll.Utils.SwipeHelperCallback
import com.example.newscroll.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.item_news.view.*

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var newsAdapter : NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = CategoryAdapter()
        newsAdapter = NewsAdapter { news -> insert(news) }

        rvCategory.apply {
            isNestedScrollingEnabled = false
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

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
            dashboardViewModel.onRefresh()
        }

    }

    private fun insert(news : News) {
        dashboardViewModel.insert(
            LikeNews(
            id = null,
            platForm = news.platForm,
            thumbnailUrl = news.thumbnailUrl,
            title = news.title,
            description = news.description,
            url = news.url
        ))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dashboardViewModel.category.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    srl.isRefreshing = false
                    categoryAdapter.submitList(it.data)
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
        dashboardViewModel.newsList.observe(viewLifecycleOwner, Observer {
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