package com.example.notes

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @ColumnInfo(name = "text") var text : String,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id : Long = 0
)