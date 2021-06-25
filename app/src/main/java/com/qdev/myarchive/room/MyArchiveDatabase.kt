package com.qdev.myarchive.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
* Create room database instance. You can use it like this:
* val db = createRoomDb(context)
* val templateDao = db.templateDao()
* templateDao.getById(0)
*/
fun createRoomDb(applicationContext: Context): MyArchiveDatabase {
    return Room.databaseBuilder(
        applicationContext,
        MyArchiveDatabase::class.java, "MyArchiveDatabase"
    ).build()
}

/**
* TemplateDatabase class holds the database. TemplateDatabase defines the database configuration
* and serves as the app's main access point to the persisted data.
*/
@Database(entities = [FileEntity::class, ArchiveFolderEntity::class], version = 1)
abstract class MyArchiveDatabase : RoomDatabase() {
    abstract fun fileDao(): FileDao
}
