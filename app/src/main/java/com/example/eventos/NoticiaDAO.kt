package com.example.eventos

import com.example.eventos.model.Noticia
import java.sql.Connection
import java.sql.DriverManager
import java.sql.DriverManager.getConnection
import java.sql.SQLException

class NoticiaDAO(private val url: String, private val usuario: String, private val senha: String) {

    private fun getConnection(): Connection {
        return DriverManager.getConnection(url, usuario, senha)
    }

    fun criarNoticia(noticia: Noticia) {
        val sql = "INSERT INTO noticias (titulo, localizacao, texto, imgUrl) VALUES (?, ?, ?, ?)"
        getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, noticia.titulo)
                stmt.setString(2, noticia.localizacao)
                stmt.setString(3, noticia.texto)
                stmt.setString(4, noticia.imageUrl) // Correção: era noticia.imageUrl, não noticia.imgUrl
                stmt.executeUpdate()
            }
        }
    }

    fun lerNoticias(): List<Noticia> {
        val noticias = mutableListOf<Noticia>()
        val sql = "SELECT * FROM noticias"
        getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                while (rs.next()) {
                    val noticia = Noticia(
                        id = rs.getInt("id"),
                        titulo = rs.getString("titulo"),
                        localizacao = rs.getString("localizacao"),
                        texto = rs.getString("texto"),
                        imageUrl = rs.getString("imgUrl") // Correção: era imageUrl, não imgUrl
                    )
                    noticias.add(noticia)
                }
            }
        }
        return noticias
    }

    fun deletarNoticia(id: Int) {
        val sql = "DELETE FROM noticias WHERE id = ?"
        getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }
}

