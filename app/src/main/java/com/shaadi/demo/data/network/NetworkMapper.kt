package com.shaadi.demo.data.network

import com.shaadi.demo.data.cache.model.UserCache
import com.shaadi.demo.data.model.User
import com.shaadi.demo.data.network.model.Result as NetworkResult
import com.shaadi.demo.utility.EntityMapper
import com.shaadi.demo.utility.getGender
import javax.inject.Inject

class NetworkMapper
@Inject constructor(): EntityMapper<NetworkResult, User> {
    override fun mapFromEntity(entity: NetworkResult): User {
        val nameObj = entity.name
        val locationObj = entity.location
        return User(
            id = entity.login.uuid,
            name = "${nameObj.first} ${nameObj.last}",
            bio = "${entity.gender.getGender()}, ${entity.dob.age}\n${locationObj.city}, ${locationObj.state}",
            status = 0,
            image = entity.picture.large
        )
    }

    override fun mapToEntity(domainModel: User): NetworkResult? {
        /** Making this nullable only because this wont be used surely*/
        return null;
    }

    fun mapFromEntityList(entities: List<NetworkResult>): List<User> {
        return entities.map { mapFromEntity(it) }
    }
}