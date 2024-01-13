package com.example.eventos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        val btnCriarnoti : Button = this.findViewById(R.id.btnCriarnoti)
        btnCriarnoti.setOnClickListener(){
            val intent = Intent(this, CriarNoticiaActivity::class.java)
            startActivity(intent)

        }
    }


}