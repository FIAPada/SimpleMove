package br.com.fiap.simplemove

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val usuario = findViewById<EditText>(R.id.edt_login)
        val senha = findViewById<EditText>(R.id.edt_senha)
        val registrar=findViewById<Button>(R.id.btn_registrar)
        val login=findViewById<Button>(R.id.btn_login)


        registrar.setOnClickListener {
            val credenciais=this.getSharedPreferences("credenciais", Context.MODE_PRIVATE)
            val editor =credenciais.edit()

            editor.putString("User", usuario.editableText.toString())
            editor.putString("Password", senha.editableText.toString())
            editor.apply()

            Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        login.setOnClickListener {
            val i= Intent(this , HomePage::class.java)

            val credenciaisExistentes=this.getSharedPreferences("credenciais", Context.MODE_PRIVATE)

            val user= credenciaisExistentes.getString("User","None")
            val password= credenciaisExistentes.getString("Password","")

            if (user.equals("None") or (usuario.editableText.toString() == "")){
                Toast.makeText(this, "Registre-se preenchendo suas informações e clicando em registrar", Toast.LENGTH_LONG).show()
            }else if(user.equals(usuario.editableText.toString()) and password.equals(senha.editableText.toString())){
                startActivity(i)
            }else{
                Toast.makeText(this, "Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show()
            }
        }



    }
}