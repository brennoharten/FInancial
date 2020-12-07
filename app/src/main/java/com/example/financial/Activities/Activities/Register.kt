package com.example.financial.Activities.Activities


import com.example.financial.Activities.Activities.Models.User
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.financial.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var registerEmail: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerSave: Button
    private lateinit var registerName: EditText
    private lateinit var registerCode: EditText

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        database = Firebase.database

        registerEmail = findViewById(R.id.register_edittext_email)
        registerPassword = findViewById(R.id.register_edittext_password)
        registerName = findViewById(R.id.register_edittext_Name)
        registerCode = findViewById(R.id.register_edittext_code)

        registerSave = findViewById(R.id.register_button_signon)
        registerSave.setOnClickListener {

            var formOK = true

            if(registerEmail.text.isEmpty()) {
                registerEmail.error = "Este campo não pode ser nulo"
                formOK = false
            }

            if(registerPassword.text.isEmpty()) {
                registerPassword.error = "Este campo não pode ser nulo"
                formOK = false
            }

            if(formOK) {

                val email = registerEmail.text.toString()
                val password = registerPassword.text.toString()
                val name = registerName.text.toString()
                val code = registerCode.text.toString()
                val score = 0

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val user = User(email, password, name, score , code)

                            val userRef = database.reference.child("user")
                            userRef.child(auth.currentUser?.uid!!).setValue(user)


                            Toast.makeText(
                                Register@this,
                                "Cadastro com sucesso!",
                                Toast.LENGTH_SHORT).show()
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("APP" , "createUserWithEmail:success")

                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("APP", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                Register@this,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }

                        // ...
                    }

            }
        }
    }
}