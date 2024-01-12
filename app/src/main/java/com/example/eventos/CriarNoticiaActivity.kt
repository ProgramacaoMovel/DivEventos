package com.example.eventos

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
class CriarNoticiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criarnoticia)

        val spinner: Spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Aqui você pode incluir o listener para o spinner se necessário
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Ação ao selecionar um item
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Ação quando nada é selecionado, se necessário
            }
        }
    }
}

