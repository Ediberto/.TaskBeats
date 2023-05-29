package com.example.taskbeats_ediberto.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskbeats_ediberto.R
import com.example.taskbeats_ediberto.data.Task
//import com.comunidadedevspace.taskbeats.R
//import com.comunidadedevspace.taskbeats.data.Task

//IMPLEMENTAR O ADAPTER NA CLASSE &quot;TaskListAdapter&quot;
// -&gt; Unit, no Kotlin significa que não terá retorno
class TaskListAdapter(
    private val openTaskDetalheView: (task: Task) -> Unit ) :
    //RecyclerView.Adapter<TaskListViewHolder>() {
    ListAdapter<Task, TaskListViewHolder>(TaskListAdapter) {

    //TEMOS QUE FAZER A IMPLEMENTAÇÃO, OU SEJA, ESTA LISTA NÃO EXISTE MAIS,
// MAS VAI EXISTIR EM ALGUM MOMENTO
    private var listTask: List<Task> = emptyList()

    //OBS. DA MESMA MANEIRA QUE PASSAMOS A listTask COM UMA TAREFA para a função
    // DA MESMA MANEIRA POSSAMOS PASSAR &quot;openTaskDetalheView&quot;
    // INFLA O LAYOUT
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        //CRIAR UM VIEWHOLDER
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        //RETORNAR UM VIEWHOLDER PARA CRIAR UM NOVO VIEWHOLDER
        return TaskListViewHolder(view)
    }
    //ATRELAR O ITEM
    //DEVEMOS RECUPERAR O ÍTEM DA POSIÇÃO DA LISTA, ALTERANDO
    //O MEMBRO.
    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task = getItem(position)
        //PASSAR O item e chamar a função bind() descrita abaixo passando
        // O click
        holder.bind(task, openTaskDetalheView) //CRIAR A FUNÇÃO bind() abaixo
    }
    companion object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.titlo == newItem.titlo &&
                    oldItem.descricao == newItem.descricao
        }
    }
}
/* DENTRO DESTA CLASSE &quot;TaskListAdapter.kt&quot; CRIAR UMA NOVA CLASSE (Considerada VIEWHOLDER)
conforme abaixo.
OBS. Esse ViewHolder, vai pedir uma View como PARÂMETRO E VAI ESTENDER UM
RecyclerView.ViewHolder,
PASSANDO COMO PARÂMETRO A view
*/
class TaskListViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {
    private val tvTaskTitlo = view.findViewById<TextView>(R.id.tv_task_titlo)
    private val tvTaskDescricao = view.findViewById<TextView>(R.id.tv_task_descricao)

    fun bind(task: Task, openTaskDetalheView: (task: Task) -> Unit) {
        tvTaskTitlo.text = task.titlo
        tvTaskDescricao.text = "${task.id} - ${task.descricao}"
        view.setOnClickListener {
            openTaskDetalheView.invoke(task)
        }
    }
}
/*
    //CRIAR OUTRA FUNÇÃO
    fun submit(list: List<Task>) {
        listTask = list
        notifyDataSetChanged()
    }

    //TAMANHO DA MINHA LISTA
    override fun getItemCount(): Int {
        return listTask.size
    }




    }

}
*/
