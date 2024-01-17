package com.example.eventos

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase





class CriarNoticiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criarnoticia)

        val tituloEditText: EditText = findViewById(R.id.create_titulo_noticia)
        val localizacaoEditText: EditText = findViewById(R.id.create_loc_noticia)
        val textoEditText: EditText = findViewById(R.id.create_corpo_noticia)
        //val categoriaSpinner =
        val criarNoticiaButton: Button = findViewById(R.id.criarNoticiaButton)


        criarNoticiaButton.setOnClickListener {
            val titulo = tituloEditText.text.toString().trim()
            val localizacao = localizacaoEditText.text.toString().trim()
            val texto = textoEditText.text.toString().trim()

            if (titulo.isNotEmpty() && localizacao.isNotEmpty() && texto.isNotEmpty()) {
                val noticia = dbModel.Noticias().apply {
                    notTitulo = titulo
                    notLocalizacao = localizacao
                    notTexto = texto
                }
                enviarNoticiaParaFirebase(noticia)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun enviarNoticiaParaFirebase(noticia: dbModel.Noticias) {
        val databaseReference = FirebaseDatabase.getInstance().getReference().child("Noticia")
        val noticiaId = databaseReference.push().key // Gera um ID único

        if (noticiaId != null) {
            noticia.notId = noticiaId
            databaseReference.child(noticiaId).setValue(noticia)
                .addOnSuccessListener {
                    Toast.makeText(this, "Notícia criada com sucesso!", Toast.LENGTH_SHORT).show()
                    // Adicione aqui qualquer ação adicional após o sucesso
                    // Por exemplo, redirecionar para a lista de notícias.
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Erro ao criar notícia.", Toast.LENGTH_SHORT).show()
                }
        } else {
            // Tratar caso onde noticiaId é nula
            Toast.makeText(this, "Erro ao gerar ID da notícia.", Toast.LENGTH_SHORT).show()
        }
    }
}