package com.example.newscroll.ui.CategoryNews

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newscroll.R
import com.example.newscroll.model.Status
import com.example.newscroll.ui.dashboard.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.android.synthetic.main.fragment_category_news.*

@AndroidEntryPoint
class CategoryNewsFragment : Fragment(R.layout.fragment_category_news) {

    private lateinit var newsAdapter : NewsAdapter
    private val categoryNewsViewModel by viewModels<CategoryNewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter()
        rvNewsList.layoutManager = LinearLayoutManager(requireContext())
        rvNewsList.adapter = newsAdapter

        categoryToolbar.setOnMenuItemClickListener{menuItem ->
            when(menuItem.itemId) {
                R.id.btnBack -> {
                    findNavController().popBackStack()
                }
                else -> false
            }
        }
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