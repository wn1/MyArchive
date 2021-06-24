package com.qdev.myarchive.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
* DAO provide methods that your app can use to query, update, insert, and delete data in the database.
*/
@Dao
interface FileDao {

    @Query("SELECT * FROM FileEntity WHERE id = :id")
    fun getById(id: Long): FileEntity?

    @Insert
    fun insert(file: FileEntity)
    
    @Update
    fun update(file: FileEntity)

    @Delete
    fun delete(file: FileEntity)
}