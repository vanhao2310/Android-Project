package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var textResult: TextView

    var state: Int = 1
    var op1: Int = 0
    var op2: Int = 0
    var op: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textView)

        findViewById<Button>(R.id.button0).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
        findViewById<Button>(R.id.buttonCE).setOnClickListener(this)
        findViewById<Button>(R.id.buttonC).setOnClickListener(this)
        findViewById<Button>(R.id.buttonBS).setOnClickListener(this)
        findViewById<Button>(R.id.buttonDivide).setOnClickListener(this)
        findViewById<Button>(R.id.buttonSub).setOnClickListener(this)
        findViewById<Button>(R.id.buttonAdd).setOnClickListener(this)
        findViewById<Button>(R.id.buttonAddSub).setOnClickListener(this)
        findViewById<Button>(R.id.buttonPoint).setOnClickListener(this)
        findViewById<Button>(R.id.buttonEqual).setOnClickListener(this)
        findViewById<Button>(R.id.buttonx).setOnClickListener(this)


    }

    val btnNum = arrayOf(R.id.button0, R.id.button1,
                            R.id.button2, R.id.button3,
                            R.id.button4, R.id.button5,
                            R.id.button6, R.id.button7,
                            R.id.button8, R.id.button9)

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id in btnNum) {
            var num: Int = (p0 as? Button)?.text.toString().toIntOrNull() ?: 0
            addDigit(num)
        } else if (id == R.id.buttonAdd) {
            if (state == 2) {
                op1 = calculate()
                op2 = 0
                textResult.text = "0"
                op = 1
            } else {
                state = 2
                textResult.text = "0"
                op = 1
            }

        } else if (id == R.id.buttonSub) {
            if (state == 2) {
                op1 = calculate()
                textResult.text = "0"
                op = 2
            } else {
                state = 2
                textResult.text = "0"
                op = 2
            }
        } else if (id == R.id.buttonx) {
            if (state == 2) {
                op1 = calculate()
                textResult.text = "0"
                op = 3
            } else {
                state = 2
                textResult.text = "0"
                op = 3
            }
        } else if (id == R.id.buttonDivide) {
            if (state == 2) {
                op1 = calculate()
                textResult.text = "0"
                op = 4
            } else {
                state = 2
                textResult.text = "0"
                op = 4
            }
        } else if (id == R.id.buttonEqual) {
            if (state == 2) {
                var rs = calculate()
                textResult.text = "$rs"
                state = 1
                op1 = 0
                op2 = 0
                op = 0
            }
        } else if (id == R.id.buttonC) {
            state = 1
            op1 = 0
            op2 = 0
            op = 0
            textResult.text = "0"
        } else if (id == R.id.buttonCE) {
            if (state == 1) {
                op1 = 0
                textResult.text = "0"
            } else {
                op2 = 0
                textResult.text = "0"
            }
        } else if (id == R.id.buttonBS) {
            if (state == 1) {
                op1 /= 10
                textResult.text = "$op1"
            } else {
                op2 /= 10
                textResult.text = "$op2"
            }
        }
    }

    fun calculate(): Int {
        //+: 1, -: 2, x: 3, /: 4
        if (op == 1) {
            return op1 + op2
        } else if (op == 2) {
            return op1 - op2
        } else if (op == 3) {
            return op1 * op2
        } else {    //phep chia
            if (op2 == 0)
                return Int.MAX_VALUE
            else
                return op1/op2
        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1*10 + c
            textResult.text = "$op1"
        } else {
            op2 = op2*10 + c
            textResult.text = "$op2"
        }
    }
}