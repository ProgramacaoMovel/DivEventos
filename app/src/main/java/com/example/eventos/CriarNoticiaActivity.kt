package com.example.eventos



import android.Manifest
import android.content.Intent
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
import com.example.eventos.api.RetrofitInstance
import com.example.eventos.model.Noticia
import com.example.eventos.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        val buttonVoltar: Button = findViewById(R.id.buttonVoltar)

        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                imageUri = uri
            }

        val selectImageButton = findViewById<Button>(R.id.btnSelectImage)
        selectImageButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                )
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
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(
                    this@CriarNoticiaActivity,
                    "Tem que selecionar uma categoria para prosseguir.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        criarNoticiaButton.setOnClickListener {
            val titulo = tituloEditText.text.toString().trim()
            val localizacao = localizacaoEditText.text.toString().trim()
            val texto = textoEditText.text.toString().trim()
            if (titulo.isNotEmpty() && localizacao.isNotEmpty() && texto.isNotEmpty() && imageUri != null) {
                val noticia = Noticia(
                    titulo,
                    localizacao,
                    texto,
                    imageUri.toString()
                )
                enviarNoticiaParaServidor(noticia)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, preencha todos os campos e selecione uma imagem.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        buttonVoltar.setOnClickListener {
            finish()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    imagePickerLauncher.launch("image/*")
                } else {
                    Toast.makeText(
                        this,
                        "Permissão de acesso ao armazenamento é necessária para selecionar imagens.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun enviarNoticiaParaServidor(noticia: Noticia) {
        RetrofitInstance.service.criarNoticia(noticia).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(this@CriarNoticiaActivity, "Notícia criada com sucesso!", Toast.LENGTH_SHORT).show()
                    // Inicia DashboardActivity após o sucesso
                    val intent = Intent(this@CriarNoticiaActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@CriarNoticiaActivity, "Erro ao criar notícia.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@CriarNoticiaActivity, "Erro na rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

