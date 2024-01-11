package com.example.eventos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        val btnNavegarParaSegunda: Button = findViewById(R.id.signin_log)
        auth = FirebaseAuth.getInstance()

        btnNavegarParaSegunda.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        auth = FirebaseAuth.getInstance()

        val emailEditText
        : EditText = this.findViewById(R.id.reg_email)
        val passwordEditText : EditText = this.findViewById(R.id.reg_pass)
        val nomeEditText : EditText = this.findViewById(R.id.reg_nome)
        val apelidoEditText : EditText = this.findViewById(R.id.reg_apelido)
        val registerButton : Button = this.findViewById(R.id.reg_bbtn_reg)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val nome = nomeEditText.text.toString()
            val apelido = apelidoEditText.text.toString()

            if (email.isNotBlank() && password.isNotBlank() && nome.isNotBlank() && apelido.isNotBlank()) {
                // Criar conta no Firebase Authentication
                auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Registro bem-sucedido
                            val user = auth.currentUser

                            // Atualizar o perfil do usuário com o nome
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(nome.toString())
                                .build()

                            user?.updateProfile(profileUpdates)

                            Toast.makeText(
                                this@RegisterActivity,
                                "Registro bem-sucedido",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Você pode redirecionar para a próxima atividade aqui, se necessário
                        } else {
                            // Falha no registro
                            Toast.makeText(
                                this@RegisterActivity,
                                "Falha no registro. ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this@RegisterActivity,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}

