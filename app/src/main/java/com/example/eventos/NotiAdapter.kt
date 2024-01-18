package com.example.eventos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventos.model.dbModel

class NotiAdapter(private val noticiasList: List<dbModel.Noticias>) : RecyclerView.Adapter<NotiAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewNoticia)
        val tituloView: TextView = view.findViewById(R.id.tituloNoti)
        val localizacaoView: TextView = view.findViewById(R.id.textViewLocalizacao)
        //val textView: TextView = view.findViewById(R.id.textViewTexto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notirecicle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticiasList[position]
        // Carregue a imagem com Glide ou outra biblioteca
        holder.tituloView.text = noticia.notTitulo
        holder.localizacaoView.text = noticia.notLocalizacao
        //holder.textView.text = noticia.notTexto
    }

    override fun getItemCount() = noticiasList.size
}
