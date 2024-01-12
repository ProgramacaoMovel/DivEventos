package com.example.eventos

class dbModel {
    data class Noticias (
        var notId: String? = null,
        var notTitulo: String? = null,
        var notLocalizacao: String? = null,
        var notTexto: String? = null,
        var notCategoria: Categoria? = null,
    )
    enum class Categoria {
        UltimaHora,Mundo,Economia,Tecnologia,Desporto
    }
}