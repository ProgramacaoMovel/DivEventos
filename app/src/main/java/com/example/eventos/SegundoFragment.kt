package com.example.eventos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SegundoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_segundo, container, false)

        val textNomeDetalhes = view.findViewById<TextView>(R.id.textNomeDetalhes)
        val textIdadeDetalhes = view.findViewById<TextView>(R.id.textIdadeDetalhes)
        val textDetalhesTexto = view.findViewById<TextView>(R.id.textDetalhesTexto)

        textNomeDetalhes.text = "Zé Escola"
        textIdadeDetalhes.text = "22 anos"
        textDetalhesTexto.text = "Detalhes adicionais sobre o aluno Zé Escola."

        return view
    }
}