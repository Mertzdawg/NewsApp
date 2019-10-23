package dk.mertz.newsapp.view


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.mertz.newsapp.R
import dk.mertz.newsapp.view_model.NewsListVM
import work.beltran.conductorviewmodel.ViewModelController

class NewsListController : ViewModelController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_newslist,container,false)

        val vm =  viewModelProvider().get(NewsListVM::class.java);



        return view


    }
}
