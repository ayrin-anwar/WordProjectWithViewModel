package com.example.wordprojectwithviewmodel.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT*from word_table ORDER BY word ASC")
    fun getAlphabetzedWord(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Query("DELETE from word_table")
    suspend fun deleteAll()

}