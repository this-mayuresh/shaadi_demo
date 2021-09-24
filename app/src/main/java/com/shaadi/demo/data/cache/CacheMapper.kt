package com.shaadi.demo.data.cache

import com.shaadi.demo.data.cache.model.UserCache
import com.shaadi.demo.data.model.User
import com.shaadi.demo.utility.EntityMapper
import com.shaadi.demo.utility.getGender
import javax.inject.Inject

class CacheMapper
@Inject
constructor() :
    EntityMapper<UserCache, User> {

    override fun mapFromEntity(entity: UserCache): User {
        return User(
            id = entity.id,
            name = entity.name,
            bio = entity.bio,
            status = entity.status,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: User): UserCache {
        return UserCache(
            id = domainModel.id,
            name = domainModel.name,
            bio = domainModel.bio,
            status = domainModel.status,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<UserCache>): List<User> {
        return entities.map { mapFromEntity(it) }
    }
}