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
        //val categoriaSpinner =
        val criarNoticiaButton: Button = findViewById(R.id.criarNoticiaButton)

<<<<<<< HEAD
        // Encontrar o spinner no layout
        val spinner: Spinner = findViewById(R.id.categorySpinner)

        // Criar um ArrayAdapter usando o array de strings e um layout padrão para o spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.categories_array, // Certifique-se de ter esse array no seu arquivo strings.xml
            android.R.layout.simple_spinner_item
        )

        // Especificar o layout a ser usado quando a lista de opções aparecer
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Aplicar o adapter ao spinner
        spinner.adapter = adapter

        // Definir o comportamento quando um item é selecionado
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCategory = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@CriarNoticiaActivity, "Selecionado: $selectedCategory", Toast.LENGTH_SHORT).show()
=======

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
>>>>>>> 9ff7a211579cd0312ced7f155e393e10534daad0
            }
        }
    }

<<<<<<< HEAD
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Código para nenhuma seleção, se necessário
            }
=======
    private fun enviarNoticiaParaFirebase(noticia: dbModel.Noticias) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("noticias")
        val noticiaId = databaseReference.push().key // Cria um ID único
        noticia.notId = noticiaId

        noticiaId?.let {
            databaseReference.child(it).setValue(noticia)
                .addOnSuccessListener {
                    Toast.makeText(this, "Notícia criada com sucesso!", Toast.LENGTH_SHORT).show()
                    finish() // Fecha a atividade após o sucesso
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Erro ao criar notícia.", Toast.LENGTH_SHORT).show()
                }
>>>>>>> 9ff7a211579cd0312ced7f155e393e10534daad0
        }
    }
}




