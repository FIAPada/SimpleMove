package br.com.fiap.simplemove

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException
import kotlin.concurrent.thread
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private var databaseUrl = "https://simple-move-a8a86-default-rtdb.firebaseio.com"

    private lateinit var user: EditText
    private lateinit var pass: EditText

    private val clientHttp = OkHttpClient.Builder().build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        user = findViewById<EditText>(R.id.edt_login)
        pass = findViewById<EditText>(R.id.edt_senha)
    }

    fun onClickRegister(view: View) {
        val userText = user.editableText.toString()
        val passText = pass.editableText.toString()

        thread {
            val usuarioJson = """
            {
                "user": "$userText",
                "pass": "$passText"
            }
        """.trimIndent()

            val body = RequestBody.create(MediaType.parse("application/json"), usuarioJson)

            val request = Request.Builder().url("$databaseUrl/users.json").post(body).build()

            val call = clientHttp.newCall(request)

            var response: Response? = null;
            try {
                response = call.execute()
                Log.i("RESPONSE REGISTER", response.body().toString())
            } catch (error: IOException) {
                error.printStackTrace()
            }
        }

        Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()
    }

    fun onClickLogin(view: View) {
        thread {
            val request = Request.Builder().url("$databaseUrl/users.json").get().build()
            val call = clientHttp.newCall(request)

            var response: Response? = null;
            try {
                response = call.execute()
                val responseJson = response.body()!!.string()

                Log.i("RESPONSE LOGIN", responseJson)

                val gson = Gson().fromJson(responseJson, Map::class.java)
                val users = gson.values

                for (user in users) {
                    val userJson = user as Map<*, *>
                    val userText = userJson["user"] as String
                    val passText = userJson["pass"] as String

                    if (userText == this.user.editableText.toString() && passText == this.pass.editableText.toString()) {
                        runOnUiThread {
                            Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT)
                                .show()

                            val i= Intent(this , HomePage::class.java)

                            startActivity(i)
                        }
                        return@thread
                    }
                }
            } catch (error: IOException) {
                Toast.makeText(this, "Usuário não existente ou senha incorreta.", Toast.LENGTH_SHORT).show()
                error.printStackTrace()
            }
        }
    }
}