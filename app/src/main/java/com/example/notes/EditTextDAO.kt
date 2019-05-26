package com.example.notes

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface EditTextDAO {

    @Query("SELECT * FROM edit_text")
    fun getAll() : List<EditTextEntity>

    @Insert
    fun insertAll(vararg editTextEntity: EditTextEntity)
}