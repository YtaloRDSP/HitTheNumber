package com.example.hitthenumber

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorLong
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var num: Int = 0
    var palpite: Int = 0
    var chances = 5
    var gameOver = false

    fun validar(nE: Int, nS: Int) {
        var msg: String = ""
        //if (nE == ''.toInt()) msg = "Valor Inválido"
        chances--
        if (nE == nS) {
            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#77dd77")))
            gameOver = true
            msg = "Parabéns! Vc acertou"
        }
        else{
            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#ff0000")))
            if (nS - nE <= 5 && nS - nE >= -5) msg = "Chegou perto!"
            else if (nE > nS) msg = "Chutou alto!"
            else msg = "Chutou baixo"
        }

        if(chances != 0 || (chances == 0 && gameOver)) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        if(gameOver or (chances == 0 && !gameOver)){
            if (chances == 0) Toast.makeText(this, "ERROU! O número era: $num", Toast.LENGTH_SHORT).show()
            finishAffinity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_OK.setOnClickListener {
            palpite = rec_numero.text.toString().toInt()
            validar(palpite, num)
            rec_numero.setText("")
            txt_chances.setText("Chances: $chances")
        }

    }

    override fun onStart(){
        super.onStart()
        num = Random.nextInt(1, 100)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "The game is On", Toast.LENGTH_SHORT).show()
        Log.i("Resuminho", "$num")
    }
}