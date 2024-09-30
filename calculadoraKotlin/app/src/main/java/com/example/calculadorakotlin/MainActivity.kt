package com.example.calculadora

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var num1EditText: EditText
    private lateinit var num2EditText: EditText
    private lateinit var operationSpinner: Spinner
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1EditText = findViewById(R.id.num1)
        num2EditText = findViewById(R.id.num2)
        operationSpinner = findViewById(R.id.operationSpinner)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        val operations = arrayOf("Adição", "Subtração", "Multiplicação", "Divisão")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        operationSpinner.adapter = adapter

        calculateButton.setOnClickListener {
            calculateResult()
        }
    }

    private fun calculateResult() {
        val num1 = num1EditText.text.toString().toDoubleOrNull()
        val num2 = num2EditText.text.toString().toDoubleOrNull()
        val operation = operationSpinner.selectedItem.toString()

        if (num1 == null || num2 == null) {
            resultTextView.text = "Por favor, insira números válidos."
            return
        }

        val result = when (operation) {
            "Adição" -> num1 + num2
            "Subtração" -> num1 - num2
            "Multiplicação" -> num1 * num2
            "Divisão" -> {
                if (num2 != 0.0) {
                    num1 / num2
                } else {
                    resultTextView.text = "Divisão por zero não é permitida."
                    return
                }
            }
            else -> null
        }

        resultTextView.text = "Resultado: $result"
    }
}
