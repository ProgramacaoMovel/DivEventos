package com.example.eventos
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNavegarParaSegunda: Button = findViewById(R.id.btnNavegarParaSegunda)
        val btnNavReg : Button = findViewById((R.id.btnreg))

        btnNavegarParaSegunda.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnNavReg.setOnClickListener{
            val intent = Intent(this, RegisterActivity :: class.java)
            startActivity(intent)
        }
    }
}