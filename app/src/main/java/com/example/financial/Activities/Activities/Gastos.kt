package com.example.financial.Activities.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.financial.R

class Gastos : AppCompatActivity(), View.OnClickListener {

    private lateinit var nome: EditText
    private lateinit var enviarGastos : Button
    private lateinit var valor: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gastos)

        nome = findViewById(R.id.gastos_edittext_nome)
        valor = findViewById(R.id.gastos_edittext_valor)

        enviarGastos = findViewById(R.id.gastos_button)
        enviarGastos.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.gastos_button -> {
                val it = Intent(this, MainActivity::class.java)
                it.putExtra("nome", nome.text.toString())
                it.putExtra("valor", valor.text.toString())

                startActivity(it)
            }

        }
    }
}