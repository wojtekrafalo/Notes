package com.example.notes

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database( entities = [(Note::class)], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao() : NoteDAO
}