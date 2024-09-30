package com.example.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calc.ui.theme.CalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = number1,
                    onValueChange = { number1 = it },
                    label = { Text("Number 1") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = number2,
                    onValueChange = { number2 = it },
                    label = { Text("Number 2") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(text = "Result: $result")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = {
                        result = calculate(number1, number2, "+")
                    }) {
                        Text("+")
                    }

                    Button(onClick = {
                        result = calculate(number1, number2, "-")
                    }) {
                        Text("-")
                    }

                    Button(onClick = {
                        result = calculate(number1, number2, "*")
                    }) {
                        Text("*")
                    }

                    Button(onClick = {
                        result = calculate(number1, number2, "/")
                    }) {
                        Text("/")
                    }
                }
            }
        }
    )
}

fun calculate(num1: String, num2: String, operation: String): String {
    val n1 = num1.toDoubleOrNull()
    val n2 = num2.toDoubleOrNull()

    return if (n1 != null && n2 != null) {
        when (operation) {
            "+" -> (n1 + n2).toString()
            "-" -> (n1 - n2).toString()
            "*" -> (n1 * n2).toString()
            "/" -> if (n2 != 0.0) (n1 / n2).toString() else "Cannot divide by zero"
            else -> "Invalid operation"
        }
    } else {
        "Invalid input"
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalcTheme {
        CalculatorApp()
    }
}
