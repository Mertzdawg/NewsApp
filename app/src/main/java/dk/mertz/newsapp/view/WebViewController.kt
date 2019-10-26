package dk.mertz.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import dk.mertz.newsapp.R
import kotlinx.android.synthetic.main.controller_webview.view.*

private  var ARTICLE_URL: String? = "URL"

class WebViewController(bundle:Bundle) : Controller(bundle) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_webview,container,false)
        //should only be enabled for trusted domains to avoid XSS attacks
        view.webView.settings.javaScriptEnabled = true
        view.webView.loadUrl(param)

        return view
    }

    constructor(param: String) : this(Bundle().apply { putString(ARTICLE_URL,param) })

    private val param by lazy { args.getString(ARTICLE_URL) }



}