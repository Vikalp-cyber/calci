package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var inputTextView: TextView

    private var currentNumber: String = ""
    private var previousNumber : String = ""
    private var operator : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)


         resultTextView = findViewById<TextView>(R.id.textView)
         inputTextView = findViewById<TextView>(R.id.textView2)

        val btnClear : Button = findViewById(R.id.btnClear)
        val btnDivide : Button = findViewById(R.id.btnDivide)
        val btnMultiply : Button = findViewById(R.id.btnMultiply)
        val btnSub : Button = findViewById(R.id.btnSub)
        val btnPlus : Button = findViewById(R.id.btnPlus)
        val btnEqual : Button = findViewById(R.id.btnEqual)
        val btnDot : Button = findViewById(R.id.btnDot)

        val numberButtons = listOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )

        numberButtons.forEach{
            button -> button.setOnClickListener{
                val value = button.text.toString()
                appendNumber(value)
        }


        }

        btnPlus.setOnClickListener{
            setOperator("+")
        }
        btnSub.setOnClickListener{
            setOperator("-")
        }
        btnMultiply.setOnClickListener{
            setOperator("*")
        }
        btnDivide.setOnClickListener{
            setOperator("/")
        }

        btnDot.setOnClickListener{
            appendDot()
        }
        btnClear.setOnClickListener{
            clearInput()
        }
        btnEqual.setOnClickListener{
            calculateResult()
        }



    }

    private fun appendNumber(value: String){
        currentNumber += value
        inputTextView.text = currentNumber
    }
    private fun appendDot(){
        if(!currentNumber.contains(".")){
            currentNumber += "."
            inputTextView.text = currentNumber
        }
    }
    private fun setOperator(op: String){
        if(currentNumber.isNotEmpty()){
            previousNumber = currentNumber
            operator = op
            currentNumber = ""
            inputTextView.text = ""
        }
    }
    private fun calculateResult() {
        if (operator != null && previousNumber.isNotEmpty() && currentNumber.isNotEmpty()) {
            val result = when (operator) {
                "+" -> previousNumber.toDouble() + currentNumber.toDouble()
                "-" -> previousNumber.toDouble() - currentNumber.toDouble()
                "*" -> previousNumber.toDouble() * currentNumber.toDouble()
                "/" -> previousNumber.toDouble() / currentNumber.toDouble()
                else -> return
            }
            resultTextView.text = result.toString()
            clearOperation()
        }
    }

    private fun clearInput() {
        currentNumber = ""
        previousNumber = ""
        operator = null
        inputTextView.text = ""
        resultTextView.text = ""
    }
    private fun clearOperation() {
        currentNumber = ""
        previousNumber = ""
        operator = null
    }

}

