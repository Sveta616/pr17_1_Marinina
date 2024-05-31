package com.example.pr17_1

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

class Individ : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individ)
        val spinner = findViewById<Spinner>(R.id.spinner)
        var selecte = spinner.selectedItemPosition
        pref = getPreferences(MODE_PRIVATE)
        if (pref.getInt("spinner", 0) != 0) {
            selecte= pref.getInt("spinner", 0)
        }
        f(selecte)
    }

    fun spin(v: View) {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val selecte = spinner.selectedItemPosition
        pref = getPreferences(MODE_PRIVATE)
        val ed = pref.edit()
        ed.putInt("spinner", selecte)
        ed.apply()
        f(selecte)

    }


    private fun f(selectedposition: Int) {
        val editText1 = findViewById<EditText>(R.id.edit1)
        val editText2 = findViewById<EditText>(R.id.edit2)
        val editText3 = findViewById<EditText>(R.id.edit3)
        imageView = findViewById<ImageView>(R.id.imageView)
        var image: Int
        when (selectedposition) {
            0 -> {
                image = R.drawable.triangle
                editText1.hint = "1 Сторона"
                editText1.visibility = View.VISIBLE
                editText2.hint = "2 Сторона"
                editText2.visibility = View.VISIBLE
                editText3.hint = "3 Сторона"
                editText3.visibility = View.VISIBLE
            }

            1 -> {
                image = R.drawable.square
                editText1.visibility = View.VISIBLE
                editText1.hint = "Высота"
                editText2.visibility = View.VISIBLE
                editText2.hint = "Ширина"
                editText3.visibility = View.GONE
            }

            2 -> {
                image = R.drawable.circle
                editText1.hint = "Радиус"
                editText1.visibility = View.VISIBLE
                editText2.visibility = View.GONE
                editText3.visibility = View.GONE
            }

            else -> image = 0
        }

        imageView.setImageResource(image)


    }

    fun doit(view: View) {
        val editText1 = findViewById<EditText>(R.id.edit1)
        val editText2 = findViewById<EditText>(R.id.edit2)
        val editText3 = findViewById<EditText>(R.id.edit3)
        val answerTextView = findViewById<TextView>(R.id.answer)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val selecte = spinner.selectedItemPosition
        var result: Double
        try {
            when (selecte) {
                1 -> result =
                    (editText1.text.toString().toDouble() + editText2.text.toString().toDouble())*2

                0 -> result = editText1.text.toString().toDouble() + editText2.text.toString()
                    .toDouble() + editText3.text.toString().toDouble()

                2 -> result = 2 * Math.PI * editText1.text.toString().toFloat()
                else -> result = 0.0
            }
            answerTextView.text = result.toString()
        } catch (e: Exception)
        {

        }
    }
}