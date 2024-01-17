package com.example.eventos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import android.widget.Button
import com.example.eventos.R.id.nav_view
import com.example.eventos.R.layout.menufragment

class DashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Configuração do botão para criar notícias
        val btnCriarnoti: Button = this.findViewById(R.id.btnCriarnoti)
        btnCriarnoti.setOnClickListener {
            val intent = Intent(this, CriarNoticiaActivity::class.java)
            startActivity(intent)
        }

    }
}