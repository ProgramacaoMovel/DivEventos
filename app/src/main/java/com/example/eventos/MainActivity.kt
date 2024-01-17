package com.example.eventos
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNavegarParaSegunda: Button = findViewById(R.id.btnNavegarParaSegunda)
        val btnNavReg: Button = findViewById(R.id.btnreg)

        btnNavegarParaSegunda.setOnClickListener {
            Log.d("MainActivity", "Navegando para LoginActivity")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnNavReg.setOnClickListener {
            Log.d("MainActivity", "Navegando para RegisterActivity")
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

}