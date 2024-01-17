package com.example.eventos.model



import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseConnection {
    @Throws(SQLException::class)
    fun connect(): Connection {
        val url = "jdbc:postgresql://127.0.0.1:5432/DivEventos"
        val user = "postgres"
        val password = "666999"

        return DriverManager.getConnection(url, user, password)
    }
}



data class Noticia(
    val id: Int,
    val titulo: String,
    val localizacao: String,
    val texto: String,
    val imageUrl: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class ResponseModel(
    val success: Boolean,
    val message: String
)


    data class LoginResponse(
        val userId: String,
        val token: String,
        val name: String
    )




    data class UserRegistration(
        val email: String,
        val password: String,
        val nome: String,
        val apelido: String
    )

data class UserRegistrationResponse(
    val success: Boolean,
    val userId: String?,
    val token: String?,
    val message: String?
)

