package dk.mertz.newsapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dk.mertz.newsapp.model.Article

import dk.mertz.newsapp.databinding.ListItemArticleBinding

class NewsAdapter(private var listener: OnArticleClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val applicationBinding = ListItemArticleBinding.inflate(layoutInflater, parent, false)
        return RecyclerHolder(applicationBinding)
    }
    private val articles = ArrayList<Article>()

    fun setArticleList(articleList: ArrayList<Article>) {
        articles.addAll(articleList)
        notifyItemRangeInserted(0, articleList.size)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.d("LIST_SIZE","" + articles.size)
        return articles.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = articles[position]
        (holder as RecyclerHolder).bind(article, listener)
    }


    interface OnArticleClickListener {
        fun onArticleClick(article: Article)
    }


    inner class RecyclerHolder(private var applicationBinding: ListItemArticleBinding) : RecyclerView.ViewHolder(applicationBinding.root) {

        fun bind(article: Article, listener: OnArticleClickListener?) {
            applicationBinding.articleTitle.text  = article.title
            applicationBinding.articleAuthor.text = article.author
            applicationBinding.root.setOnClickListener {
                listener?.onArticleClick(article)
            }

        }


    }
}