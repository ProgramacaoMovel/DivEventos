package com.example.eventos.model

class dbModel {
    data class Noticias (
        var notId: String? = null,
        var notTitulo: String? = null,
        var notLocalizacao: String? = null,
        var notTexto: String? = null,
    )
}