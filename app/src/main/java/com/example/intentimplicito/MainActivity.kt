package com.example.intentimplicito

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intentimplicito.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //para ir al formulario q introduce los datos del nuevo contacto
        binding.btnAgregarC.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        //llamar al 112 (abre el marcador; no requiere permiso)
        binding.btn112.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:112"))
            startActivity(dialIntent)
        }
    }
}
