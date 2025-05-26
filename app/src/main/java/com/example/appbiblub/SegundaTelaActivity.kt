package com.example.appbiblub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appbiblub.bancodedados.AppDatabase
import kotlinx.coroutines.launch
import kotlin.jvm.java

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)

        val db = AppDatabase.getDatabase(this)
        val usuarioDao = db.usuarioDao()

        loginButton.setOnClickListener {
            val emailText = emailInput.text.toString().trim()
            val senhaText = passwordInput.text.toString().trim()

            if (emailText.isNotEmpty() && senhaText.isNotEmpty()) {
                lifecycleScope.launch {
                    val usuario = usuarioDao.autenticar(emailText, senhaText)

                    runOnUiThread {
                        if (usuario != null) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Login realizado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Aqui você pode redirecionar para outra tela, por exemplo:
                            val intent = Intent(this@LoginActivity, TelaPrincipalActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Email ou senha incorretos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
