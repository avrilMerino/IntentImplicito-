package com.example.intentimplicito
import android.content.Intent //Para crear "mensajes" (intents) que piden hacer algo
import android.net.Uri// Para construir URIs como "tel:112"
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intentimplicito.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    // "binding" nos da acceso directo a los views del XML (btnAgregarC, btn112...)
    // sin tener que usar findViewById. Se inicializa más abajo
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //Solo maqueta los bordes; no afecta a la lógica

        // 1) Inflamos el layout usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 2) Mostramos en pantalla ese layout
        setContentView(binding.root)

//LÓGICA DE LOS BOTONES

        // Botón "AGREGAR CONTACTO":
        // Al pulsarlo, abrimos la SEGUNDA activity (MainActivity2) de nuestra app
        // Esto se hace con un "intent explícito" (le digo a Android la clase exacta
        binding.btnAgregarC.setOnClickListener {
            // Intent(this, MainActivity2::class.java) = "Quiero abrir MainActivity2"
            startActivity(Intent(this, MainActivity2::class.java))
        }

        // Botón "LLamar al 112":
        // Creamos un "intent implícito" que no abre una activity nuestra,
        // sino cualquier app del sistema que pueda "marcar un número"
        // ACTION_DIAL abre el marcador con el 112 escrito, y NO necesita permisos.
        binding.btn112.setOnClickListener {
            // Uri.parse("tel:112") construye la dirección de teléfono para el intent
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:112"))
            // Android busca una app que sepa manejar ACTION_DIAL y la abre (el marcador)
            startActivity(dialIntent)
        }
    }
}
