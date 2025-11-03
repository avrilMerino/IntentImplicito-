package com.example.intentimplicito

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract //API oficial para tratar con Contactos
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2) //Mostramos el layout de esta pantalla
        // Obtenemos referencias a los 3 EditText y al botón del layout
        // (Aquí uso findViewById por simplicidad
        val etNombre  = findViewById<EditText>(R.id.lblNombre) //nombre
        val etNumero  = findViewById<EditText>(R.id.lblNumero)   //teléfono
        val etEmail   = findViewById<EditText>(R.id.editTextTextEmailAddress)  //email
        val btnInsert = findViewById<Button>(R.id.btnInsertarContacto)

        // Cuando pulso "INSERTAR CONTACTO"
        btnInsert.setOnClickListener {
            // 1) Cojo lo que ha escrito el usuario y quito espacios delante/detrás
            val nombre = etNombre.text.toString().trim()
            val numero = etNumero.text.toString().trim()
            val email  = etEmail.text.toString().trim()

            // 2) Validación mínima: sin nombre o sin número no creamos nada
            if (nombre.isEmpty() || numero.isEmpty()) {
                Toast.makeText(
                    this,
                    "Nombre y número son obligatorios",
                    Toast.LENGTH_SHORT
                ).show()
                // Salimos del onClick sin seguir
                return@setOnClickListener
            }

            // 3) Construimos un INTENT IMPLÍCITO especial para "insertar contacto".
            //  No lo guardamos nosotros: abrimos la app de Contactos con los datos ya puestos, y el usuario pulsa "Guardar".
            val insertIntent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
                // Este type indica que el contenido que vamos a insertar es un "RawContact"
                type = ContactsContract.RawContacts.CONTENT_TYPE

                // Metemos dentro del intent los valores para que aparezcan ya rellenados
                putExtra(ContactsContract.Intents.Insert.NAME, nombre) // nombre del contacto
                putExtra(ContactsContract.Intents.Insert.PHONE, numero) // teléfono principal

                if (email.isNotEmpty()) {
                    putExtra(ContactsContract.Intents.Insert.EMAIL, email) // email
                }
            }

            // 4) Lanzamos la app de Contactos. El usuario verá el formulario con
            //  esos datos y tendrá que pulsar "GUARDAR". No hacen falta permisos
            startActivity(insertIntent)
        }
    }
}
