package br.com.fiap.simplemove

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MapView : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_map_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val logout = findViewById<ImageButton>(R.id.img_logout)

        logout.setOnClickListener {
            val i = Intent(this, HomePage::class.java)
            startActivity(i)
        }

        val bus=findViewById<ImageButton>(R.id.btn_bus_notselected)
        val taxi=findViewById<ImageButton>(R.id.btn_taxi_notselected)
        val selectedBus=findViewById<ImageButton>(R.id.btn_bus_selected)
        val selectedTaxi=findViewById<ImageButton>(R.id.btn_taxi_selected)
        val spinnerTaxi=findViewById<Spinner>(R.id.list_taxi)
        val spinnerBus=findViewById<Spinner>(R.id.list_bus)

        bus.setOnClickListener {
            spinnerTaxi.visibility = View.INVISIBLE
            spinnerBus.visibility = View.VISIBLE
            bus.visibility = View.INVISIBLE
            selectedBus.visibility = View.VISIBLE
            selectedTaxi.visibility = View.INVISIBLE
            taxi.visibility = View.VISIBLE
        }
        selectedBus.setOnClickListener {
            spinnerTaxi.visibility = View.INVISIBLE
            spinnerBus.visibility = View.INVISIBLE
            selectedBus.visibility = View.INVISIBLE
            bus.visibility = View.VISIBLE
            selectedTaxi.visibility = View.INVISIBLE
            taxi.visibility = View.VISIBLE
        }
        taxi.setOnClickListener {
            spinnerTaxi.visibility = View.VISIBLE
            spinnerBus.visibility = View.INVISIBLE
            taxi.visibility = View.INVISIBLE
            selectedTaxi.visibility = View.VISIBLE
            selectedBus.visibility = View.INVISIBLE
            bus.visibility = View.VISIBLE
        }
        selectedTaxi.setOnClickListener {
            spinnerBus.visibility = View.INVISIBLE
            spinnerTaxi.visibility = View.INVISIBLE
            selectedTaxi.visibility = View.INVISIBLE
            taxi.visibility = View.VISIBLE
            selectedBus.visibility = View.INVISIBLE
            bus.visibility = View.VISIBLE
        }

    }
}