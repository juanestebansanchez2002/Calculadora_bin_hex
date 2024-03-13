package com.example.calculadora_bin_hex

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.calculadora_bin_hex.R

class MainActivity : ComponentActivity() {

    private lateinit var tvInput: TextView
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verticalactivity)

        // Inicializar vistas utilizando los IDs actualizados del XML
        tvInput = findViewById(R.id.tuIdDelEditText) // Asegúrate de que este ID esté correcto en tu XML para el EditText
        tvResult = findViewById(R.id.textView2) // TextView para mostrar el resultado

        // Configurar clics de botones utilizando los IDs actualizados
        val btnOne: ImageButton = findViewById(R.id.imageButton2) // Botón para el número 1
        val btnZero: ImageButton = findViewById(R.id.imageButton3) // Botón para el número 0
        val btnSum: ImageButton = findViewById(R.id.imageButton8) // Botón para suma
        val btnRes: ImageButton = findViewById(R.id.imageButton5) // Botón para resta
        val btnMul: ImageButton = findViewById(R.id.imageButton4) // Botón para multiplicación
        val btnDiv: ImageButton = findViewById(R.id.imageButton9) // Botón para división
        val btnClear: Button = findViewById(R.id.clearButton) // Botón para limpiar
        val btnEquals: Button = findViewById(R.id.equalsButton) // Botón para calcular

        // Definir comportamientos de botones
        btnOne.setOnClickListener { appendToInput("1") }
        btnZero.setOnClickListener { appendToInput("0") }
        btnSum.setOnClickListener { appendToInput("+") }
        btnRes.setOnClickListener { appendToInput("-") }
        btnMul.setOnClickListener { appendToInput("*") }
        btnDiv.setOnClickListener { appendToInput("/") }
        btnClear.setOnClickListener { clearInput() }
        btnEquals.setOnClickListener { calculateResult() }
    }

    private fun appendToInput(str: String) {
        val currentText = tvInput.text.toString()
        tvInput.text = currentText + str
    }

    private fun clearInput() {
        tvInput.text = ""
        tvResult.text = ""
    }

    private fun calculateResult() {
        // Tu implementación no cambia respecto a la versión de AppCompatActivity
        val expression = tvInput.text.toString()

        if (expression.isEmpty()) {
            return
        }

        val parts = expression.split(Regex("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)"))

        if (parts.size != 3) {
            tvResult.text = "Error"
            return
        }

        val num1 = parts[0].toIntOrNull(2)
        val operator = parts[1]
        val num2 = parts[2].toIntOrNull(2)

        if (num1 == null || num2 == null) {
            tvResult.text = "Error"
            return
        }

        val result = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0) num1 / num2 else null
            else -> null
        }

        if (result != null) {
            val binaryResult = Integer.toBinaryString(result)
            val decimalResult = result
            val octalResult = Integer.toOctalString(result)
            tvResult.text = "Binario: $binaryResult\nDecimal: $decimalResult\nOctal: $octalResult"
        } else {
            tvResult.text = "Error"
        }
    }
}