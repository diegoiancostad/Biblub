package com.example.appbiblub.bancodedados

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val usuario: String,
    val email: String,
    val senha: String
)
