package br.com.fiap.simplemove

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val logout = findViewById<ImageButton>(R.id.img_logout)
        val pesquisar = findViewById<ImageButton>(R.id.img_lupa)

        logout.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        pesquisar.setOnClickListener {
            val i= Intent(this , MapView::class.java)
            startActivity(i)
        }


    }
}