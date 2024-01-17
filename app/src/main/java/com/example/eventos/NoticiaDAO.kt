package com.example.eventos

import com.example.eventos.model.Noticia
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.Properties

class NoticiaDAO(private val url: String, private val user: String, private val password: String) {

    // Use a companion object for constants, if necessary
    companion object {
        private const val INSERT_NOTICIA_SQL = "INSERT INTO noticias (titulo, localizacao, texto, imgUrl) VALUES (?, ?, ?, ?)"
        private const val SELECT_NOTICIAS_SQL = "SELECT * FROM noticias"
        private const val DELETE_NOTICIA_SQL = "DELETE FROM noticias WHERE id = ?"
    }

    // The getConnection method remains the same
    private fun getConnection(): Connection {
        return DriverManager.getConnection(url, user, password)
    }

    fun criarNoticia(noticia: Noticia) {
        getConnection().use { conn ->
            conn.prepareStatement(INSERT_NOTICIA_SQL).use { stmt ->
                stmt.setString(1, noticia.titulo)
                stmt.setString(2, noticia.localizacao)
                stmt.setString(3, noticia.texto)
                stmt.setString(4, noticia.imageUrl)
                stmt.executeUpdate()
            }
        }
    }

    fun lerNoticias(): List<Noticia> {
        val noticias = mutableListOf<Noticia>()
        getConnection().use { conn ->
            conn.prepareStatement(SELECT_NOTICIAS_SQL).use { stmt ->
                val rs = stmt.executeQuery()
                while (rs.next()) {
                    val noticia = Noticia(
                        id = rs.getInt("id"),
                        titulo = rs.getString("titulo"),
                        localizacao = rs.getString("localizacao"),
                        texto = rs.getString("texto"),
                        imageUrl = rs.getString("imgUrl")
                    )
                    noticias.add(noticia)
                }
            }
        }
        return noticias
    }

    fun deletarNoticia(id: Int) {
        getConnection().use { conn ->
            conn.prepareStatement(DELETE_NOTICIA_SQL).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }
}
