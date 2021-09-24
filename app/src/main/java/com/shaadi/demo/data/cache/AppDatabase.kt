package com.shaadi.demo.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaadi.demo.data.cache.dao.UserDao
import com.shaadi.demo.data.cache.model.UserCache

@Database(entities = [UserCache::class],version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object{
        val DATABASE_NAME:String = "shaadi_db"
    }
}