package com.shaadi.demo.di

import com.shaadi.demo.data.cache.CacheMapper
import com.shaadi.demo.data.cache.dao.UserDao
import com.shaadi.demo.data.cache.repository.UserRepo
import com.shaadi.demo.data.network.ApiInterface
import com.shaadi.demo.data.network.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao,
        apiInterface: ApiInterface,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): UserRepo{
        return UserRepo(userDao, apiInterface, cacheMapper, networkMapper)
    }
}