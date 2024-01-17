package com.example.eventos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    private val REQUEST_CODE = 100 // Constante para a solicitação de seleção de imagem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feedback)

        val editTextTextMultiLine: EditText = findViewById(R.id.editTextTextMultiLine)
        val editTextTextEmailAddress2: EditText = findViewById(R.id.editTextTextEmailAddress2)
        val imageView13: ImageView = findViewById(R.id.imageView4) // Corrigido para imageView4
        val button8: Button = findViewById(R.id.button8) // Botão para cancelar ou voltar
        val button9: Button = findViewById(R.id.button9) // Botão para enviar feedback
        val btnCapturaEcra: Button = findViewById(R.id.btnCapturaEcra) // Botão para captura de tela

        btnCapturaEcra.setOnClickListener { onSelectImageClick() } // Listener para btnCapturaEcra

        button8.setOnClickListener {
            finish()
        }

        button9.setOnClickListener {
            val feedback = editTextTextMultiLine.text.toString()
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain" // Mudança para 'text/plain'
                putExtra(Intent.EXTRA_EMAIL, arrayOf("feedback@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Feedback do Aplicativo")
                putExtra(Intent.EXTRA_TEXT, feedback)
            }

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(this, "Nenhum cliente de email instalado.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun onSelectImageClick() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            val imageView13: ImageView = findViewById(R.id.imageView4)
            imageView13.setImageURI(imageUri)
        }
    }
}
