package com.example.financial.Activities.Activities



import com.example.financial.Activities.Activities.Models.User
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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

    private lateinit var entrega: Button
    private lateinit var gps: Button
    private lateinit var duvidas: Button

    private lateinit var auth:FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private var  user: User? = null
    private var rank: User? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        entrega = findViewById(R.id.home_button_entrega)
//        entrega.setOnClickListener(this)
//
//        gps = findViewById(R.id.home_button_gps)
//        gps.setOnClickListener(this)
//
//        duvidas = findViewById(R.id.home_button_duvidas)
//        duvidas.setOnClickListener(this)

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
                rank = snapshot.getValue(User::class.java)

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
    }

    override fun onClick(v: View?) {
        when(v?.id) {
//            R.id.home_button_entrega -> {
//                val it = Intent(this, EntregaActivity::class.java)
//                startActivity(it)
//            }
//            R.id.home_button_duvidas -> {
//                val it = Intent(this, DuvidasActivity::class.java)
//                startActivity(it)
//            }
//            R.id.home_button_gps -> {
//                val url = "https://www.google.com.br/maps/search/eco+pontos+/@-3.7511894,-38.5755529,13z/data=!3m1!4b1"
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                startActivity(intent)
//            }
        }
    }
}