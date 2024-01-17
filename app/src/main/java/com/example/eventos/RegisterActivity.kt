package com.example.eventos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.eventos.api.RetrofitInstance
import com.example.eventos.model.UserRegistration // Esta é a classe de modelo de dados para registro
import com.example.eventos.model.UserRegistrationResponse

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        val btnNavegarParaSegunda: Button = findViewById(R.id.signin_log)
        btnNavegarParaSegunda.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val emailEditText: EditText = findViewById(R.id.reg_email)
        val passwordEditText: EditText = findViewById(R.id.reg_pass)
        val nomeEditText: EditText = findViewById(R.id.reg_nome)
        val apelidoEditText: EditText = findViewById(R.id.reg_apelido)
        val registerButton: Button = findViewById(R.id.reg_bbtn_reg)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val nome = nomeEditText.text.toString()
            val apelido = apelidoEditText.text.toString()

            if (email.isNotBlank() && password.isNotBlank() && nome.isNotBlank() && apelido.isNotBlank()) {
                registerUser(email, password, nome, apelido)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(email: String, password: String, nome: String, apelido: String) {
        val newUser = UserRegistration(email, password, nome, apelido)
        RetrofitInstance.service.registerUser(newUser).enqueue(object : Callback<UserRegistrationResponse> {
            override fun onResponse(call: Call<UserRegistrationResponse>, response: Response<UserRegistrationResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Registro bem-sucedido", Toast.LENGTH_SHORT).show()
                    // Redirecionar para a próxima atividade ou login
                } else {
                    Toast.makeText(this@RegisterActivity, "Falha no registro", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserRegistrationResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Erro na rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
