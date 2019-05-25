package com.example.notes

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NoteDAO {

    @Query("select * from notes")
    fun getAll() : List<Note>

    @Query("select * from notes where id == :idInDB")
    fun getNote(idInDB: Int) : Note

    @Insert
    fun insertAll(vararg note : Note)

    @Query("update notes set brushColor = :brushColor, brushWidth = :brushWidth, lowestY = :lowestY, Paths = :Paths where id == :idInDB")
    fun updatePaintNote(idInDB: Int, brushColor: Int, brushWidth: Float, lowestY: Int, Paths: String)

    @Query("update notes set text  = :text where id == :idInDB")
    fun updateTextNote(idInDB: Int, text: String)

    @Query("delete from notes")
    fun deleteAll()
}