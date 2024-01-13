package com.example.eventos

import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criarnoticia)

        val tituloEditText: EditText = findViewById(R.id.create_titulo_noticia)
        val localizacaoEditText: EditText = findViewById(R.id.create_loc_noticia)
        val textoEditText: EditText = findViewById(R.id.create_corpo_noticia)
        val criarNoticiaButton: Button = findViewById(R.id.criarNoticiaButton)
        val spinner: Spinner = findViewById(R.id.categorySpinner)

        // Configuração do spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.categories_array, // Certifique-se de ter esse array no seu arquivo strings.xml
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCategory = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@CriarNoticiaActivity, "Selecionado: $selectedCategory", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Código para nenhuma seleção, se necessário
            }
        }

        // Configuração do botão de criar notícia
        criarNoticiaButton.setOnClickListener {
            val titulo = tituloEditText.text.toString().trim()
            val localizacao = localizacaoEditText.text.toString().trim()
            val texto = textoEditText.text.toString().trim()
            val categoria = spinner.selectedItem.toString()

            if (titulo.isNotEmpty() && localizacao.isNotEmpty() && texto.isNotEmpty()) {
                val noticia = dbModel.Noticias().apply {
                    notTitulo = titulo
                    notLocalizacao = localizacao
                    notTexto = texto
                    var notCategoria = categoria // Adicionando a categoria selecionada
                }
                enviarNoticiaParaFirebase(noticia)
            } else {
                Toast.makeText(this@CriarNoticiaActivity, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun enviarNoticiaParaFirebase(noticia: dbModel.Noticias) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("noticias")
        val noticiaId = databaseReference.push().key // Cria um ID único
        noticia.notId = noticiaId

        noticiaId?.let {
            databaseReference.child(it).setValue(noticia)
                .addOnSuccessListener {
                    Toast.makeText(this@CriarNoticiaActivity, "Notícia criada com sucesso!", Toast.LENGTH_SHORT).show()
                    finish() // Fecha a atividade após o sucesso
                }
                .addOnFailureListener {
                    Toast.makeText(this@CriarNoticiaActivity, "Erro ao criar notícia.", Toast.LENGTH_SHORT).show()
                }
        }
    }
}

