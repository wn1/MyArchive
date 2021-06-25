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
    val backupFileName: String,

    @ColumnInfo
    val fileNameList: List<String>,

    @ColumnInfo
    val originalDate: Long,

    @ColumnInfo
    val scanDate: Long,

    @ColumnInfo
    val doubleSha256: String,

    @ColumnInfo
    val fileSize: Long,

    @ColumnInfo
    val backupDoublesDescriptionFile: String
)