package com.example.eventos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HeadlinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.headlines)

        // Inicializando TextViews
        val textView19: TextView = findViewById(R.id.textView19)
        val textView20: TextView = findViewById(R.id.textView20)
        val textView21: TextView = findViewById(R.id.textView21)
        val textView22: TextView = findViewById(R.id.textView22)
        val textView24: TextView = findViewById(R.id.textView24)

        // Inicializando Buttons
        val button12: Button = findViewById(R.id.button12)
        val button13: Button = findViewById(R.id.button13)
        val button14: Button = findViewById(R.id.button14)
        val button15: Button = findViewById(R.id.button15)
        val button17: Button = findViewById(R.id.button17)
        val button2: Button = findViewById(R.id.button2)

        // Inicializando ImageViews
        val imageView15: ImageView = findViewById(R.id.imageView15)
        val imageView16: ImageView = findViewById(R.id.imageView16)

        // Inicializando a View de divisão
        val divider3: View = findViewById(R.id.divider3)

        // Definindo os listeners para botões
        button12.setOnClickListener {
            // Código para o que acontece quando button12 é clicado
        }

        button13.setOnClickListener {
            // Código para o que acontece quando button13 é clicado
        }

        button14.setOnClickListener {
            // Código para o que acontece quando button14 é clicado
        }

        button15.setOnClickListener {
            // Código para o que acontece quando button15 é clicado
        }

        button17.setOnClickListener {
            // Código para o que acontece quando button17 é clicado
        }

        button2.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish() // Opcional: se você quer finalizar a HeadlinesActivity
        }



    }
}
