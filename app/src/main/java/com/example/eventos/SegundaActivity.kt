package com.example.eventos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SegundaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)
    }
    fun voltarParaMain(view: View) {
        finish()
    }
}
