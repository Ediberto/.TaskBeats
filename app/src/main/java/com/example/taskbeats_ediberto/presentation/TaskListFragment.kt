package com.example.taskbeats_ediberto.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.taskbeats_ediberto.R
import com.example.taskbeats_ediberto.data.Task

/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {
    //RECUPERAR A IMAGEM "img_empty_task"
    private lateinit var ctnContent : LinearLayout
    //ADAPTER
    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter(::openTaskListDetalhes)
    }
    //CRIA ViewModel VAZIO
    private val viewModel: TaskListViewModel by lazy {
        TaskListViewModel.create(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // INICIALIZAR A IMAGEM ctnContent
        ctnContent = view.findViewById(R.id.ctn_content)
        //RECYCLERVIEW
        val rvTasks : RecyclerView = view.findViewById(R.id.rv_task_list)
        rvTasks.adapter = adapter
    }
    override fun onStart() {
        super.onStart()
        listFromDataBase()
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
        viewModel.taskListLiveData.observe(this, listObserver)
    }
    private fun openTaskListDetalhes(task: Task) {
        val intent = TaskDetalhesActivity.start(requireContext(), task)
        requireActivity().startActivity(intent)
    }
    companion object {
        @JvmStatic
        fun newInstance() = TaskListFragment()
    }
}