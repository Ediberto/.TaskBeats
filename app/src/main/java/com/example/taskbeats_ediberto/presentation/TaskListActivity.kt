package com.example.taskbeats_ediberto.presentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
//import android.view.View
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
//import com.comunidadedevspace.taskbeats.R
//import com.comunidadedevspace.taskbeats.data.AppDatabase
//import com.comunidadedevspace.taskbeats.data.Task
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.example.taskbeats_ediberto.R
import com.example.taskbeats_ediberto.TaskBeatsApplication
//import com.google.android.filament.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.Serializable
import com.example.taskbeats_ediberto.data.AppDatabase
import com.example.taskbeats_ediberto.data.Task
import kotlinx.coroutines.Dispatchers

class TaskListActivity : AppCompatActivity() {
    //RECUPERAR A IMAGEM "img_empty_task"
    private lateinit var ctnContent : LinearLayout
    //INICIALIZAR ESTA IMAGEM "imgContent" LÁ EM BAIXO NO ONCREATE
    //ALTERAR A LINHA DO ADAPTER E PASSAR A FUNÇÃO "openTaskDetalheView" PARA ESSE ADAPTER
   // private val adapter : TaskListAdapter = TaskListAdapter(:: openTaskDetalheView )
    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter(::onListItemClicked)
    }
    //CRIA ViewModel VAZIO
    private val viewModel: TaskListViewModel by lazy {
        TaskListViewModel.create(application)
    }
    //adapter
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            //PEGANDO RESULTADO
            val data = result.data
            val taskAction = data?.getSerializableExtra(TASK_ACTION_RESULT) as TaskAction
          //  val task: Task = taskAction.task
            viewModel.execute(taskAction)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //INFLAR O LAYOUT
        setContentView(R.layout.activity_tasks_list)
        //COLOCAR O TOOLBAR
        setSupportActionBar(findViewById(R.id.toolbar))
       // listFromDataBase()
        // INICIALIZAR A IMAGEM ctnContent
        ctnContent = findViewById(R.id.ctn_content)
        //RECYCLERVIEW
        val rvTasks : RecyclerView = findViewById(R.id.rv_task_list)
        rvTasks.adapter = adapter

        //ESTÁ SENDO PASSADO DOIS ARGUMENTOS, PARA RESOLVER ESSE PROBLEMA VAMOS ALTERAR OS
        //ARGUMENTOS NA CLASSE "TaskListAdapter".
        //RECUPERANDO A RECYCLERVIEW
        //COLOCAR AÇÃO NO FLOAT ACTION BUTTON
        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener {
            openTaskListDetalhes(null)
        }
    }
    override fun onStart() {
        super.onStart()
        //MOSTRA UMA INSTANCIA DA DATABASE
        //Log.d("Ediberto testando", dataBase.toString())
        listFromDataBase()
     }
    private fun deleteAll() {
        val taskAction = TaskAction(null, ActionType.DELETE_ALL.name)
        viewModel.execute(taskAction)
    }
     //ATUALIZAR A LISTA, ATRAVÉS DO SUBMITLIST
    private fun listFromDataBase(){
        //Observer
        val listObserver = Observer<List<Task>> {listTasks ->
            if(listTasks.isEmpty()) {
                ctnContent.visibility = View.VISIBLE
            } else {
                ctnContent.visibility = View.GONE
            }
            adapter.submitList(listTasks)
        }
        //Live Data
        viewModel.taskListLiveData.observe(this@TaskListActivity, listObserver)
    }
    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }
    private fun onListItemClicked(task : Task) {
        openTaskListDetalhes(task)
    }
    private fun openTaskListDetalhes(task: Task?) {
        val intent = TaskDetalhesActivity.start(this, task)
        startForResult.launch(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_task_list, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_task -> {
                //DELETA TODAS AS TAREFAS
                deleteAll()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}
//Serializable  //QDO FOR CLICADO EM UMA DAS OPÇÕES, AGORA NESTA CASO A OPÇÃO DELETE, SERÁ PASSADO PARA OUTRA TELA
// CRUD
enum class ActionType {
    //CRIAR TRÊS OBJETOS DO TIPO ActionType
    //ESSES OBJETOS SÃO AS AÇÕES QUE TEMOS NA CLASSE ActionType
    DELETE,
    DELETE_ALL,
    UPDATE,
    CREATE
}
//A ACTIVITY POSSA SEGURAR A TAREFA EM SI E TAMBÉM O TIPO. VEJA ABAIXO:
data class TaskAction (
    val task : Task?,
    val actionType: String
) : Serializable
//A MainActivity, VAI SER
//RESPONSÁVEL PARA FAZER A OPERAÇÃO, ISTO É, EXECUTAR A AÇÃO.
const val TASK_ACTION_RESULT = "TASK_ACTION_RESULT"
//A TELA DA FRENTE "TaskDetalhesActivity.kt", SOMENTE VAI DEFINIR O QUE
//DEVE SER FEITO.