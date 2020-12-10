package com.example.financial.Activities.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.financial.R

class Ganhos : AppCompatActivity(), View.OnClickListener {
    private lateinit var nome: EditText
    private lateinit var enviarGanhos : Button
    private lateinit var valor: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganhos)

        nome = findViewById(R.id.ganhos_edittext_nome)
        valor = findViewById(R.id.ganhos_edittext_valor)

        enviarGanhos = findViewById(R.id.ganhos_button_enviar)
        enviarGanhos.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.ganhos_button_enviar -> {
                val it = Intent(this, MainActivity::class.java)
                it.putExtra("nome2", nome.text.toString())
                it.putExtra("valor2", valor.text.toString())

                startActivity(it)
            }

        }
    }
}