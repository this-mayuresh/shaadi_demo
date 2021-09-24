package com.shaadi.demo.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserCache(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var name: String,
    var bio: String,
    var status: Int,
    var image: String
)