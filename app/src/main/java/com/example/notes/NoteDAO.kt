package com.example.notes

import android.arch.persistence.room.Dao
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
}