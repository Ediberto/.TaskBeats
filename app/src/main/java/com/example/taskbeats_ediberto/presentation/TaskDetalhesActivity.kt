package com.example.taskbeats_ediberto.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.taskbeats_ediberto.R
import com.example.taskbeats_ediberto.data.Task
import com.google.android.material.snackbar.Snackbar
//import kotlinx.coroutines.NonCancellable.message

class TaskDetalhesActivity : AppCompatActivity() {
    //private lateinit var tvTitulo : TextView

    //COLOCAR A TAREFA DENTRO DO ESCOPO DESTA ACTIVITY
    private var task : Task? = null
    private lateinit var btnConcluir: Button
    //CRIAR UM OBJETO companion COM UMA CHAVE PARA PASSAR
    //UM VALOR DE UMA TELA PARA OUTRA
    companion object {
        private const val TASK_DETALHE_EXTRA = "task.extra.detalhe"
        //A FUNÇÃO ABAIXO GARANTE QUE QUEM FOR ABRIR A "TaskDetalhesActivity.KT",
        //PASSE UMA TAREFA A SER MOSTRADA
        fun start(context: Context, task: Task?): Intent {
            val intent = Intent(context, TaskDetalhesActivity::class.java).apply {
                //QUANDO A PROXIMA TELA FOR CHAMADA, ENVIAR O ITEM DA TAREFA JUNTO
                putExtra(TASK_DETALHE_EXTRA, task)
            }
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detalhes)
        //EXIBIR OS TRES PONTOS DO MENU
        setSupportActionBar(findViewById(R.id.toolbar))
        //RECUPERAR A STRING DA TELA ANTERIOR
        //OU SEJA,
        //RECUPERAR A TAREFA QUE É UMA STRING QUE VEN DO INTENT
        //NÃO SABEMOS SE A tarefa EXISTE
        task = intent.getSerializableExtra(TASK_DETALHE_EXTRA) as Task?
       // OU task = requireNotNull(intent.getSerializableExtra(TASK_DETALHE_EXTRA) as Task?)
        //RECUPERAR O TEXTVIEW DO XML activity_task_detalhes.xml
        val edtTitulo = findViewById<EditText>(R.id.edt_task_titulo)
        val edtDescricao = findViewById<EditText>(R.id.edt_task_descricao)
        //RECUPERAR O ID DO BOTÃO
        btnConcluir = findViewById<Button>(R.id.btn_concluir)

        if(task != null) {
            edtTitulo.setText(task!!.titlo)
            edtDescricao.setText(task!!.descricao)
        }
         var tvTitlo = findViewById<TextView>(R.id.tv_task_titulo_detalhe)
        //SETANDO UM NOVO TEXTO NA TELA
        //tvTitlo.text = task?.titlo ?: "Adicione uma Tarefa"
        //EVENTO DO BOTÃO CONCLUIR
        btnConcluir.setOnClickListener {
            val titlo = edtTitulo.text.toString()
            val descricao = edtDescricao.text.toString()
            if (titlo.isNotEmpty() && descricao.isNotEmpty()) {
                //se tarefa for igual a vazio
                //CRIA UMA NOVA TAREFA
                if (task == null) {
                    //addOrUpdateTask(0, titlo, descricao, TaskListActivity.ActionType.CREATE)
                   addOrUpdateTask(0, titlo, descricao, ActionType.CREATE)
                } else {
                    //addOrUpdateTask(task!!.id, task!!.titlo, descricao, TaskListActivity.ActionType.UPDATE)
                    addOrUpdateTask(task!!.id, task!!.titlo, descricao, ActionType.UPDATE)
                }
            } else {
                showMessage(it, "Os Campos precisam ser preenchidos")
            }
        }
    }
    fun addOrUpdateTask(
        id: Int,
        titulo: String,
        descricao: String,
        actionType: ActionType
    ) {
        //val newTask = Task(id, titulo, descricao)
        val task = Task(id, titulo, descricao)
        returnAction(task, actionType)
    }
    //CICLO DE VIDA DA ACTIVITY
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //INFLAR O BOTÃO PARA CONSEGUIRMOS UTILIZÁ-LO
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_task_detalhe, menu)
        return true
    }
    //VERIFICAR QUAL A OPÇÃO QUE FOI CLICADA DO MENU, PELO ID
    // CONSEGUE-SE ISSO, ENTÃO VAMOS CRIAR A FUNÇÃO ABAIXO
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_task -> {
                //SETANDO O RESULTADO NA TELA ANTERIOR
                if (task != null) {
                   returnAction(task!!, ActionType.DELETE)
                } else {
                   //showMessage(tvTitulo, "Item não existe")
                    showMessage(btnConcluir, "Item não existe")
                }
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
    //fun returnAction(task: Task, actionType: TaskListActivity.ActionType) {
    private fun returnAction(task: Task, actionType: ActionType) {
        val intent = Intent()
            .apply {
                val taskAction = TaskAction(task, actionType.name)
                //val taskAction = TaskAction(task!!, actionType.name)
                putExtra("TASK_ACTION_RESULT", taskAction)
            }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
  }
  private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
  }
