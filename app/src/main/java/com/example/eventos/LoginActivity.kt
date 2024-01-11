package com.example.eventos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Inicializa o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Botão de login
        val loginButton = findViewById<Button>(R.id.button2)
        loginButton.setOnClickListener {
            performLogin()
        }

    }
    private fun performLogin() {
        val email = findViewById<EditText>(R.id.Log_email).text.toString()
        val password = findViewById<EditText>(R.id.Log_password).text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            // Autenticação com Firebase usando e-mail e senha
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login bem-sucedido
                        goToMainActivity()
                    } else {
                        // Se falhar, exiba uma mensagem ao usuário
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToMainActivity() {
        // Navega para a MainActivity ou outra atividade após o login
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}