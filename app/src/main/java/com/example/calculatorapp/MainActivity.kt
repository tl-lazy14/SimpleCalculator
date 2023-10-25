package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    private var expression: String = "0"
    // Ignore comma button, perform only with integer number
    private var result: Int = 0

    private lateinit var input: TextView
    private lateinit var output: TextView

    private fun solveMathOperation(operation: String): Double {
        return ExpressionBuilder(operation).build().evaluate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        output = findViewById(R.id.output)
        input.text = "0"

        val button0: Button = findViewById(R.id.button_0)
        val button1: Button = findViewById(R.id.button_1)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.button_3)
        val button4: Button = findViewById(R.id.button_4)
        val button5: Button = findViewById(R.id.button_5)
        val button6: Button = findViewById(R.id.button_6)
        val button7: Button = findViewById(R.id.button_7)
        val button8: Button = findViewById(R.id.button_8)
        val button9: Button = findViewById(R.id.button_9)

        val buttonCE: Button = findViewById(R.id.clear_entity)
        val buttonC: Button = findViewById(R.id.clear_all)
        val buttonBS: Button = findViewById(R.id.backspace)
        val buttonPlus: Button = findViewById(R.id.button_plus)
        val buttonMinus: Button = findViewById(R.id.button_minus)
        val buttonMultiply: Button = findViewById(R.id.multiply)
        val buttonDivision: Button = findViewById(R.id.button_division)
        val buttonOpposite: Button = findViewById(R.id.button_opposite)
        val buttonEqual: Button = findViewById(R.id.button_equal)

        val numberButtons = listOf(
            button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                if (expression == "0") {
                    expression = button.text.toString()
                } else {
                    expression += button.text
                }
                input.text = expression
            }
        }

        buttonPlus.setOnClickListener { performOperation("+") }
        buttonMinus.setOnClickListener {
            if (expression == "0") {
                expression = "-";
                input.text = expression
            } else {
                performOperation("-")
            }
        }
        buttonMultiply.setOnClickListener { performOperation("*") }
        buttonDivision.setOnClickListener { performOperation("/") }

        buttonEqual.setOnClickListener {
            try {
                result = solveMathOperation(expression).toInt()
                output.text = result.toString()
            } catch (e: Exception) {
                output.text = "Invalid Input"
            }
        }

        buttonCE.setOnClickListener {
            if (expression.toDoubleOrNull() != null) {
                expression = "0"
                input.text = "0"
            }
        }

        buttonC.setOnClickListener {
            expression = "0"
            input.text = "0"
            output.text = ""
        }

        buttonBS.setOnClickListener {
            if (expression.length > 1) {
                expression = expression.dropLast(1)
            } else if (expression.length == 1) {
                expression = "0"
            }
            input.text = expression
        }

        // Ignore comma button

        buttonOpposite.setOnClickListener {
            val num = expression.toIntOrNull();
            if (num != null) {
                expression = (num * (-1)).toString()
                input.text = expression
            }
        }
    }

    private fun performOperation(operation: String) {
        expression += operation
        input.text = expression
    }
}