package com.seuprojeto.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AdicionarFilmeActivity : AppCompatActivity() {

    // Instanciando o banco Firestore
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_filme)

        val editNome = findViewById<EditText>(R.id.editNome)
        val editCartaz = findViewById<EditText>(R.id.editCartaz)
        val editStreaming = findViewById<EditText>(R.id.editStreaming)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val cartaz = editCartaz.text.toString().trim()
            val streaming = editStreaming.text.toString().trim()

            if (nome.isNotEmpty() && cartaz.isNotEmpty() && streaming.isNotEmpty()) {
                salvarFilme(nome, cartaz, streaming)
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarFilme(nome: String, cartaz: String, streaming: String) {
        val filme = hashMapOf(
            "nome" to nome,
            "cartaz" to cartaz,
            "streaming" to streaming
        )

        db.collection("filmes_series")
            .add(filme)
            .addOnSuccessListener {
                Toast.makeText(this, "Filme cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Fecha a tela e volta
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao cadastrar!", Toast.LENGTH_SHORT).show()
            }
    }
}
