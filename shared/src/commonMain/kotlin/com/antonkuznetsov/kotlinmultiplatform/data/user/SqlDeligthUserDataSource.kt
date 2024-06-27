package com.antonkuznetsov.kotlinmultiplatform.data.user

import com.antonkuznetsov.kotlinmultiplatform.domain.user.User
import com.antonkuznetsov.kotlinmultiplatform.domain.user.UserDataSource
import com.antonkuznetsov.kotlinmultiplatform.cache.AppDatabase

class SqlDeligthUserDataSource(db: AppDatabase): UserDataSource {
	
	private val queries = db.modelQueries
	
	override suspend fun insertUser(user: User) {
		queries.insertUser(
			id = user.id,
			firstName = user.firstName,
			lastName = user.lastName
		)
	}
	
	override suspend fun getUserById(id: Long): User? {
		return queries
			.getUserById(id)
			.executeAsOneOrNull()
			?.toUser()
	}
	
	override suspend fun getAllUsers(): List<User> {
		return queries
			.getAllUsers()
			.executeAsList()
			.map { it.toUser() }
	}
	
	override suspend fun deleteUserById(id: Long) {
		queries.deleteUserById(id)
	}
}
