package com.example.eventos

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.FirebaseDatabase

class CriarNoticiaActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criarnoticia)

        val tituloEditText: EditText = findViewById(R.id.create_titulo_noticia)
        val localizacaoEditText: EditText = findViewById(R.id.create_loc_noticia)
        val textoEditText: EditText = findViewById(R.id.create_corpo_noticia)
        val criarNoticiaButton: Button = findViewById(R.id.criarNoticiaButton)

        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                imageUri = uri
                // Aqui você pode, por exemplo, exibir a imagem selecionada em um ImageView
            }

        val selectImageButton = findViewById<Button>(R.id.btnSelectImage)
        selectImageButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
            } else {
                imagePickerLauncher.launch("image/*")
            }
        }

        val spinner: Spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Faça algo com o item selecionado
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(this@CriarNoticiaActivity, "Tem que selecionar uma categoria para prosseguir.", Toast.LENGTH_SHORT).show()
            }
        }

        criarNoticiaButton.setOnClickListener {
            val titulo = tituloEditText.text.toString().trim()
            val localizacao = localizacaoEditText.text.toString().trim()
            val texto = textoEditText.text.toString().trim()


            if (titulo.isNotEmpty() && localizacao.isNotEmpty() && texto.isNotEmpty()) {
                val noticia = dbModel.Noticias(
                    titulo,
                    localizacao,
                    texto,
                    imageUri.toString()
                ) // Substitua Noticia pela sua classe de modelo
                enviarNoticiaParaFirebase(noticia)
            } else {
                if (imageUri == null) {
                    Toast.makeText(this, "Tem que selecionar uma imagem para prosseguir.", Toast.LENGTH_SHORT).show()
                } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    imagePickerLauncher.launch("image/*")
                } else {
                    Toast.makeText(this, "Permissão de acesso ao armazenamento é necessária para selecionar imagens.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun enviarNoticiaParaFirebase(noticia: dbModel.Noticias) { // Substitua Noticia pela sua classe de modelo
        val databaseReference = FirebaseDatabase.getInstance().getReference("noticias")
        val noticiaId = databaseReference.push().key // Cria um ID único
        noticia.notId =
            noticiaId ?: "" // Certifique-se de que sua classe Noticia tenha um campo 'id'

        databaseReference.child(noticiaId ?: "").setValue(noticia)
            .addOnSuccessListener {
                Toast.makeText(this, "Notícia criada com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Fecha a atividade após o sucesso
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao criar notícia.", Toast.LENGTH_SHORT).show()
            }
    }
    data class Noticia(
        val titulo: String,
        val localizacao: String,
        val texto: String,
        val imageUrl: String,
        var id: String = ""
    )
}

