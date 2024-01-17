package com.example.eventos

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.filament.View

class NewsActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.headlines)

        val constraintLayout: ConstraintLayout = findViewById(R.id.constraintLayout)
        val textView19: TextView = findViewById(R.id.textView19)
        val imageView14: ImageView = findViewById(R.id.imageView14)
        val divider3: android.view.View? = findViewById(R.id.divider3)
        val button12: Button = findViewById(R.id.button12)
        val button13: Button = findViewById(R.id.button13)
        val button14: Button = findViewById(R.id.button14)
        val button15: Button = findViewById(R.id.button15)
        val imageView15: ImageView = findViewById(R.id.imageView15)
        val textView20: TextView = findViewById(R.id.textView20)
        val textView21: TextView = findViewById(R.id.textView21)
        val imageView16: ImageView = findViewById(R.id.imageView16)
        val textView22: TextView = findViewById(R.id.textView22)
        val textView24: TextView = findViewById(R.id.textView24)
        val button10: Button = findViewById(R.id.button10)
        val button: Button = findViewById(R.id.button)

        // Aqui você pode definir os ouvintes de eventos e a lógica para cada componente, por exemplo:
        button12.setOnClickListener {
            // Faça algo quando o botão "Última Hora" for pressionado
        }



        // Adicione mais lógica conforme necessário
    }
}
