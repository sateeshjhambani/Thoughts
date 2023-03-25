package com.sateeshjh.thoughts.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.sateeshjh.thoughts.feature_note.data.repository.FakeNoteRepository
import com.sateeshjh.thoughts.feature_note.domain.model.InvalidNoteException
import com.sateeshjh.thoughts.feature_note.domain.model.Note
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {

    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var getNotesUseCase: GetNotesUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
        getNotesUseCase = GetNotesUseCase(fakeNoteRepository)
    }

    @Test
    fun `Add valid note, successfully added`() = runBlocking {
        val validNote = Note(
            title = "Some Title",
            content = "Some Content",
            color = 1,
            timestamp = 1
        )

        addNoteUseCase(validNote)

        assertThat(validNote).isIn(getNotesUseCase().single())
    }

    @Test(expected = InvalidNoteException::class)
    fun `Add note with empty title, throws InvalidNoteException`() = runBlocking {
        val emptyTitleNote = Note(
            title = "",
            content = "Some Content",
            color = 1,
            timestamp = 1
        )

        addNoteUseCase(emptyTitleNote)
    }

    @Test(expected = InvalidNoteException::class)
    fun `Add note with empty content, throws InvalidNoteException`() = runBlocking {
        val emptyContentNote = Note(
            title = "Some Title",
            content = "",
            color = 1,
            timestamp = 1
        )

        addNoteUseCase(emptyContentNote)
    }
}