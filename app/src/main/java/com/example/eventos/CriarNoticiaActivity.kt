package com.example.eventos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CriarNoticiaActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criarnoticia)

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
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Código para nenhuma seleção, se necessário
            }
        }
    }
}




