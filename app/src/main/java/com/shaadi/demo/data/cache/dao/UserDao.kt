package com.shaadi.demo.data.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shaadi.demo.data.cache.model.UserCache

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user:UserCache):Long


    @Query("SELECT * FROM user")
    fun getUsers():LiveData<List<UserCache>>

    @Update
    suspend fun updateUser(user: UserCache):Int
}