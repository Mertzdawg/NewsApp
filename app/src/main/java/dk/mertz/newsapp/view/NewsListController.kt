package dk.mertz.newsapp.view

import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import dk.mertz.newsapp.R
import dk.mertz.newsapp.model.Article
import dk.mertz.newsapp.view_model.NewsListVM
import kotlinx.android.synthetic.main.controller_newslist.view.*
import work.beltran.conductorviewmodel.ViewModelController

class NewsListController : ViewModelController(), NewsAdapter.OnArticleClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_newslist,container,false)
        val vm =  viewModelProvider().get(NewsListVM::class.java)

        val adapter = setRecyclerView(view)
        setHasOptionsMenu(true)

        subscribeNewsCallback(vm, adapter)

        return view
    }

    private fun setRecyclerView(view: View): NewsAdapter {
        val adapter = NewsAdapter(this)
        view.recyclerView.adapter = adapter
        return adapter
    }

    private fun subscribeNewsCallback(vm: NewsListVM, adapter: NewsAdapter) {
        vm.getArticles().observe(this, Observer<List<Article>> {
            if (it != null) {
                adapter.setArticleList(it as ArrayList<Article>)
                view!!.progressBar?.visibility = View.INVISIBLE
            }
        })
    }

    override fun onArticleClick(article: Article) {
        Log.d("onArticleClick","clicked!")
        val bundle = bundleOf(Pair("URL", article.url))
        router.pushController(RouterTransaction
            .with(WebViewController(bundle))
            //fade in and out
            .pushChangeHandler(FadeChangeHandler())
            .popChangeHandler(FadeChangeHandler())
        )
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val searchItem = menu.findItem(R.id.search_articles)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val vm =  viewModelProvider().get(NewsListVM::class.java)
                if (query != null) {
                    vm.refreshQuery(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }


        })


    }

}
