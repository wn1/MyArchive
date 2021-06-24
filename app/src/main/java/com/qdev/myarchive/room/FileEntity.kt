package com.qdev.myarchive.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
* Entities represent tables in your app's database.
*/
@Entity
data class FileEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo
    val fileName: String,

    @ColumnInfo
    val originalName: String
)