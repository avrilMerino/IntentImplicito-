package com.example.intentimplicito

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val etNombre  = findViewById<EditText>(R.id.lblNombre)
        val etNumero  = findViewById<EditText>(R.id.lblNumero)
        val etEmail   = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val btnInsert = findViewById<Button>(R.id.btnInsertarContacto)

        btnInsert.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val numero = etNumero.text.toString().trim()
            val email  = etEmail.text.toString().trim()

            if (nombre.isEmpty() || numero.isEmpty()) {
                Toast.makeText(this, "Nombre y número son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Intent implícito para insertar contacto en la app de Contactos
            val insertIntent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
                type = ContactsContract.RawContacts.CONTENT_TYPE
                putExtra(ContactsContract.Intents.Insert.NAME, nombre)
                putExtra(ContactsContract.Intents.Insert.PHONE, numero)
                if (email.isNotEmpty()) {
                    putExtra(ContactsContract.Intents.Insert.EMAIL, email)
                }
            }

            // Abre la app de Contactos con los campos ya rellenados
            startActivity(insertIntent)
        }
    }
}
