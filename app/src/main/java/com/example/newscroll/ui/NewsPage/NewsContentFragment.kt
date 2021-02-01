package com.example.newscroll.ui.NewsPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.newscroll.R
import kotlinx.android.synthetic.main.fragment_news_content.*

class NewsContentFragment : Fragment(R.layout.fragment_news_content) {
    val args : NewsContentFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = args.url

        newsView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                request?.url
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        newsView.loadUrl(url)
    }

}