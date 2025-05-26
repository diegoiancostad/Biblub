package com.example.appbiblub

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TelaPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)

        val btnAssistir = findViewById<Button>(R.id.btnAssistir)
        val btnMinhaLista = findViewById<Button>(R.id.btnMinhaLista)

        val imgRick = findViewById<ImageView>(R.id.imgRick)
        val imgTheBoys = findViewById<ImageView>(R.id.imgTheBoys)
        val imgPeaky = findViewById<ImageView>(R.id.imgPeaky)
        val imgInvincible = findViewById<ImageView>(R.id.imgInvincible)

        // Botão Assistir leva para o site do filme Flow
        btnAssistir.setOnClickListener {
            abrirLink("https://www.netflix.com/br/title/81466239")
        }

        // Botão Minha Lista
        btnMinhaLista.setOnClickListener {
            Toast.makeText(this, "Adicionado à sua lista", Toast.LENGTH_SHORT).show()
        }

        // Clique nas imagens das séries
        imgRick.setOnClickListener {
            abrirLink("https://www.hbomax.com/br/pt/series/urn:hbo:series:GYvubBQ1xq4uDwgEAAAAO")
        }

        imgTheBoys.setOnClickListener {
            abrirLink("https://www.primevideo.com/detail/0N4QG5Z1QBA4VCPOG9HMLXKD99")
        }

        imgPeaky.setOnClickListener {
            abrirLink("https://www.netflix.com/br/title/80002479")
        }

        imgInvincible.setOnClickListener {
            abrirLink("https://www.primevideo.com/detail/0OBZK7N8US5S1D8A43VKQ92TJJ")
        }
    }

    // Função para abrir o link no navegador
    private fun abrirLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}

