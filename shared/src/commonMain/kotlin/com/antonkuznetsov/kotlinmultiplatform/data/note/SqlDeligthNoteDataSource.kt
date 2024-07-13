package com.antonkuznetsov.kotlinmultiplatform.data.note

import com.antonkuznetsov.kotlinmultiplatform.domain.note.Note
import com.antonkuznetsov.kotlinmultiplatform.domain.note.NoteDataSource
import com.antonkuznetsov.kotlinmultiplatform.cache.AppDatabase

class SqlDeligthNoteDataSource(db: AppDatabase): NoteDataSource {
	
	private val queries = db.modelQueries
	
	override suspend fun insertNote(note: Note) {
		queries.insertNote(
			id = note.id,
			text = note.text
		)
	}
	
	override suspend fun getNoteById(id: Long): Note? {
		return queries
			.getNoteById(id)
			.executeAsOneOrNull()
			?.toNote()
	}
	
	override suspend fun getAllNotes(): List<Note> {
		return queries
			.getAllNotes()
			.executeAsList()
			.map { it.toNote() }
	}
	
	override suspend fun deleteNoteById(id: Long) {
		queries.deleteNoteById(id)
	}
}
