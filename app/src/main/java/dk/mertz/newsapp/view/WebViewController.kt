package dk.mertz.newsapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import dk.mertz.newsapp.R
import kotlinx.android.synthetic.main.controller_webview.view.*

private  var EXTRA_PARAMETER: String = "URL"

class WebViewController(bundle:Bundle) : Controller(bundle) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_webview,container,false)
        Log.d("ARTICLE_URL",param)
        view.webView.loadUrl(param)

        return view
    }

    constructor(param: String) : this(Bundle().apply { putString(EXTRA_PARAMETER,param) })

    private val param by lazy { args.getString(EXTRA_PARAMETER) }




}