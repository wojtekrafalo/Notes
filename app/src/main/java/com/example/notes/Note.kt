package com.example.notes

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "brushColor") var brushColor: Int,
    @ColumnInfo(name = "brushWidth") var brushWidth: Float,
    @ColumnInfo(name = "lowestY") var lowestY: Float,
    @ColumnInfo(name = "Paths") var Paths: String,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Int? = 0
)