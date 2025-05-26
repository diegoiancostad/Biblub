package com.seuprojeto.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.R

class ListaFilmesActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        val textLista = findViewById<TextView>(R.id.textLista)

        listarFilmes { texto ->
            textLista.text = texto
        }
    }

    private fun listarFilmes(callback: (String) -> Unit) {
        db.collection("filmes_series")
            .get()
            .addOnSuccessListener { result ->
                val builder = StringBuilder()

                for (document in result) {
                    val nome = document.getString("nome")
                    val cartaz = document.getString("cartaz")
                    val streaming = document.getString("streaming")

                    builder.append("🎬 Nome: $nome\n")
                    builder.append("🖼️ Cartaz: $cartaz\n")
                    builder.append("▶️ Streaming: $streaming\n\n")
                }

                callback(builder.toString())
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Erro ao ler documentos", e)
                Toast.makeText(this, "Erro ao carregar lista!", Toast.LENGTH_SHORT).show()
                callback("Erro ao carregar lista.")
            }
    }
}
