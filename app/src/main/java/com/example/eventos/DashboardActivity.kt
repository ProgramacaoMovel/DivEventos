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
        val toolbar: Toolbar = findViewById(conadunto)
        setSupportActionBar(toolbar)

        // Em suas atividades
        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_dash, menu)
            return true
        }
        fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.dashboard -> {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.highlights -> {
                    val intent = Intent(this, HeadlinesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.seguir -> {
                    val intent = Intent(this, FeedbackActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.feedback -> {
                    val intent = Intent(this, FeedbackActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }


    }
}