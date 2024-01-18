package com.example.eventos

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.eventos.R.layout.activity_detalhes_noticia

class DetalhesNoticiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detalhes_noticia)

        // Recuperar os dados do Intent
        val titulo = intent.getStringExtra("noticiaTitulo")
        val localizacao = intent.getStringExtra("noticiaLocalizacao")
        val texto = intent.getStringExtra("noticiaTexto")
        val imagemUrl = intent.getStringExtra("noticiaImagemUrl")

        // Encontrar as Views
        val imageViewNoticia: ImageView = findViewById(R.id.imageViewNoticia)
        val textViewTitulo: TextView = findViewById(R.id.det_tit)
        val textViewLocalizacao: TextView = findViewById(R.id.textViewLocalizacao)
        val textViewTexto: TextView = findViewById(R.id.textView10)

        // Configurar as Views com os dados
        textViewTitulo.text = titulo
        textViewLocalizacao.text = localizacao
        textViewTexto.text = texto

        // Carregar a imagem usando Glide ou outra biblioteca de carregamento de imagens
        Glide.with(this)
            .load(imagemUrl)
            .into(imageViewNoticia)
    }
}
