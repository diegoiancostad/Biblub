package com.example.appbiblub.bancodedados

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    suspend fun inserir(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE email = :email AND senha = :senha")
    suspend fun autenticar(email: String, senha: String): Usuario?

    @Query("SELECT * FROM usuario WHERE email = :email")
    suspend fun buscarPorEmail(email: String): Usuario?
}
