package com.example.taskbeats_ediberto.presentation

//import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.taskbeats_ediberto.R
import com.example.taskbeats_ediberto.data.News
import java.net.HttpURLConnection
import java.net.URL


/**
 * A simple [Fragment] subclass.
 * Use the [NewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsListFragment : Fragment() {
    //DAQUI
  /*  private val urlList = listOf(
        "https://www.example.com",
        "https://www.google.com",
        "https://www.openai.com"
    )*/
   /* private val urlList1 = "https://www.google.com"
    val url = URL(urlList1)
    val connection = url.openConnection() as HttpURLConnection */
   // Intent intent = new Intent(Intent.ACTION_VIEW,
   // Uri.parse(link.getText().toString()));
   // startActivity(intent);
    //connection.requestMethod = "GET"
    // Faz a conexão e obtém a resposta
    //val responseCode = connection.responseCode
   // println("URL: $urlList1 | Response Code: $responseCode")
  /*  Button ok = (Button) findViewById(R.id.btnLink);
    ok.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(link.getText().toString()));
            startActivity(intent);
        }
    }); */
    //AQUI
    //PARA RECUPERAR O RECYCLERVIEW NO FRAGMENT_NEWS_LIST.XML
    private val adapter: NewsListAdapter by lazy {
        NewsListAdapter()
    }
    //OU ASSIM
    //private val adapter = NewsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }
    //RECUPERANDO O RECYCLERVIEW NO FRAGMENT_NEWS_LIST.XML
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvNewsList = view.findViewById<RecyclerView>(com.example.taskbeats_ediberto.R.id.rv_news)
        //CHAMAR O ADAPTER
        rvNewsList.adapter = adapter
        //CRIAR UMA LISTA
      /*   val titlo1: String
        titlo1 = "https://google.com"
        val url = URL(titlo1)
        val connection = url.openConnection() as HttpURLConnection
        //val link = findViewById(R.id.editLink) as TextView
        val link = titlo1 as TextView
       // val ok: Button = findViewById(R.id.btnLink) as Button
       // ok.setOnClickListener(View.OnClickListener {
        link.setOnClickListener(View.OnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
               // Uri.parse(link.text.toString())
            )
            startActivity(intent)
        }) */
        val newsList = listOf<News>(
            News(
                titlo = "4.312 Imagens gratuitas de Notícias",
                imgUrl = "https://cdn.pixabay.com/photo/2016/02/01/00/56/news-1172463_1280.jpg"

            ),
            News(
                titlo = "Fotos de Noticias jornal, Imagens de Noticias jornal sem royalties",
                imgUrl = "https://static3.depositphotos.com/1004325/185/i/600/depositphotos_1853470-stock-photo-news.jpg"
            ),
            News(
                titlo = "Blog CNA - Os melhores sites de notícias em inglês para estudar",
                imgUrl = "https://cna.com.br/Content/uploads/blogposts/os-melhores-sites-de-noticias-em-ingles-para-estudar.jpg"
            ),
            News(
                titlo = "Google Notícias – Apps no Google Play",
                imgUrl = "https://play-lh.googleusercontent.com/b3MqZswO8F7j3lcdH01kxzaeHa7vUndy7ma_JwdM_j_Vpj8LKZcKt0HmpORQ7CKF2A"
            ),
            News(
               titlo = "Jornal Notícias do Brasil – Alexandre Zakir comenta sobre a insegurança no Metrô de SP e as políticas de segurança – SINDPESP",
                imgUrl = "https://sindpesp.org.br/wp-content/uploads/2022/09/noticias-do-brasil-alexandre-zakir-inseguranca-metro.jpg"
            ),
            News(
               titlo = "Últimas Notícias: Seja o Primeiro a Saber - Notícias ao Minuto Brasil\n",
               imgUrl = "https://cdn.noticiasaominuto.com.br/img/fbshare.png"
            )
        )
        adapter.submitList(newsList)
    }
    //DAQUI
    //private val urlList1 = "https://www.google.com"
 //   private val titlo = "https://www.google.com"
 //   val url = URL(titlo)
 //   val connection = url.openConnection() as HttpURLConnection
    //AQUI
    companion object {
        @JvmStatic
        fun newInstance() = NewsListFragment()
    }
}