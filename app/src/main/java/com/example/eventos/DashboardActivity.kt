package com.example.eventos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.Button
import com.example.eventos.R.id.conadunto


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

        // Configuração da Toolbar
        val toolbar: Toolbar = findViewById(conadunto) // Use R.id.conadunto
        setSupportActionBar(toolbar)
    }

    // Sobrescrever onCreateOptionsMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dash, menu)
        return true
    }

    // Sobrescrever onOptionsItemSelected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dashboard -> {
                startActivity(Intent(this, DashboardActivity::class.java))
                true
            }
            R.id.highlights -> {
                startActivity(Intent(this, HeadlinesActivity::class.java))
                true
            }
            R.id.feedback -> {
                startActivity(Intent(this, FeedbackActivity::class.java))
                true
            }
            R.id.logout -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
