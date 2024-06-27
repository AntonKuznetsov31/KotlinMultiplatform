package com.antonkuznetsov.kotlinmultiplatform.domain.user

class SearchUsers {
	fun execute(users: List<User>, query: String): List<User> {
		if (query.isEmpty()) {
			return users
		}
		return users.filter { it.firstName.contains(query) || it.lastName.contains(query) }
	}
}