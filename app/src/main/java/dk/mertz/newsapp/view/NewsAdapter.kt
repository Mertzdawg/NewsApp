package dk.mertz.newsapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dk.mertz.newsapp.R
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
            applicationBinding.articleDescription.text = article.description
            //opens WebView of article
            applicationBinding.root.setOnClickListener {
                listener?.onArticleClick(article)
            }
            //Article Image
            Glide
                .with(itemView)
                .load(article.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(applicationBinding.articleImage)
        }


    }


}