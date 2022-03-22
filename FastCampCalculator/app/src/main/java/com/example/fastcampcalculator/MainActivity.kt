package com.example.fastcampcalculator

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.fastcampcalculator.dao.HistoryDao
import com.example.fastcampcalculator.model.History
import java.lang.NumberFormatException
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private val historyLayout : View by lazy{
        findViewById<View>(R.id.historyLayout)
    }
    private val historyLinearLayout : LinearLayout by lazy{
        findViewById<LinearLayout>(R.id.historyLinearLayout)
    }
    private val tv_expression : TextView by lazy{
        findViewById<TextView>(R.id.tv_expression)
    }
    private val tv_result : TextView by lazy{
        findViewById<TextView>(R.id.tv_result)
    }

    lateinit var db :AppDataBase

    private var isOperator = false
    private var hasOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "historyDB"
        ).build()
    }

    private fun numberButtonClicked(number:String){
        Log.d("button: " ,"numberButton")
        if(isOperator){
            tv_expression.append(" ")
        }
        isOperator = false

        val expressText = tv_expression.text.split(" ")

        if(expressText.isNotEmpty() && expressText.last().length >= 15){
            Toast.makeText(this,"15자리 까지만 사용할 수 있습니다.",Toast.LENGTH_SHORT).show()
            return
        }else if (number == "0" && expressText.last().isEmpty()){
            Toast.makeText(this,"0은 제일 앞에 올 수 없습니다.",Toast.LENGTH_SHORT).show()
            return
        }
        tv_expression.append(number)
        tv_result.text = calculateExpression()
    }

    private fun operatorButtonClicked(operator : String){
        if(tv_expression.text.isEmpty()){
            return
        }

        when{
            isOperator->{
                val text = tv_expression.text.toString()
                tv_expression.text = text.dropLast(1) + operator
            }
            hasOperator->{
                Toast.makeText(this,"연산자는 한 번만 사용할 수 있습니다.",Toast.LENGTH_SHORT).show()
                return
            }
            else ->{
                tv_expression.append(" $operator")
            }
        }
        val span = SpannableStringBuilder(tv_expression.text)
        span.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this,R.color.green)),
            tv_expression.text.length-1,
            tv_expression.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv_expression.text = span

        isOperator = true
        hasOperator = true
        Log.d("expressiong : " , tv_expression.text.toString())
    }

    fun buttonPressed(view : View){
        when(view.id){
            R.id.btn0->numberButtonClicked("0")
            R.id.btn1->numberButtonClicked("1")
            R.id.btn2->numberButtonClicked("2")
            R.id.btn3->numberButtonClicked("3")
            R.id.btn4->numberButtonClicked("4")
            R.id.btn5->numberButtonClicked("5")
            R.id.btn6->numberButtonClicked("6")
            R.id.btn7->numberButtonClicked("7")
            R.id.btn8->numberButtonClicked("8")
            R.id.btn9->numberButtonClicked("9")
            R.id.btn_plus->operatorButtonClicked("+")
            R.id.btn_minus->operatorButtonClicked("-")
            R.id.btn_divide->operatorButtonClicked("/")
            R.id.btn_modulo->operatorButtonClicked("%")
            R.id.btn_multiple->operatorButtonClicked("*")
        }
    }
    fun clearButtonClicked(view : View){
        tv_result.text= ""
        tv_expression.text= ""
        isOperator = false
        hasOperator = false
    }
    fun historyClicked(view : View){
        historyLayout.isVisible = true
        historyLinearLayout.removeAllViews()
        Thread(Runnable {
            db.historyDao().getAll().reversed().forEach{
                runOnUiThread{
                    val historyView = LayoutInflater.from(this).inflate(R.layout.history_row, null, false)
                    historyView.findViewById<TextView>(R.id.expressionTextView).text = it.expression
                    historyView.findViewById<TextView>(R.id.resultTextView).text = "= ${it.result}"
                    historyLinearLayout.addView(historyView)
                }
            }

        }).start()


    }
    fun historyClearButtonClicked(view : View){
        historyLinearLayout.removeAllViews()
        Thread(Runnable {
            db.historyDao().deleteAll()
        }).start()
    }
    fun closeHistoryButtonClicked(view : View){
        historyLayout.isVisible = false
    }



    fun resultButtonClicked(view : View){
        val expressionTexts = tv_expression.text.split(" ")
        if(tv_expression.text.isEmpty() || expressionTexts.size == 1){
            return
        }
        if (expressionTexts.size != 3 && hasOperator){
            Toast.makeText(this, "아직 완성되지 않은 수식입니다." ,Toast.LENGTH_SHORT).show()
            return
        }
        if(!expressionTexts[0].isNumber() || !expressionTexts[2].isNumber()) {
            Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val expressionText = tv_expression.text.toString()
        val resultText = calculateExpression()




        Thread(Runnable{
            db.historyDao().insertHistory(History(null,expressionText,resultText))
        }).start()


        tv_result.text = ""
        tv_expression.text = resultText

        isOperator = false
        hasOperator = false

    }

    private fun calculateExpression():String{
        val expressionTexts = tv_expression.text.split(" ")
        if(hasOperator.not() || expressionTexts.size != 3){
            Log.d("calc: ", "1번")
            return ""
        }
        else if(!expressionTexts[0].isNumber() || !expressionTexts[2].isNumber()){
            Log.d("calc: ", "2번")
            return ""
        }
        val exp1 = expressionTexts[0].toBigInteger()
        val exp2 = expressionTexts[2].toBigInteger()
        val op = expressionTexts[1]
        Log.d("my log : ", op)
        return when(op){
            "+"->(exp1+exp2).toString()
            "-"->(exp1-exp2).toString()
            "*"->(exp1*exp2).toString()
            "/"->(exp1/exp2).toString()
            "%"->(exp1%exp2).toString()
            else -> ""
        }

    }


}

fun String.isNumber(): Boolean{
    return try{
        this.toBigInteger()
        true
    }
    catch (e: NumberFormatException){
        false
    }

}
