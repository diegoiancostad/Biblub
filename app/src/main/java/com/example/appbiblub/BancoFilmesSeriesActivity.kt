package com.example.appbiblub

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class BancoFilmesSeriesActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var etCartaz: EditText
    private lateinit var etStreaming: EditText
    private lateinit var btnSalvar: Button
    private lateinit var layoutLista: LinearLayout

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banco_filmes_series)

        etNome = findViewById(R.id.etNome)
        etCartaz = findViewById(R.id.etCartaz)
        etStreaming = findViewById(R.id.etStreaming)
        btnSalvar = findViewById(R.id.btnSalvar)
        layoutLista = findViewById(R.id.layoutLista)

        btnSalvar.setOnClickListener {
            salvarFilmeOuSerie()
        }

        carregarFilmesSeries()
    }

    private fun salvarFilmeOuSerie() {
        val nome = etNome.text.toString()
        val cartaz = etCartaz.text.toString()
        val streaming = etStreaming.text.toString()

        if (nome.isEmpty() || cartaz.isEmpty() || streaming.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val dados = hashMapOf(
            "nome" to nome,
            "cartaz" to cartaz,
            "streaming" to streaming
        )

        db.collection("filmes_series")
            .add(dados)
            .addOnSuccessListener {
                Toast.makeText(this, "Filme/Série salvo!", Toast.LENGTH_SHORT).show()
                limparCampos()
                carregarFilmesSeries()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show()
                Log.e("Firestore", "Erro: ", it)
            }
    }

    private fun carregarFilmesSeries() {
        layoutLista.removeAllViews()

        db.collection("filmes_series")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val nome = document.getString("nome")
                    val streaming = document.getString("streaming")

                    val texto = TextView(this).apply {
                        text = "$nome\n$streaming\n"
                        textSize = 16f
                        setPadding(0, 8, 0, 8)
                    }
                    layoutLista.addView(texto)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao carregar filmes/séries", Toast.LENGTH_SHORT).show()
                Log.e("Firestore", "Erro: ", it)
            }
    }

    private fun limparCampos() {
        etNome.text.clear()
        etCartaz.text.clear()
        etStreaming.text.clear()
    }
}