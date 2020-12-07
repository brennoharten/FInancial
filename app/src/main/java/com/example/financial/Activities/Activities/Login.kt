package com.example.financial.Activities.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.financial.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity(), View.OnClickListener {

    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginRegister: TextView
    private lateinit var loginSingin: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        loginEmail = findViewById(R.id.login_edittext_email)
        loginPassword = findViewById(R.id.login_edittext_password)

        loginRegister = findViewById(R.id.login_textview_register)
        loginRegister.setOnClickListener(this)

        loginSingin = findViewById(R.id.login_button_signin)
        loginSingin.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.login_textview_register -> {
                val it = Intent(this, Register::class.java)
                startActivity(it)
            }

            R.id.login_button_signin -> {
                var formOK = true

                if(loginEmail.text.isEmpty()) {
                    loginEmail.error = "Este campo não pode ser nulo"
                    formOK = false
                }

                if(loginPassword.text.isEmpty()) {
                    loginPassword.error = "Este campo não pode ser nulo"
                    formOK = false
                }

                if(formOK) {
                    val email = loginEmail.text.toString()
                    val password = loginPassword.text.toString()

                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("APP", "signInWithEmail:success")
                                    val user = auth.currentUser
                                    val it = Intent(this, MainActivity::class.java)
                                    startActivity(it)
                                    finish()
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("APP", "signInWithEmail:failure", task.exception)
                                    Toast.makeText(Login@this,
                                            "Usuario ou senha invalida.",
                                            Toast.LENGTH_SHORT).show()
                                }
                            }
                }
            }
        }
    }
}