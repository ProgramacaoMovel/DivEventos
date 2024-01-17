package com.example.eventos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eventos.api.RetrofitInstance
import com.example.eventos.model.LoginRequest
import com.example.eventos.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val loginButton: Button = findViewById(R.id.btn_login_btn)
        loginButton.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val emailEditText = findViewById<EditText>(R.id.Log_email)
        val passwordEditText = findViewById<EditText>(R.id.Log_password)

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            val loginRequest = LoginRequest(email, password)
            RetrofitInstance.service.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        goToMainActivity()
                    } else {
                        Toast.makeText(baseContext, "Authentication failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(baseContext, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}
