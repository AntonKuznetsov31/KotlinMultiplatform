package com.antonkuznetsov.kotlinmultiplatform.android.user_list

import com.antonkuznetsov.kotlinmultiplatform.domain.note.Note

data class NotesListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)