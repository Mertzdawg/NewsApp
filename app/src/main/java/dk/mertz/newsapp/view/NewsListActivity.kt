package dk.mertz.newsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dk.mertz.newsapp.R

class NewsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newslist)
    }
}
