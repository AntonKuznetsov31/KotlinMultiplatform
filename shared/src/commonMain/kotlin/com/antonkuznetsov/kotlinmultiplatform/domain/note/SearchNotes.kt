package com.antonkuznetsov.kotlinmultiplatform.domain.note

class SearchNotes {
	fun execute(notes: List<Note>, query: String): List<Note> {
		if (query.isEmpty()) {
			return notes
		}
		return notes.filter { it.text.contains(query) }
	}
}