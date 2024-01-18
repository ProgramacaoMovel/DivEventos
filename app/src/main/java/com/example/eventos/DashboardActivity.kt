package com.example.eventos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventos.R.id.conadunto
import com.example.eventos.model.dbModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        // Configuração do botão para criar notícias
        val btnCriarnoti: Button = this.findViewById(R.id.btnCriarnoti)
        btnCriarnoti.setOnClickListener {
            val intent = Intent(this, CriarNoticiaActivity::class.java)
            startActivity(intent)
        }

        // Configuração da Toolbar
        val toolbar: Toolbar = findViewById(conadunto) // Use R.id.conadunto
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewNoticias)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val noticiasList = mutableListOf<dbModel.Noticias>()
        val adapter = NotiAdapter(noticiasList) { noticiaSelecionada ->
            onItemClicked(noticiaSelecionada as dbModel.Noticias)
        }
        recyclerView.adapter = adapter

        // Carregar dados do Firebase
        val databaseReference = FirebaseDatabase.getInstance().getReference("Noticias")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                noticiasList.clear()
                for (snapshot in dataSnapshot.children) {
                    val noticia = snapshot.getValue(dbModel.Noticias::class.java)
                    noticia?.let { noticiasList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Tratar erro
            }
        })


    }

    private fun onItemClicked(noticia: dbModel.Noticias) {
        val intent = Intent(this, DetalhesNoticiaActivity::class.java)
        intent.putExtra("NOTICIA", noticia) // Supondo que Noticias seja Serializable ou Parcelable
        startActivity(intent)
    }

    // Sobrescrever onCreateOptionsMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dash, menu)
        return true
    }

    // Sobrescrever onOptionsItemSelected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dashboard -> {
                startActivity(Intent(this, DashboardActivity::class.java))
                true
            }
            R.id.highlights -> {
                startActivity(Intent(this, HeadlinesActivity::class.java))
                true
            }
            R.id.feedback -> {
                startActivity(Intent(this, FeedbackActivity::class.java))
                true
            }
            R.id.logout -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
