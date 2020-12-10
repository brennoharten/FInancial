package com.example.financial.Activities.Activities.Models

import android.widget.ListView
import com.example.financial.Activities.Activities.Ganhos
import com.example.financial.Activities.Activities.Gastos

data class User(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val usuario: String = "",
    val numero: String = "",
    val gastos: MutableList<Valor>,
    val ganhos: MutableList<Valor>
)