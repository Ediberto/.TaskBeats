package com.example.taskbeats_ediberto.presentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.taskbeats_ediberto.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //RECUPERAR OS CONTAINERS
        //RECUPERAR O BottomAppBar
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        //COLOCAR O ACTIONBUTTON
        val floatActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        //CHAMAR A FUNÇÃO openTaskListDetalhes
        floatActionButton.setOnClickListener {
            openTaskListDetalhes()
        }
        //INSTANCIAR OS DOIS FRAGMENTS
        val taskListFragment = TaskListFragment.newInstance()
        val newsListFragment = NewsListFragment.newInstance()
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, taskListFragment)
            setReorderingAllowed(true)
        }
        bottomNavView.setOnItemSelectedListener {  menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.task_list -> taskListFragment
                R.id.news_list -> newsListFragment
                else -> taskListFragment
            }
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, fragment)
                setReorderingAllowed(true)
            }
            true
        }
    }
    //ABRINDO UMA NOVA ACTIVITY PARA CRIAR UMA NOVA TAREFA
    private fun openTaskListDetalhes() {
        val intent = TaskDetalhesActivity.start(this, null)
        startActivity(intent)
    }
}