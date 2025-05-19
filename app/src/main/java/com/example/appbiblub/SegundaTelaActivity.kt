package com.example.appbiblub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.emailInput)
        val senha = findViewById<EditText>(R.id.passwordInput)
        val botaoLogin = findViewById<Button>(R.id.loginButton)

        botaoLogin.setOnClickListener {
            val emailText = email.text.toString()
            val senhaText = senha.text.toString()

            if (emailText.isNotEmpty() && senhaText.isNotEmpty()) {
                // Simular sucesso no login
                Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


annotation class SegundaTelaActivity
