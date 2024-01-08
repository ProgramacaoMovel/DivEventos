package com.example.eventos
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
class PrimeiroFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate do layout para este fragment
        val view = inflater.inflate(R.layout.fragment_primeiro, container, false)

        val textNome = view.findViewById<TextView>(R.id.textNome)
        val textIdade = view.findViewById<TextView>(R.id.textIdade)
        val buttonDetalhes = view.findViewById<Button>(R.id.buttonDetalhes)

        textNome.text = "Zé Escola"
        textIdade.text = "22 anos"

        // Evento no botão para navegar para o SegundoFragment
        buttonDetalhes.setOnClickListener {
            val fragmentManager: FragmentManager? = fragmentManager
            val fragmentTransaction: FragmentTransaction? = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.container, SegundoFragment()) // Substituir pelo SegundoFragment
            fragmentTransaction?.addToBackStack(null) // Adicionar à pilha de fragmentos para voltar
            fragmentTransaction?.commit()
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonDetalhes = view.findViewById<Button>(R.id.buttonDetalhes)
        buttonDetalhes.setOnClickListener {
            findNavController().navigate(R.id.action_primeiroFragment_to_segundoFragment)
        }
    }
}