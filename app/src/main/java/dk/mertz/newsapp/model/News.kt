package dk.mertz.newsapp.model

data class NewsList(val status : String? = null, val totalResults: Int? = null,val articles: List<Article>? = null) {}


data class Article(val source: Source, val author:String? = null, val title:String? = null,
                   val description:String? = null,  val url: String? = null,
                   val urlToImage: String? = null, val publishedAt:String? = null,
                   val content: String? = null)

data class Source(val id: Any? = null,val name:String? = null )