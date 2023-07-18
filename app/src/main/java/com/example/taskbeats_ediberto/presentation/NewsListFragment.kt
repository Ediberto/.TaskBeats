package com.example.taskbeats_ediberto.presentation

//import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.taskbeats_ediberto.R
import com.example.taskbeats_ediberto.data.local.News


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

    //CRIAR O VIEWMODEL, SÓ VAI SER CRIADO QDO ELE FOR OBSERVADO, POR ISSO O BY LAZY
    private val viewModel by lazy {
        NewsListViewModel.create()
    }
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
        //USAR A LISTA QUE VEIO DO BACKEND
        viewModel.newsListLiveData.observe(viewLifecycleOwner) { newsListDto ->
            //PASSAR TODOS OS ITENS DA LISTA QUE VEM DO BACKEND
            val newsList = newsListDto.map { newsDto ->
                News(
                    titlo = newsDto.title,
                    imgUrl = newsDto.imageUrl
                )
            }
            //TODA VEZ QUE CHEGAR UM NOVO ITEM DO BACKEND, SERÁ SUBMETIDO UMA NOVA LISTA
            adapter.submitList(newsList)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = NewsListFragment()
    }
}