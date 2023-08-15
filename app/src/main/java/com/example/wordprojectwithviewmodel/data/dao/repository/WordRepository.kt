package com.example.wordprojectwithviewmodel.data.dao.repository

import androidx.annotation.WorkerThread
import com.example.wordprojectwithviewmodel.data.dao.Word
import com.example.wordprojectwithviewmodel.data.dao.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository (private val dao: WordDao) {
    val allWords: Flow<List<Word>> = dao.getAlphabetzedWord()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        dao.insertWord(word)
    }
}