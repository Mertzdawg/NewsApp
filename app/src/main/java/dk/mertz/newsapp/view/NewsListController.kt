package dk.mertz.newsapp.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dk.mertz.newsapp.R
import dk.mertz.newsapp.model.Article
import dk.mertz.newsapp.view_model.NewsListVM
import work.beltran.conductorviewmodel.ViewModelController
import kotlinx.android.synthetic.main.controller_newslist.view.*

class NewsListController : ViewModelController(), NewsAdapter.OnArticleClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_newslist,container,false)
        val vm =  viewModelProvider().get(NewsListVM::class.java)

        val adapter = setRecyclerView(view)

        subscribeDataCallback(vm, adapter)

        return view
    }

    private fun setRecyclerView(view: View): NewsAdapter {
        val adapter = NewsAdapter(this)

        val dataList: ArrayList<Article> = ArrayList()

        val newsLinearLayoutManager = LinearLayoutManager(view.context)
        newsLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        view.recyclerView.layoutManager = newsLinearLayoutManager

        adapter.setArticleList(dataList)
        view.recyclerView.adapter = adapter
        return adapter
    }

    private fun subscribeDataCallback(
        vm: NewsListVM,
        adapter: NewsAdapter
    ) {
        vm.getArticles().observe(this, Observer<List<Article>> {

            if (it != null) {
                adapter.setArticleList(ArrayList(it))
            }

        })
    }


    override fun onArticleClick(position: Int) {

    }

}
