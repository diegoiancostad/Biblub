package com.example.appbiblub

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var btnAvancar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        editTextNome = findViewById(R.id.etUsuario)
        editTextEmail = findViewById(R.id.etEmail)
        editTextSenha = findViewById(R.id.etSenha)
        btnAvancar = findViewById(R.id.btnAvancar)

        // Desabilita o botão inicialmente
        btnAvancar.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val nome = editTextNome.text.toString().trim()
                val email = editTextEmail.text.toString().trim()
                val senha = editTextSenha.text.toString().trim()
                btnAvancar.isEnabled = nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        // Aplica o listener a todos os campos
        editTextNome.addTextChangedListener(textWatcher)
        editTextEmail.addTextChangedListener(textWatcher)
        editTextSenha.addTextChangedListener(textWatcher)

        btnAvancar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
