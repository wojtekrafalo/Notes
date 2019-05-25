package com.example.notes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NoteActivity : AppCompatActivity() {

    var idInDB: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        idInDB = intent.getIntExtra("idInDB", 0)
    }

    fun onClick(view: View) {
        when(view.getId()) {
            R.id.buttonText -> {
                var intent = Intent(this, NoteTextActivity::class.java)
                intent.putExtra("idInDB", idInDB)
                startActivity(intent)
            }
            R.id.buttonPaint -> {
                var intent = Intent(this, NotePaintActivity::class.java)
                intent.putExtra("idInDB", idInDB)
                startActivity(intent)
            }
        }
    }
}
