package com.example.notes

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.text.Html

@Entity(tableName = "edit_text")
data class EditTextEntity (
    @ColumnInfo(name = "text") var text : String,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id : Int = 0
)