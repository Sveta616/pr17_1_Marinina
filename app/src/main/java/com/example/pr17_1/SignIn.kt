package com.example.pr17_1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class SignIn : AppCompatActivity() {
    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var pref: SharedPreferences
    private lateinit var save: TextView
    private lateinit var savep: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        save = findViewById(R.id.save_log);
        savep = findViewById(R.id.save_password);

    }

    fun handler(v: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.chose_d))
            .setMessage(getString(R.string.choose_mess))
            .setCancelable(true)

            .setPositiveButton(getString(R.string.save_d)) { _, _ ->
                pref = getPreferences(MODE_PRIVATE)
                val ed = pref.edit()
                ed.putString("login", login.text.toString())
                ed.putString("password", password.text.toString())
                ed.apply()

                save.text = (pref.getString("login", ""))
                savep.text = (pref.getString("password", ""))

                val alert = AlertDialog.Builder(this)
                    .setTitle(getString(R.string.success))
                    .setMessage(getString(R.string.save_messss))
                    .setPositiveButton(getString(R.string.etc), null)
                    .create()
                    .show()
            }

            .setNegativeButton(getString(R.string.load)) { _, _ ->
                pref = getPreferences(MODE_PRIVATE)
                login.setText(pref.getString("login", ""))
                password.setText(pref.getString("password", ""))
                val alert = AlertDialog.Builder(this)
                    .setTitle(R.string.success)
                    .setMessage(getString(R.string.save_mess))
                    .setPositiveButton(getString(R.string.etc), null)
                    .create()
                    .show()
            }
        builder.create()
        builder.show()

    }

    fun nextb(view: View) {
        if (login.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
        {
            val intent = Intent(this, Individ::class.java)
            startActivity(intent)
        } else
        {

            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Введите логин или пароль")
                .setPositiveButton("Продолжить", null)
                .create()
                .show()

        }
    }
}


