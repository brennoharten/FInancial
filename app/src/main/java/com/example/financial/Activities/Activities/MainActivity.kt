package com.example.financial.Activities.Activities

import android.content.Intent
import com.example.financial.Activities.Activities.Models.User
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.financial.Activities.Activities.Models.Valor
import com.example.financial.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var enviar_gastos: Button
    private lateinit var enviar_ganhos: Button


    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private var user: User? = null

    private var textGastos = ""
    private var textoGanhos = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enviar_gastos = findViewById(R.id.home_button_gastos)
        enviar_gastos.setOnClickListener(this)

        enviar_ganhos = findViewById(R.id.home_button_ganhos)
        enviar_ganhos.setOnClickListener(this)


        auth = Firebase.auth
        database = Firebase.database

        Log.i("App", "UID: ${auth.currentUser?.uid!!}")

        val userRef = database.reference.child("user")
        userRef.orderByKey().addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                if (snapshot.key == auth.currentUser?.uid!!) {
                    user = snapshot.getValue(User::class.java)
                    Log.i("App", "user: ${user}")
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        if (intent.getStringExtra("nome") != "") {

            val nome = intent.getStringExtra("nome")
            val valor = intent.getStringExtra("valor")?.toDouble()
            val registro = Valor(nome, valor)

            user?.gastos?.add(registro)
            findViewById<TextView>(R.id.home_text_gastos).text = "${nome}: ${valor}"
        }

        if (intent.getStringExtra("nome2") != "") {

            val nome = intent.getStringExtra("nome2")
            val valor = intent.getStringExtra("valor2")?.toDouble()
            val registro = Valor(nome, valor)

            user?.ganhos?.add(registro)
            findViewById<TextView>(R.id.home_text_ganhos).text = "${nome}: ${valor}"
        }

//        refazer(user!!)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.home_button_gastos -> {
                val it = Intent(this, Gastos::class.java)
                startActivity(it)
            }
            R.id.home_button_ganhos -> {
                val it = Intent(this, Ganhos::class.java)
                startActivity(it)
            }
        }
    }
}
//    fun refazer(user: User) {
//
//        if (user != null) {
//            for(i in user?.ganhos!!) {
//                this.textoGanhos += user?.ganhos[i].nome +"\n"
//            }
//            for(gasto in user?.gastos[].valor.toString()) {
//                this.textGastos += gasto +"\n"
//            }
//
////            for (ganho in user?.ganhos!!) {
////                this.textoGanhos += ganho + "\n"
////            }
//        }
//    }
//}




