package com.antonkuznetsov.kotlinmultiplatform.data.note

import com.antonkuznetsov.kotlinmultiplatform.domain.note.Note
import database.NoteEntity

fun NoteEntity.toNote(): Note {
	return Note(
		id = id,
		text = text
	)
}