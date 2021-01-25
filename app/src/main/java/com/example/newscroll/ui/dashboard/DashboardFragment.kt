package com.example.newscroll.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newscroll.R
import com.example.newscroll.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var newsAdapter : NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = CategoryAdapter()
        newsAdapter = NewsAdapter()

        rvCategory.apply {
            isNestedScrollingEnabled = false
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        rvNewsList.layoutManager = LinearLayoutManager(requireContext())
        rvNewsList.adapter = newsAdapter

        srl.setOnRefreshListener {
            dashboardViewModel.onRefresh()
        }
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