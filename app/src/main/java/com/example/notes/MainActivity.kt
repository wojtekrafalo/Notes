package com.example.notes

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), EditTextFragment.OnFragmentInteractionListener {
    private var numberOfFragments:Int = 0
    private lateinit var editTextFragment:EditTextFragment
    private lateinit var editTextMenuFragment:EditTextMenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextFragment = fragEditTextFragment as EditTextFragment
        editTextMenuFragment = fragEditTextMenuFragment as EditTextMenuFragment
        editTextMenuFragment.setListener(editTextFragment)
    }


    private fun addEditTextFragment (){
        val frag:EditTextFragment = EditTextFragment.newInstance()
        supportFragmentManager.beginTransaction().add(numberOfFragments, frag).commit()
//        frag.setListener(this)
        numberOfFragments++
    }

    //TODO tak tworzysz bazę danych. Do klasy abstrakcyjnej NotesDatabase dodasz kolejne metody z innymi tabelami.
    // Trzeba do każdej z nich utworzyć odpowiednie Entity z kolumnami podobne do mojego EditTextEntity.
    // oraz odpowiednie klasy z podstawowymi kwerendami takie jak EditTextDAO.
    // Jak chcesz dostać tabelę z danymi z pola tekstowego użyj metody getTableEditText() z fragmentu EditTextFragment.
    // Jak chcesz zapisać w polu tekstowym dane z tabeli użyt setTableEditText() też z tego fragmentu.
    // Podobnie działa getter i setter na fragmentach z rysowaniem od Bartka.
    private fun createDatabase(): NotesDatabase? {
        try {
            val database = Room.databaseBuilder(
                    this,
                    NotesDatabase::class.java,
                    "NOTES_DATABASE.db"
            ).build()

            val ed = editTextFragment.getTableEditText()
            database.editTextDAO().insertAll(ed)
            return database

        } catch (e: Exception) {
            Log.i("am2019", e.message)
        }

        return null
    }

    override fun onFragmentInteractionEdit(editText:EditText) {
    }
}
