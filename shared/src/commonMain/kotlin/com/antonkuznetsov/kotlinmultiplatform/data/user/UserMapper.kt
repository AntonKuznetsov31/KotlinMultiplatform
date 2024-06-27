package com.antonkuznetsov.kotlinmultiplatform.data.user

import com.antonkuznetsov.kotlinmultiplatform.domain.user.User
import database.UserEntity

fun UserEntity.toUser(): User {
	return User(
		id = id,
		firstName = firstName,
		lastName = lastName
	)
}