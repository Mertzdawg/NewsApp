package dk.mertz.newsapp.view_model


import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dk.mertz.newsapp.model.Article
import dk.mertz.newsapp.model.NewsList
import dk.mertz.newsapp.network.BASE_URL
import dk.mertz.newsapp.network.NewsApiService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class NewsListVM : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val articles = MutableLiveData<List<Article>>()
    private var loading = true

    init {
        loadNews("Android")
    }

    private fun loadNews(query : String) {
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(NewsApiService::class.java)
        //collect RxJava disposables
        compositeDisposable.add(requestInterface.getNews(query)
        //send observable notification to UI thread
            .observeOn(AndroidSchedulers.mainThread())
        //subscribe to observer away from UI thread
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(newsList : NewsList) {
        Log.d("JSON",newsList.toString())
        articles.postValue(newsList.articles)
        loading = false
    }

    fun getArticles() = articles

    fun refreshQuery(query: String) {
        loadNews(query)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}