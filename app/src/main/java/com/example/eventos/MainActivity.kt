package com.example.eventos
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        }

        fun navegarParaSegunda(view: View) {
            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }
}