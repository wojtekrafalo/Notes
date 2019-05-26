package com.example.notes

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NoteDAO {

    /**
     *  Operations on "notes_descriptions" table
     */

    @Query("select * from notes_descriptions")
    fun getAllCategories() : List<NoteDescription>

    @Query("select * from notes_descriptions order by id desc limit 1")
    fun getLastAdded() : NoteDescription

    @Insert
    fun insertNoteDescr(vararg noteDescr : NoteDescription)

    @Query("delete from notes_descriptions where id == :idInDB")
    fun deleteNoteDescr(idInDB: Long)

    @Query("delete from notes_descriptions")
    fun deleteAllDescr()

    /**
     *  Operations on "notes" table
     */

    @Query("select * from notes")
    fun getAll() : List<Note>

    @Query("select * from notes where id == :idInDB")
    fun getNote(idInDB: Long) : Note

    @Query("update notes set brushColor = :brushColor, brushWidth = :brushWidth, lowestY = :lowestY, Paths = :Paths where id == :idInDB")
    fun updatePaintNote(idInDB: Long, brushColor: Int, brushWidth: Float, lowestY: Float, Paths: String)

    @Query("update notes set text  = :text where id == :idInDB")
    fun updateTextNote(idInDB: Long, text: String)

    @Insert
    fun insertAll(vararg note : Note)

    @Query("delete from notes where id == :idInDB")
    fun deleteNote(idInDB: Long)

    @Query("delete from notes")
    fun deleteAll()
}