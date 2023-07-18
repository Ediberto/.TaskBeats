package com.example.taskbeats_ediberto.presentation

import com.example.taskbeats_ediberto.data.local.Task
import java.io.Serializable

// CRUD
enum class ActionType {
    //CRIAR TRÊS OBJETOS DO TIPO ActionType
    //ESSES OBJETOS SÃO AS AÇÕES QUE TEMOS NA CLASSE ActionType
    DELETE,
    UPDATE,
    CREATE
}
//A ACTIVITY POSSA SEGURAR A TAREFA EM SI E TAMBÉM O TIPO. VEJA ABAIXO:
data class TaskAction (
    val task : Task?,
    val actionType: String
) : Serializable