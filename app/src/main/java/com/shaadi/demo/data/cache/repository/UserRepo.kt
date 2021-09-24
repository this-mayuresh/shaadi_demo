package com.shaadi.demo.data.cache.repository

import androidx.lifecycle.LiveData
import com.shaadi.demo.data.cache.CacheMapper
import com.shaadi.demo.data.cache.dao.UserDao
import com.shaadi.demo.data.cache.model.UserCache
import com.shaadi.demo.data.model.User
import com.shaadi.demo.data.network.ApiInterface
import com.shaadi.demo.data.network.NetworkMapper
import com.shaadi.demo.utility.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class UserRepo
constructor(
    private val userDao: UserDao,
    private val apiInterface: ApiInterface,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){

    suspend fun saveUsers(isNetworkAvailable:Boolean):Flow<DataState<Nothing>> = flow {
        emit(DataState.Loading)
        try {
            if(isNetworkAvailable){
                val networkUsers= apiInterface.getUserData().results
                val users = networkMapper.mapFromEntityList(networkUsers)

                for (user in users){
                    userDao.insertUser(cacheMapper.mapToEntity(user))
                }
            }
            emit(DataState.Success)
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

    fun getUsers() = userDao.getUsers()

    suspend fun updateUser(user: User): Int{
     return userDao.updateUser(cacheMapper.mapToEntity(user))
    }
}